START TRANSACTION;

CREATE TABLE Projects (
	ProjectID int AUTO_INCREMENT PRIMARY KEY,
    Name varchar(100) NOT NULL,
    Value decimal(15,2) NOT NULL,
    TechLead int NOT NULL,
    ClientID int NOT NULL,
    FOREIGN KEY (TechLead) REFERENCES DeliveryEmployees(DeliveryEmployeeID),
    FOREIGN KEY (ClientID) REFERENCES Clients(ClientID)
);

COMMIT;