package Authentication.Controller;

import Authentication.View.LoginView;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {
    private LoginView view;

    public LoginController() {
        System.out.println("Starting LoginView...");
        this.view = new LoginView(this);
        createUserTable();
    }

    public void createUserTable(){
        //Making a table in the db
        Connection connection = DBConnection.getConnection();
        try {
            Statement createUserTable = connection.createStatement();
            createUserTable.executeUpdate("CREATE TABLE IF NOT EXISTS User (" +
                    "  `id` INT NOT NULL AUTO_INCREMENT," +
                    "  `username` VARCHAR(45) NULL," +
                    "  `password` VARCHAR(45) NULL," +
                    "  `role` VARCHAR(45) NULL," +
                    "  PRIMARY KEY (`id`))");
            System.out.println("User table created, or already exists!"); //Check if it's actually made in both workbench and the Database window in IntelliJ
        }catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println("Could not create table");
        }
    }

    //Show Table
    public void showTable() {
        System.out.println("Attempting to show table...");
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement showUserTable = connection.prepareStatement("SELECT * FROM user");
            ResultSet resultSet = showUserTable.executeQuery();

            if(!resultSet.isBeforeFirst()){
                System.out.println("User table is empty.");
            }

            while(resultSet.next()){
                System.out.print("ID: " + resultSet.getString("ID"));
                System.out.print(", Username: " + resultSet.getString("USERNAME"));
                System.out.print(", Password: " + resultSet.getString("PASSWORD"));
                System.out.println(", Role: " + resultSet.getString("ROLE"));
                System.out.println("----------------------------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not show table.");
        }
    }

    //Add User
    public void addUser(String username, String password, String role) {
        System.out.println("Attempting to add user...");
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement addUser = connection.prepareStatement("INSERT INTO User (username, password, role) VALUES (?, ?, ?)");
            addUser.setString(1, username);
            addUser.setString(2, password);
            addUser.setString(3, role);
            addUser.executeUpdate();
            System.out.println("User added");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not add user.");
        }
        }

    //Delete User
    public void deleteUser(int userId) {
        System.out.println("Attempting to delete user...");
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement deleteUser = connection.prepareStatement("DELETE FROM User WHERE (`id` = (?))");
            deleteUser.setInt(1, userId);
            deleteUser.executeUpdate();
            System.out.println("User deleted");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not delete user.");
        }
    }

    //View User
    public void viewUser(int userId) {
        System.out.println("Attempting to view user...");
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement viewUser = connection.prepareStatement("SELECT * FROM User WHERE ID = (?)");
            viewUser.setInt(1, userId);
            ResultSet resultSet = viewUser.executeQuery();

            if(!resultSet.isBeforeFirst()){
                System.out.println("User with ID of " + userId + " not found.");
            }

            if(resultSet.next()){
                System.out.print("ID: " + resultSet.getString("ID"));
                System.out.print(", Username: " + resultSet.getString("USERNAME"));
                System.out.print(", Password: " + resultSet.getString("PASSWORD"));
                System.out.println(", Role: " + resultSet.getString("ROLE"));
                System.out.println("----------------------------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not find user.");
        }
    }

    // Authenticates student login using email and password
    public boolean authenticate(String enteredUsername, String enteredPassword) {
        Connection connection = DBConnection.getConnection();
        try {
            String query = "SELECT * FROM User WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, enteredUsername);
            preparedStatement.setString(2, enteredPassword);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.isBeforeFirst()){
                System.out.println("User not found.");
                return false;
            }

            resultSet.close();
            preparedStatement.close();

            System.out.println("User authenticated.");
            return true;
        } catch (SQLException e) {
            System.out.println("Error during authentication: ");
            return false;
        }
    }
    }
