package org.kainos.ea.client;

public class FailedToDeleteDeliveryEmployeeException extends Throwable {
    @Override
    public String getMessage(){
        return "Failed to delete a deliveryEmployee";
    }
}
