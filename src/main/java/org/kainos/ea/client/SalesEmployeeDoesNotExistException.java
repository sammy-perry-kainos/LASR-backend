package org.kainos.ea.client;

public class SalesEmployeeDoesNotExistException extends Throwable{
    @Override
    public String getMessage(){
        return "SalesEmployee does not exist";
    }
}
