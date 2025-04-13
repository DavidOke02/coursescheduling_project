package Authentication.Controller;

import Authentication.View.LoginView;
import db.DBConnection;

import java.sql.Connection;
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

    //Add User

    //Delete User

    //View User

    // Authenticates student login using email and password
    public boolean authenticate(String enteredEmail, String enteredPassword) {
        return true;
    }
}
