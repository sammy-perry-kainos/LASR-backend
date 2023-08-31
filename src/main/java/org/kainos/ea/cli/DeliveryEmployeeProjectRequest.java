package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeliveryEmployeeProjectRequest {
    int projectId;
    int deliveryEmployeeId;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getDeliveryEmployeeId() {
        return deliveryEmployeeId;
    }

    public void setDeliveryEmployeeId(int deliveryEmployeeId) {
        this.deliveryEmployeeId = deliveryEmployeeId;
    }

    @JsonCreator
    public DeliveryEmployeeProjectRequest(@JsonProperty("projectId") int projectId,
                                          @JsonProperty("deliveryEmployeeId")  int deliveryEmployeeId) {
        this.projectId = projectId;
        this.deliveryEmployeeId = deliveryEmployeeId;
    }
}
