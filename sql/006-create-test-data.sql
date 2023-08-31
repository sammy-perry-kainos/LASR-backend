INSERT INTO Clients (Name, Address, PhoneNumber) VALUES
    ('John Doe', '123 Main St, City A', '555-123411'),
    ('Jane Smith', '456 Elm St, City B', '555-567811'),
    ('Michael Johnson', '789 Oak St, City C', '555-119876'),
    ('Sarah Williams', '234 Maple St, City D', '555-543211'),
    ('David Lee', '567 Pine St, City E', '555-876115'),
    ('Emily Brown', '890 Birch St, City F', '555-112345'),
    ('Daniel Martinez', '123 Cedar St, City G', '11555-6543'),
    ('Olivia Taylor', '456 Walnut St, City H', '51155-3456'),
    ('James Anderson', '789 Willow St, City I', '11555-7654'),
    ('Sophia Wilson', '234 Cherry St, City J', '51155-4321');
    
INSERT INTO DeliveryEmployees (Name, Salary, BankAccountNumber, NationalInsuranceNumber)
VALUES
    ('John Doe', 50000.00, '12345678', 'ABC123XYZ'),
    ('Jane Smith', 55000.00, '98765432', 'DEF456UVW'),
    ('Michael Johnson', 48000.00, '56789012', 'GHI789RST'),
    ('Emily Brown', 52000.00, '34567890', 'JKL012ABC'),
    ('David Wilson', 60000.00, '87654321', 'MNO345DEF'),
    ('Sarah Lee', 53000.00, '23456789', 'PQR678GHI'),
    ('Daniel Clark', 49000.00, '76543210', 'STU901JKL'),
    ('Jessica Taylor', 58000.00, '45678901', 'VWX234MNO'),
    ('Kevin Martinez', 51000.00, '65432109', 'YZA567PQR'),
    ('Linda Anderson', 54000.00, '34567812', 'BCD890STU');
    
INSERT INTO SalesEmployees (Name, Salary, BankAccountNumber, NationalInsuranceNumber, CommissionRate)
VALUES
    ('John Smith', 50000.00, '12345678', 'AB123456C', 0.10),
    ('Jane Doe', 55000.00, '87654321', 'CD987654A', 0.12),
    ('Michael Johnson', 60000.00, '45678901', 'EF234567B', 0.08),
    ('Emily Williams', 52000.00, '56789012', 'GH345678D', 0.15),
    ('Robert Brown', 48000.00, '23456789', 'IJ456789E', 0.09),
    ('Jessica Taylor', 53000.00, '34567890', 'KL567890F', 0.11),
    ('David Lee', 58000.00, '67890123', 'MN678901G', 0.07),
    ('Sarah Anderson', 51000.00, '78901234', 'OP789012H', 0.13),
    ('Christopher Martinez', 57000.00, '89012345', 'QR890123I', 0.10),
    ('Linda Wilson', 54000.00, '90123456', 'ST901234J', 0.14);
    
INSERT INTO Projects (Name, Value, TechLead, ClientID)
VALUES
    ('Project 1', 50000.00, 1, 1),
    ('Project 2', 75000.00, 2, 2),
    ('Project 3', 120000.00, 3, 3),
    ('Project 4', 90000.00, 4, 1),
    ('Project 5', 60000.00, 2, 4),
    ('Project 6', 80000.00, 5, 2),
    ('Project 7', 95000.00, 3, 3),
    ('Project 8', 110000.00, 1, 4),
    ('Project 9', 70000.00, 4, 1),
    ('Project 10', 85000.00, 5, 3);
    
SELECT * FROM Clients;

SELECT * FROM DeliveryEmployees;

SELECT * FROM SalesEmployees;

SELECT * FROM Projects;

SELECT * FROM DeliveryEmployees_Projects;