package org.kainos.ea.client;

public class FailedToUpdateProjectException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed to update project";
    }
}
