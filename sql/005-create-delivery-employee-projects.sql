START TRANSACTION;

CREATE TABLE IF NOT EXISTS DeliveryEmployees_Projects (
    DeliveryEmployeeID int NOT NULL,
    ProjectID int NOT NULL,
    PRIMARY KEY (DeliveryEmployeeID, ProjectID)
);

ALTER TABLE DeliveryEmployees_Projects
	ADD CONSTRAINT fk_deliveryemployeesprojects_deliveryemployeesid
		FOREIGN KEY (DeliveryEmployeeID)
        REFERENCES DeliveryEmployees(DeliveryEmployeeID);
        
ALTER TABLE DeliveryEmployees_Projects
	ADD CONSTRAINT fk_deliveryemployeesprojects_projectsid
		FOREIGN KEY (ProjectID)
        REFERENCES Projects(ProjectID);

COMMIT;