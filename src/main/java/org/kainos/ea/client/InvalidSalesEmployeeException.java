package org.kainos.ea.client;

public class InvalidSalesEmployeeException extends Throwable {
    public InvalidSalesEmployeeException(String error){
        super(error);
    }
}
