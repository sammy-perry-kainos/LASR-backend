package org.kainos.ea.api;

import org.kainos.ea.client.FailedToAssignDeliveryEmployeeToProjectException;
import org.kainos.ea.db.DeliveryEmployeeProjectsDAO;

import java.sql.SQLException;

public class DeliveryEmployeeProjectService {
    DeliveryEmployeeProjectsDAO deliveryEmployeeProjectsDAO;

    public DeliveryEmployeeProjectService(DeliveryEmployeeProjectsDAO deliveryEmployeeProjectsDAO) {
        this.deliveryEmployeeProjectsDAO = deliveryEmployeeProjectsDAO;
    }

    public int assignDeliveryEmployeeToProject(int deliveryEmployee, int project) throws FailedToAssignDeliveryEmployeeToProjectException {
        try{
            return deliveryEmployeeProjectsDAO.assignDeliveryEmployeeToProject(deliveryEmployee, project);
        } catch(SQLException e){
            throw new FailedToAssignDeliveryEmployeeToProjectException();
        }
    }
}
