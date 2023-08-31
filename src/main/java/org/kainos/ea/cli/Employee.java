package org.kainos.ea.cli;

public class Employee {
    public int getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(int employeeID) {
        EmployeeID = employeeID;
    }

    private int EmployeeID;
    private String name;
    private double salary;

    public Employee(int EmployeeID, String name, double salary, String bankNumber, String NI) {
        this.name = name;
        this.salary = salary;
        this.bankNumber = bankNumber;
        this.NI = NI;
        this.EmployeeID = EmployeeID;
    }

    private String bankNumber;

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
        return NI;
    }

    public void setNI(String NI) {
        this.NI = NI;
    }

    private String NI;


}
