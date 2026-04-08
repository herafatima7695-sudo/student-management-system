CREATE DATABASE studentdb;
USE studentdb;

CREATE TABLE student (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    grade VARCHAR(10),
    attendance INT
);