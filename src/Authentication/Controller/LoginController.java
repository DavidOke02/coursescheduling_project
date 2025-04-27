package Authentication.Controller;

import Authentication.View.LoginView;
import db.DBConnection;

import java.sql.*;

public class LoginController {
    private LoginView view;

    private String loggedInUserId;
    private String loggedInUserRole;

    public LoginController() {

        createUserTable();
        insertTestUsers();
    }

    public void createUserTable() {
        Connection connection = DBConnection.getConnection();
        try {
            Statement createUserTable = connection.createStatement();
            createUserTable.executeUpdate("CREATE TABLE IF NOT EXISTS User (" +
                    "  `id` VARCHAR(10) NOT NULL," +
                    "  `firstName` VARCHAR(45) NULL," +
                    "  `middleInitial` CHAR(1) NULL," +
                    "  `lastName` VARCHAR(45) NULL," +
                    "  `role` VARCHAR(45) NULL," +
                    "  `password` VARCHAR(45) NULL," +
                    "  PRIMARY KEY (`id`))");
            System.out.println("User table created, or already exists!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Could not create table");
        }
    }

    public boolean authenticate(String enteredId, String enteredPassword) {
        Connection connection = DBConnection.getConnection();
        try {
            // Ensure the query points to the correct table (coursescheduling_db.User)
            String query = "SELECT * FROM coursescheduling_db.User WHERE id = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, enteredId);
            preparedStatement.setString(2, enteredPassword);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                System.out.println("User not found.");
                return false;
            }

            if (resultSet.next()) {
                loggedInUserId = resultSet.getString("id");
                loggedInUserRole = resultSet.getString("role");
            }

            System.out.println("User authenticated.");
            return true;
        } catch (SQLException e) {
            System.out.println("Error during authentication: " + e.getMessage());
            return false;
        }
    }

    public String getLoggedInUserId() {
        return loggedInUserId;
    }

    public String getLoggedInUserRole() {
        return loggedInUserRole;
    }

    // Directly insert test users
    public void insertTestUsers() {
        Connection connection = DBConnection.getConnection();
        try {
            // Insert Advisor user
            insertUser("John", "Doe", "A", "password", "Advisor", "ADV123");

            // Insert Student user
            insertUser("Jane", "Smith", "B", "password", "Student", "STU123");

            // Insert Admin user
            insertUser("Sarah", "Peters", "F", "password", "Admin", "ADM123");

        } catch (SQLException e) {
           // System.out.println("Error during user insertion: " + e.getMessage());
        }
    }

    // Method to insert a user directly
    private void insertUser(String firstName, String lastName, String middleInitial, String password, String role, String userId) throws SQLException {
        Connection connection = DBConnection.getConnection();

        // Prepare the insert statement to add the new user
        String insertUserQuery = "INSERT INTO User (id, firstName, middleInitial, lastName, role, password) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(insertUserQuery);
        ps.setString(1, userId);        // Set the userId
        ps.setString(2, firstName);     // Set the first name
        ps.setString(3, middleInitial); // Set the middle initial
        ps.setString(4, lastName);      // Set the last name
        ps.setString(5, role);          // Set the role
        ps.setString(6, password);      // Set the password
        ps.executeUpdate();
        System.out.println(role + " user added with ID: " + userId);
    }
}
