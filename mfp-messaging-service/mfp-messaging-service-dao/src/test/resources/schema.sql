CREATE TABLE EXPIRED_MESSAGES
(
ID int NOT NULL AUTO_INCREMENT,
MessageId varchar(255) NOT NULL,
Username varchar(255) NOT NULL,
Text varchar(255) NOT NULL,
ExpirationDate DATETIME NOT NULL,
PRIMARY KEY (ID)
);

CREATE TABLE UNEXPIRED_MESSAGES
(
ID int NOT NULL AUTO_INCREMENT,
MessageId varchar(255) NOT NULL,
Username varchar(255) NOT NULL,
Text varchar(255) NOT NULL,
ExpirationDate DATETIME NOT NULL,
PRIMARY KEY (ID)
);

