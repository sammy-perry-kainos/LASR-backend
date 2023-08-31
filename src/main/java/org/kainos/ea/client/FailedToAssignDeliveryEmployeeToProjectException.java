package org.kainos.ea.client;

public class FailedToAssignDeliveryEmployeeToProjectException extends Throwable {
    @Override
    public String getMessage(){
        return "Failed to assign Delivery Employee to Project";
    }
}
