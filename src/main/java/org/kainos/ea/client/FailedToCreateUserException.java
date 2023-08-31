package org.kainos.ea.client;

public class FailedToCreateUserException extends Throwable {
    @Override
    public String getMessage() { return "Failed to create user"; }
}
