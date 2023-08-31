package org.kainos.ea.client;

public class FailedToRemoveDeliveryEmployeeFromProjectException extends Throwable {
    @Override
    public String getMessage(){
        return "Failed to remove delivery employee from project";
    }
}
