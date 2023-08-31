package org.kainos.ea.client;

public class FailedToGetAllClientsException extends Throwable {
    @Override
    public String getMessage(){
        return "Failed to get all clients";
    }
}
