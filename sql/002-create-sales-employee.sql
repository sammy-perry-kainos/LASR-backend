START TRANSACTION;

-- Create SalesEmployee Table
CREATE TABLE IF NOT EXISTS SalesEmployees (
	SalesEmployeeID int AUTO_INCREMENT PRIMARY KEY,
    Name varchar(70) NOT NULL,
    Salary decimal(10,2) NOT NULL, 
    BankAccountNumber varchar(8) NOT NULL,
    NationalInsuranceNumber char(9) NOT NULL,
    CommissionRate double NOT NULL); 
    
COMMIT;