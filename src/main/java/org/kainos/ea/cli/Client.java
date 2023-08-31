package org.kainos.ea.cli;

public class Client {
    private int clientId;
    private String name;
    private String address;
    private String phoneNumber;

    public Client(int clientId, String name, String address, String phoneNumber) {
        this.clientId = clientId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
