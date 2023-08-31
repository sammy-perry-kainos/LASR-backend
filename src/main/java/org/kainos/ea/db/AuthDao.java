package org.kainos.ea.db;

import org.apache.commons.lang3.time.DateUtils;
import org.eclipse.jetty.server.Authentication;
import org.kainos.ea.cli.Login;
import org.kainos.ea.cli.User;
import org.kainos.ea.client.TokenExpiredException;
import org.kainos.ea.db.DatabaseConnector;

import java.sql.*;
import java.util.Date;
import java.util.UUID;

public class AuthDao {

    private DatabaseConnector databaseConnector;

    public AuthDao(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    public boolean validLogin(Login login) {
        try (Connection c = databaseConnector.getConnection()) {
            Statement st = c.createStatement();

            ResultSet rs = st.executeQuery("SELECT Password FROM `User` WHERE Username = '"
                    + login.getUsername() + "'");

            while (rs.next()) {
                return rs.getString("password").equals(login.getPassword());
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    public String generateToken(String username) throws SQLException {
        String token = UUID.randomUUID().toString();
        Date exipry = DateUtils.addHours(new Date(), 24);

        Connection c = databaseConnector.getConnection();

        String insertStatement = "INSERT INTO Token (Username, Token, Expiry) " +
                "VALUES (?, ?, ?)";

        PreparedStatement st = c.prepareStatement(insertStatement);

        st.setString(1, username);
        st.setString(2, token);
        st.setTimestamp(3, new java.sql.Timestamp(exipry.getTime()));

        st.executeUpdate();

        return token;
    }

    public int createLogin(User user) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String insertStatement = "INSERT INTO User (Username, Password, RoleID) VALUES (?, ?, ?)";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, user.getUsername());
        st.setString(2, user.getPassword());
        st.setInt(3, user.getRoleID());

        st.executeUpdate();

        int updated = st.getUpdateCount();

        if (updated > 0) {
            return updated;
        }

        return -1;
    }

    public boolean checkUserName(String username) throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        // Valid request
        ResultSet rs = st.executeQuery("SELECT COUNT(Username) FROM User WHERE Username='" + username + "'");

        while (rs.next()) {
            if (rs.getInt("COUNT(Username)") > 0) {
                return true;
            }
        }

        return false;
    }

    public boolean checkRoleID(int roleID) throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        // Valid request
        ResultSet rs = st.executeQuery("SELECT COUNT(RoleID) FROM Role WHERE RoleID=" + roleID);

        while (rs.next()) {
            if (rs.getInt("COUNT(RoleID)") == 0) {
                return true;
            }
        }

        return false;
    }

    public int getRoleIDFromToken(String token) throws SQLException, TokenExpiredException {
        Connection c = databaseConnector.getConnection();

        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT RoleID, Expiry FROM `User` " +
                "JOIN `Token` USING (Username) " +
                "WHERE Token = '" + token + "';");

        while (rs.next()) {
            Timestamp expiry = rs.getTimestamp("Expiry");

            if (expiry.after(new Date())) {
                return rs.getInt("RoleID");
            } else {
                throw new TokenExpiredException();
            }
        }

        return -1;
    }
}
