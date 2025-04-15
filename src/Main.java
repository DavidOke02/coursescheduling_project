import Authentication.Controller.LoginController;
import Authentication.LoginUI;
import CourseManagement.Controller.AdminDashboardController;
import CourseManagement.Controller.CourseListViewController;
import CourseManagement.Model.Course;
import CourseManagement.View.AddCourseUI;
import CourseManagement.View.AdminDashboard;
import db.DBConnection;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        //App start (From login screen)
        //LoginController loginController = new LoginController();

        //For assignment -uncomment one of the 2 at a time to test each use case
        //new LoginUI();
        AdminDashboardController controller = new AdminDashboardController();

        //Db Connect
        Connection connection = DBConnection.getConnection();
        System.out.println("Database connection established!"); //Make sure SQL workbench is open

        /**
         * I was gonna put the user table creation code here, but it's in LoginController as it makes more sense there
         */
    }
}