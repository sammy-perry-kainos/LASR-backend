package org.kainos.ea.api;

public class FailedToGetProjectException extends Throwable {
    @Override
    public String getMessage(){
        return "Failed to get projects";
    }
}
