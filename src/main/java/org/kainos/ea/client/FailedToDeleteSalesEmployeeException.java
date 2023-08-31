package org.kainos.ea.client;

public class FailedToDeleteSalesEmployeeException extends Throwable {
    @Override
    public String getMessage(){
        return "Failed to delete a sales employee";
    }
}
