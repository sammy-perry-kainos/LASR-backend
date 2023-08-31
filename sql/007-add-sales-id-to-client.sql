START TRANSACTION;

ALTER TABLE Clients
	ADD SalesEmployeeID INT;

ALTER TABLE Clients
    ADD CONSTRAINT fk_salesemployee
		FOREIGN KEY (SalesEmployeeID)
        REFERENCES SalesEmployees(SalesEmployeeID);
        
SET foreign_key_checks = 0;

UPDATE Clients
SET SalesEmployeeID = 2
WHERE ClientID > 5;

UPDATE Clients 
SET SalesEmployeeID = 5
WHERE ClientID <= 5;

SET foreign_key_checks = 1;
        
COMMIT;