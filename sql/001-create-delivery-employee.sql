START TRANSACTION;

CREATE TABLE IF NOT EXISTS DeliveryEmployees (
    DeliveryEmployeeID      int AUTO_INCREMENT NOT NULL PRIMARY KEY,
    Name                    varchar(70) NOT NULL, 
    Salary                  decimal(10, 2) NOT NULL,
    BankAccountNumber       varchar(8) NOT NULL, 
    NationalInsuranceNumber char(9) NOT NULL
);

COMMIT;