package org.kainos.ea.api;

import org.kainos.ea.cli.DeliveryEmployee;
import org.kainos.ea.cli.DeliveryRequest;
import org.kainos.ea.client.DeliveryEmployeeDoesNotExistException;
import org.kainos.ea.client.FailedToCreateDeliveryEmployeeeException;
import org.kainos.ea.client.FailedToDeleteDeliveryEmployeeException;
import org.kainos.ea.client.FailedToGetDeliveryEmployeeException;
import org.kainos.ea.db.DeliveryDao;

import java.sql.SQLException;
import java.util.List;

public class DeliveryService {
    private DeliveryDao deliveryDao;

    public DeliveryService(DeliveryDao deliveryDao) {
        this.deliveryDao = deliveryDao;
    }

    public int createDeliveryEmployee(DeliveryRequest deliveryRequest) throws FailedToCreateDeliveryEmployeeeException {
        try {
            int id = deliveryDao.createDeliveryEmployee(deliveryRequest);

            if (id == -1) {
                throw new FailedToCreateDeliveryEmployeeeException();
            }
            return id;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToCreateDeliveryEmployeeeException();
        }
    }

    public void updateDeliveryEmployee(int id, DeliveryRequest deliveryRequest) throws DeliveryEmployeeDoesNotExistException{
        try {
            DeliveryEmployee employeeToUpdate = deliveryDao.getDeliveryEmployeeById(id);

            if(employeeToUpdate == null){
                throw new DeliveryEmployeeDoesNotExistException();
            }
            deliveryDao.updateDeliveryEmployee(id, deliveryRequest);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public DeliveryEmployee getDeliveryEmployeeById(int id) throws FailedToGetDeliveryEmployeeException, DeliveryEmployeeDoesNotExistException{
        try{
            DeliveryEmployee deliveryEmployee = deliveryDao.getDeliveryEmployeeById(id);

            if(deliveryEmployee == null){
                throw new DeliveryEmployeeDoesNotExistException();
            }
            return deliveryEmployee;
        }catch(SQLException e){
            System.err.println(e.getMessage());

            throw new FailedToGetDeliveryEmployeeException();
        }
    }

    public List<DeliveryEmployee> getAllDeliveryEmployees() throws FailedToGetDeliveryEmployeeException{
        try{
            List<DeliveryEmployee> deliveryEmployeeList = deliveryDao.getAllDeliveryEmployees();
            return deliveryEmployeeList;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            throw new FailedToGetDeliveryEmployeeException();
        }
    }

    public void deleteDeliveryEmployee(int id) throws DeliveryEmployeeDoesNotExistException, FailedToDeleteDeliveryEmployeeException{
        try{
            DeliveryEmployee deliveryEmployee = deliveryDao.getDeliveryEmployeeById(id);

            if(deliveryEmployee == null){
                throw new DeliveryEmployeeDoesNotExistException();
            }
            deliveryDao.deleteDeliveryEmployee(id);
        }catch(SQLException e){
            System.err.println(e.getMessage());

            throw new FailedToDeleteDeliveryEmployeeException();
        }
    }
}
