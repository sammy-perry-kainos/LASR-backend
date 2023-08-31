package org.kainos.ea.core;

import org.kainos.ea.cli.SalesEmployee;
import org.kainos.ea.cli.SalesRequest;

public class SalesEmployeeValidator {
    public String isValidSalesEmployee(SalesRequest employee){
        if(employee.getName().length() > 70){
            return "Name is greater than 70 characters";
        }
        if(employee.getName().isEmpty()){
            return "Name is empty";
        }
        if(employee.getSalary() == 0){
            return "Salary is empty";
        }
        if(employee.getBankNumber().length() != 8){
            return "Bank Account Number is not 8 characters long";
        }
        if(employee.getNI().length() != 9){
            return "NI Number is not 9 character long";
        }

        return null;
    }
}
