package com.INI8LABS.task;

import java.sql.*;
import java.util.Scanner;

public class RegistrationCRUD {
    private static final String URL = "jdbc:mysql://localhost:3306/ini8_labs_task";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "Mahesh11@#";

    private static final String INSERT_REGISTRATION = "INSERT INTO Registration (Name, Email, DateOfBirth, Phone_Number, Address) VALUES (?, ?, ?, ?, ?)";
    private static final String READ_REGISTRATIONS = "SELECT * FROM Registration";
    private static final String UPDATE_REGISTRATION = "UPDATE Registration SET Name = ?, Email = ?, DateOfBirth = ?, Phone_Number = ?, Address = ? WHERE ID = ?";
    private static final String DELETE_REGISTRATION = "DELETE FROM Registration WHERE ID = ?";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);

            System.out.println("Select appropriate option");
            System.out.println("1 - Insert Data");
            System.out.println("2 - Read data");
            System.out.println("3 - Update single column Data");
            System.out.println("4 - Update multiple column Data");
            System.out.println("5 - Delete Data");
            System.out.println("6 - Exit");

            boolean b = true;
            while (b) {
                System.out.println("Enter the option: ");
                int option = scan.nextInt();
                if (option > 0 && option <= 6) {
                    switch (option) {
                        case 1: {
                            insertRegistration(con, scan);
                            b = true;
                            break;
                        }
                        case 2: {
                            readRegistrations(con);
                            b = true;
                            break;
                        }
                        case 3: {
                            updateSingleColumnInRegistration(con, scan);
                            b = true;
                            break;
                        }
                        case 4: {
                            updateMultipleColumnInRegistration(con, scan);
                            b = true;
                            break;
                        }
                        case 5: {
                            deleteRegistration(con, scan);
                            b = true;
                            break;
                        }
                        case 6: {
                            System.out.println("Exiting from the database");
                            b = false;
                            break;
                        }
                        default: {
                            System.out.println("Please choose a valid option");
                            b = false;
                        }
                    }
                } else {
                    System.out.println("Enter a valid option");
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error: JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error: Unable to establish connection to the database.");
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println("Error: Unable to close database connection.");
                    e.printStackTrace();
                }
            }
        }
    }

    // Method to insert the data into the Registration table
    private static void insertRegistration(Connection con, Scanner scan) {
        try {
            PreparedStatement pstmt = con.prepareStatement(INSERT_REGISTRATION);
            System.out.println("Enter Name:");
            pstmt.setString(1, scan.next());

            System.out.println("Enter Email:");
            pstmt.setString(2, scan.next());

            System.out.println("Enter Date of Birth (YYYY-MM-DD):");
            pstmt.setString(3, scan.next());

            System.out.println("Enter Phone Number:");
            pstmt.setString(4, scan.next());
            scan.nextLine();

            System.out.println("Enter Address:");
            pstmt.setString(5, scan.nextLine());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Registration inserted successfully");
            } else {
                System.out.println("Error: Failed to insert registration. Please try again.");
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed to insert registration. Please check your input and try again.");
            e.printStackTrace();
        }
    }

    // Method to read the data from the Registration table
    private static void readRegistrations(Connection con) {
        try {
            PreparedStatement pstmt = con.prepareStatement(READ_REGISTRATIONS);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("ID = " + rs.getInt("ID") + ", Name = " + rs.getString("Name") +
                        ", Email = " + rs.getString("Email") + ", DateOfBirth = " + rs.getDate("DateOfBirth") +
                        ", Phone_Number = " + rs.getString("Phone_Number") + ", Address = " + rs.getString("Address"));
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed to read registrations.");
            
        }
    }

    // Method to update single column in the Registration table
    private static void updateSingleColumnInRegistration(Connection con, Scanner scan) {
        try {
            System.out.println("Enter the ID of the registration to update:");
            int id = scan.nextInt();
            scan.nextLine();

            System.out.println("Select the field to update:");
            System.out.println("1 - Name");
            System.out.println("2 - Email");
            System.out.println("3 - Date of Birth");
            System.out.println("4 - Phone Number");
            System.out.println("5 - Address");
            int option = scan.nextInt();
            scan.nextLine();

            PreparedStatement pstmt = null;
            switch (option) {
                case 1:
                    System.out.println("Enter new Name:");
                    pstmt = con.prepareStatement("UPDATE Registration SET Name = ? WHERE ID = ?");
                    pstmt.setString(1, scan.nextLine());
                    break;
                case 2:
                    System.out.println("Enter new Email:");
                    pstmt = con.prepareStatement("UPDATE Registration SET Email = ? WHERE ID = ?");
                    pstmt.setString(1, scan.nextLine());
                    break;
                case 3:
                    System.out.println("Enter new Date of Birth (YYYY-MM-DD):");
                    pstmt = con.prepareStatement("UPDATE Registration SET DateOfBirth = ? WHERE ID = ?");
                    pstmt.setString(1, scan.nextLine());
                    break;
                case 4:
                    System.out.println("Enter new Phone Number:");
                    pstmt = con.prepareStatement("UPDATE Registration SET PhoneNumber = ? WHERE ID = ?");
                    pstmt.setString(1, scan.nextLine());
                    break;
                case 5:
                    System.out.println("Enter new Address:");
                    pstmt = con.prepareStatement("UPDATE Registration SET Address = ? WHERE ID = ?");
                    pstmt.setString(1, scan.nextLine());
                    break;
                default:
                    System.out.println("Invalid option.");
                    return;
            }

            pstmt.setInt(2, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Registration updated successfully");
            } else {
                System.out.println("Error: Failed to update registration. Please check your input and try again.");
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed to update registration. Please check your input and try again.");
        }
    }

    // Method to update multiple columns in the Registration table
    private static void updateMultipleColumnInRegistration(Connection con, Scanner scan) {
        try {
            System.out.println("Enter the ID of the registration to update:");
            int id = scan.nextInt();
            PreparedStatement pstmt = con.prepareStatement(UPDATE_REGISTRATION);
            System.out.println("Enter Name:");
            pstmt.setString(1, scan.next());

            System.out.println("Enter Email:");
            pstmt.setString(2, scan.next());

            System.out.println("Enter Date of Birth (YYYY-MM-DD):");
            pstmt.setString(3, scan.next());

            System.out.println("Enter Phone Number:");
            pstmt.setString(4, scan.next());
            scan.nextLine();

            System.out.println("Enter Address:");
            pstmt.setString(5, scan.nextLine());

            pstmt.setInt(6, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Registration updated successfully");
            } else {
                System.out.println("Error: Failed to update registration. Please check your input and try again.");
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed to update registration. Please check your input and try again.");
            e.printStackTrace();
        }
    }

    // Method to delete a row from the Registration table
    private static void deleteRegistration(Connection con, Scanner scan) {
        try {
            System.out.println("Enter the ID of the registration to delete:");
            int id = scan.nextInt();
            PreparedStatement pstmt = con.prepareStatement(DELETE_REGISTRATION);
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Row deleted successfully");
            } else {
                System.out.println("Error: Failed to delete registration. Please check the ID and try again.");
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed to delete registration. Please check the ID and try again.");
            e.printStackTrace();
        }
    }
}
