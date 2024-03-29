# Java JDBC Registration System

This project implements a simple registration system in Java using JDBC (Java database connectivity) for interacting with a MySQL database.

## Setup

### Requirements:
- Java Development Kit (JDK) installed on your machine
- MySQL Server installed locally or accessible remotely
- MySQL Connector/J JDBC driver

### Steps

1. **Clone the Repository:**
   - Clone this repository to your local machine using Git:
     ```
     git clone https://github.com/your-username/registration-system.git
     ```

2. **Download MySQL Connector/J**
   - Download the MySQL Connector/J JDBC driver from the official MySQL website: [MySQL Connector/J Downloads](https://dev.mysql.com/downloads/connector/j/), click on the first link and download it according to your System OS.
   - Add the downloaded JAR file to your project's classpath.
     - **For IntelliJ IDEA:**
       - Right-click on the project name in the project explorer.
       - Select "Open Module Settings" (or "Module Settings").
       - Go to the "Libraries" tab.
       - Click on the "+" icon and choose "Java".
       - Navigate to the location of the downloaded JAR file and select it.
       - Click "OK" to add the JAR file to the classpath.
     - **For Eclipse:**
       - Right-click on the project name in the project explorer.
       - Select "Build Path" > "Configure Build Path".
       - Go to the "Libraries" tab.
       - Click on "Add External JARs".
       - Navigate to the location of the downloaded JAR file and select it.
       - Click "Apply and Close" to add the JAR file to the classpath.

3. **Configure Database Connection**
   - Update the database connection properties in the `RegistrationCRUD.java` file:
     ```java
     private static final String URL = "jdbc:mysql://localhost:3306/Database_name";
     private static final String USER_NAME = "username";
     private static final String PASSWORD = "password";
     ```
     Replace `Database_name`, `username`, and `password` with your MySQL database name, username, and password respectively.

4. **Database Setup**
   - Create a MySQL database.
   - Then, create the registration table using the provided SQL script:
     ```sql
     CREATE TABLE registration (
         ID INT PRIMARY KEY AUTO_INCREMENT,
         Name VARCHAR(255) NOT NULL,
         Email VARCHAR(255) NOT NULL,
         DateOfBirth DATE,
         Phone_Number VARCHAR(15),
         Address TEXT
     );
     ```

5. **Run the Application**
   - Run the `RegistrationCRUD.java` file to start the application.

6. **Test the CRUD Operations**
   - Choose the appropriate option from the menu displayed in the console to:
     - Insert data
     - Read data
     - Update data (single column or multiple columns)
     - Delete data
