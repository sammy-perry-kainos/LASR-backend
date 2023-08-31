CREATE TABLE `Role` (
	RoleID TINYINT NOT NULL,
    Name varchar(64) NOT NULL,
    PRIMARY KEY (RoleID)
);

INSERT INTO Role(RoleID, Name)
VALUES (1, 'Admin');
INSERT INTO Role(RoleID, Name)
VALUES (2, 'User');
INSERT INTO Role(RoleID, Name)
VALUES (3, 'HR');
INSERT INTO Role(RoleID, Name)
VALUES (4, 'Sales');
INSERT INTO Role(RoleID, Name)
VALUES (5, 'Management');

CREATE TABLE `User` (
	Username varchar(64) NOT NULL,
    Password varchar(64) NOT NULL,
    RoleID TINYINT NOT NULL,
    PRIMARY KEY (Username),
    FOREIGN KEY (RoleID) REFERENCES Role(RoleID)
);

INSERT INTO User(Username, Password, RoleID)
VALUES ('admin', 'admin', 1);
INSERT INTO User(Username, Password, RoleID)
VALUES ('user', 'user', 2); 
INSERT INTO User(Username, Password, RoleID)
VALUES ('hr', 'hr', 3);
INSERT INTO User(Username, Password, RoleID)
VALUES ('sales', 'sales', 4); 
INSERT INTO User(Username, Password, RoleID)
VALUES ('management', 'management', 5);  

CREATE TABLE Token (
	Username varchar(64) NOT NULL,
    Token varchar(64) NOT NULL,
    Expiry DATETIME NOT NULL,
    FOREIGN KEY (Username) REFERENCES User(Username)
);
