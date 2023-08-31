package org.kainos.ea.client;

public class FailedToGetSalesEmployeesException extends Throwable{
    @Override
    public String getMessage(){
        return "Failed to get SalesEmployees";
    }
}
