package org.kainos.ea.api;

import org.kainos.ea.cli.DeliveryEmployee;
import org.kainos.ea.cli.SalesEmployee;
import org.kainos.ea.cli.SalesRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.SalesEmployeeValidator;
import org.kainos.ea.db.SalesDao;

import java.sql.SQLException;
import java.util.List;

public class SalesEmployeeService {

    private SalesDao salesDao;
    private SalesEmployeeValidator salesEmployeeValidator;
    public SalesEmployeeService(SalesDao salesDao, SalesEmployeeValidator salesEmployeeValidator){
        this.salesDao = salesDao;
        this.salesEmployeeValidator = salesEmployeeValidator;
    }

    public int createSales(SalesRequest employee) throws FailedToCreateSalesException, InvalidSalesEmployeeException {
        try{
            String validation = salesEmployeeValidator.isValidSalesEmployee(employee);

            if(validation != null){
                throw new InvalidSalesEmployeeException(validation);
            }

            int id = salesDao.createSalesEmployee(employee);

            if(id == -1){
                throw new FailedToCreateSalesException();
            }

            return id;
        }catch(SQLException e){
            System.err.println(e.getMessage());

            throw new FailedToCreateSalesException();
        }
    }

    public void updateSalesEmployee(int id, SalesRequest employee) throws InvalidSalesEmployeeException, FailedToUpdateSalesEmployeeException, SalesEmployeeDoesNotExistException {
        try{
            String validation = salesEmployeeValidator.isValidSalesEmployee(employee);

            if(validation != null){
                throw new InvalidSalesEmployeeException(validation);
            }

            SalesEmployee salesEmployeeToUpdate = salesDao.getSalesEmployeeByID(id);

            if(salesEmployeeToUpdate == null){
                throw new SalesEmployeeDoesNotExistException();
            }

            salesDao.updateSales(id, employee);

        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToUpdateSalesEmployeeException();
        }
    }

    public SalesEmployee viewSalesEmployee(int id) throws FailedToGetSalesEmployeeException, SalesEmployeeDoesNotExistException{
        try{
            SalesEmployee salesEmployee = salesDao.getSalesEmployeeByID(id);

            if(salesEmployee == null){
                throw new SalesEmployeeDoesNotExistException();
            }

            return salesEmployee;
        }catch(SQLException e){
            System.err.println(e.getMessage());

            throw new FailedToGetSalesEmployeeException();
        }
    }

    public List<SalesEmployee> getAllSalesEmployees() throws FailedToGetSalesEmployeesException {

        try {
            List<SalesEmployee> employees = salesDao.getAllSalesEmployees();

            return employees;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetSalesEmployeesException();
        }
    }

    public void deleteSalesEmployee(int id) throws SalesEmployeeDoesNotExistException, FailedToDeleteSalesEmployeeException {
        try{
            SalesEmployee salesEmployee = salesDao.getSalesEmployeeByID(id);

            if(salesEmployee == null){
                throw new SalesEmployeeDoesNotExistException();
            }
            salesDao.deleteSalesEmployee(id);
        }catch(SQLException e){
            System.err.println(e.getMessage());

            throw new FailedToDeleteSalesEmployeeException();
        }
    }
}
