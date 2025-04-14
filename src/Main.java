import Authentication.Controller.LoginController;
import CourseManagement.Controller.CourseListViewController;
import CourseManagement.Model.Course;
import db.DBConnection;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        //App start (From login screen)
        //LoginController loginController = new LoginController();
        CourseListViewController controller2= new CourseListViewController();

        //Db Connect
        Connection connection = DBConnection.getConnection();
        System.out.println("Database connection established!"); //Make sure SQL workbench is open

        /**
         * I was gonna put the user table creation code here, but it's in LoginController as it makes more sense there
         */
    }
}