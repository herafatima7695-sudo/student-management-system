# Student Management System

A Java-based Student Management System using JDBC and MySQL. This project allows users to manage student records through a simple console-based interface.

## 🚀 Features
- Add new student details
- View all student records
- Delete student records
- MySQL database integration using JDBC
- Secure password input from user

## 🛠️ Technologies Used
- Java
- JDBC (Java Database Connectivity)
- MySQL
- SQL

## 🗄️ Database Structure
```sql
CREATE TABLE student (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    grade VARCHAR(10),
    attendance INT
);
