package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeliveryRequest {
    String name;
    double salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getNI() {
        return ni;
    }

    public void setNI(String NI) {
        this.ni = NI;
    }

    String bankNumber;
    String ni;



    @JsonCreator
    public DeliveryRequest(@JsonProperty("name") String name, @JsonProperty("salary") double salary, @JsonProperty("bankNumber") String bankNumber, @JsonProperty("NI") String NI) {
        this.name = name;
        this.salary = salary;
        this.bankNumber = bankNumber;
        this.ni = NI;
    }



}
