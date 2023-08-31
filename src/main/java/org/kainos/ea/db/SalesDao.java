package org.kainos.ea.db;

import org.kainos.ea.cli.SalesEmployee;
import org.kainos.ea.cli.SalesRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesDao {
    DatabaseConnector databaseConnector;
    public SalesDao(DatabaseConnector databaseConnector){
        databaseConnector = new DatabaseConnector();
    }

    public SalesEmployee getSalesEmployeeByID(int id) throws SQLException{
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT SalesEmployeeID, Name, Salary, BankAccountNumber, NationalInsuranceNumber, CommissionRate FROM SalesEmployees WHERE SalesEmployeeID =" + id);

        while(rs.next()){
            return new SalesEmployee(
                    rs.getInt("SalesEmployeeID"),
                    rs.getString("Name"),
                    rs.getDouble("Salary"),
                    rs.getString("BankAccountNumber"),
                    rs.getString("NationalInsuranceNumber"),
                    rs.getDouble("CommissionRate")
            );
        }

        return null;
    }


    public int createSalesEmployee(SalesRequest employee) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String insertStatement = "INSERT INTO SalesEmployees (Name, Salary, BankAccountNumber, NationalInsuranceNumber, CommissionRate) " +
                "VALUES(?, ?, ?, ?, ?)";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, employee.getName());
        st.setDouble(2, employee.getSalary());
        st.setString(3, employee.getBankNumber());
        st.setString(4, employee.getNI());
        st.setDouble(5, employee.getCommission());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if(rs.next()){
            return rs.getInt(1);
        }

        return -1;
    }

    public void updateSales(int id, SalesRequest employee) throws SQLException{
        Connection c = databaseConnector.getConnection();

        String updateStatement = "UPDATE SalesEmployees SET Name = ?, Salary = ?, BankAccountNumber = ?, CommissionRate = ? WHERE SalesEmployeeID = ?";

        PreparedStatement st = c.prepareStatement(updateStatement);

        st.setString(1, employee.getName());
        st.setDouble(2, employee.getSalary());
        st.setString(3, employee.getBankNumber());
        st.setDouble(4, employee.getCommission());
        st.setInt(5, id);

        st.executeUpdate();
    }

    public List<SalesEmployee> getAllSalesEmployees() throws SQLException{
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT SalesEmployeeID, Name, Salary, BankAccountNumber, NationalInsuranceNumber, CommissionRate FROM SalesEmployees");

        List<SalesEmployee> employees = new ArrayList<>();

        while(rs.next()){
            SalesEmployee employee =  new SalesEmployee(
                    rs.getInt("SalesEmployeeID"),
                    rs.getString("Name"),
                    rs.getDouble("Salary"),
                    rs.getString("BankAccountNumber"),
                    rs.getString("NationalInsuranceNumber"),
                    rs.getDouble("CommissionRate")
            );

            employees.add(employee);
        }

        return employees;
    }

    public void deleteSalesEmployee(int id) throws SQLException{
        Connection c = databaseConnector .getConnection();

        String deleteStatement = "DELETE FROM SalesEmployees WHERE SalesEmployeeID = ?";

        PreparedStatement st = c.prepareStatement(deleteStatement);

        st.setInt(1, id);

        st.executeUpdate();

    }
}
