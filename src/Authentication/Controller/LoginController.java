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
            createUserTable.executeUpdate("CREATE TABLE IF NOT EXISTS User");
            System.out.println("Table created!"); //Check if it's actually made in both workbench and the Database window in IntelliJ
        }catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println("Could not create table");
        }
    }
}
