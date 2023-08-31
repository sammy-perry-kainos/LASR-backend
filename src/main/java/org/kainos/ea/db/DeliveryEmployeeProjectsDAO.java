package org.kainos.ea.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class DeliveryEmployeeProjectsDAO {

    public int assignDeliveryEmployeeToProject(int deliveryEmployeeId, int projectId) throws SQLException {
        Connection c = DatabaseConnector.getConnection();

        PreparedStatement st = c.prepareStatement("INSERT INTO DeliveryEmployees_Projects (DeliveryEmployeeID, ProjectID) VALUES (?,?);", Statement.RETURN_GENERATED_KEYS);
        st.setInt(1, deliveryEmployeeId);
        st.setInt(2, projectId);

        return st.executeUpdate();
    }
}
