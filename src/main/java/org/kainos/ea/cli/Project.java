package org.kainos.ea.cli;

public class Project {
    private int projectId;
    private String name;
    private double value;
    private int techLead;
    private int clientId;

    public Project(int projectId, String name, double value, int techLead, int clientId) {
        this.projectId = projectId;
        this.name = name;
        this.value = value;
        this.techLead = techLead;
        this.clientId = clientId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getTechLead() {
        return techLead;
    }

    public void setTechLead(int techLead) {
        this.techLead = techLead;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
