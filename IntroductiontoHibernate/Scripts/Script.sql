USE users;

SELECT * from users;

SELECT TABLE_NAME 
FROM information_schema.TABLES 
WHERE TABLE_SCHEMA = 'users' AND TABLE_NAME = 'users';

CREATE TABLE user(
	id INT PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(30),
	password VARCHAR(30),
	age INT,
	registrationDate DATE
);

USE users;
SELECT COLUMN_NAME FROM information_schema.COLUMNS
WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = 'users';

ALTER TABLE users
ADD `bidddrt3` VARCHAR(255),
ADD `badddte22` TINYINT(1);


