package org.kainos.ea.client;

public class FailedToCreateSalesException extends Throwable{
    @Override
    public String getMessage(){
        return "Failed to create a SalesEmployee";
    }
}
