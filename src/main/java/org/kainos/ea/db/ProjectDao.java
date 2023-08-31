package org.kainos.ea.db;

import org.kainos.ea.cli.Project;
import org.kainos.ea.cli.ProjectRequestAddClient;

import java.sql.*;
import java.util.ArrayList;

public class ProjectDao {

    private DatabaseConnector databaseConnector;

    public ProjectDao(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    public ArrayList<Project> getAllProjects() throws SQLException {
        ArrayList<Project> projects = new ArrayList<Project>();

        Connection c = databaseConnector.getConnection();

        String selectStatement = "SELECT ProjectId, Name, Value, TechLead, ClientID FROM Projects";
        PreparedStatement preparedStatement = c.prepareStatement(selectStatement);

        ResultSet rs = preparedStatement.executeQuery();

        while(rs.next()){
            projects.add(new Project(
                    rs.getInt("ProjectId"),
                    rs.getString("Name"),
                    rs.getDouble("Value"),
                    rs.getInt("TechLead"),
                    rs.getInt("ClientID")));
        }

        return projects;
    }

    public void addClientToProject(int id, ProjectRequestAddClient project)
            throws SQLException {
        Connection c = databaseConnector.getConnection();

        String updateStatement ="UPDATE Projects SET ClientID = ? WHERE ProjectID = ?";

        PreparedStatement st = c.prepareStatement(updateStatement);

        st.setInt(1, project.getClientId());
        st.setInt(2, id);

        st.executeUpdate();
    }

    public boolean validateProjectId(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        // Valid request
        ResultSet rs = st.executeQuery("SELECT COUNT(ProjectID) FROM Projects WHERE ProjectID=" + id);

        while (rs.next()) {
            if (rs.getInt("COUNT(ProjectID)") > 0) {
                return true;
            }
        }

        return false;
    }
}
