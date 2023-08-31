START TRANSACTION;

ALTER TABLE Clients
	ADD SalesEmployeeID INT;

ALTER TABLE Clients
    ADD CONSTRAINT fk_salesemployee
		FOREIGN KEY (SalesEmployeeID)
        REFERENCES SalesEmployees(SalesEmployeeID);
        
COMMIT;