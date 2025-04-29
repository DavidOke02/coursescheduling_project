import CourseManagement.View.AdminDashboard;
import CourseManagement.View.CourseDetailView;
import Scheduling.View.StudentScheduleView;
import db.DBConnection;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        //App start (From login screen)
        //LoginController loginController = new LoginController();

        //For assignment -uncomment one of the 2 at a time to test each use case
        //new LoginUI();
        //AdminDashboard controller = new AdminDashboard();
        //new StudentScheduleView();
        new CourseDetailView("IST412");

        //Db Connect
        Connection connection = DBConnection.getConnection();
        System.out.println("Database connection established!"); //Make sure SQL workbench is open

        /**
         * I was gonna put the user table creation code here, but it's in LoginController as it makes more sense there
         */
    }
}