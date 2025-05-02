import Authentication.AdvisorDashboard;
import Authentication.LoginUI;
import Authentication.StudentDashboard;
import CourseManagement.View.AdminDashboard;
import CourseManagement.View.CourseDetailView;
import Scheduling.Controller.StudentDashboardController;
import Scheduling.View.StudentDashboardView;
import Scheduling.View.StudentScheduleView;
import db.DBConnection;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        //App start (From login screen)

        //For assignment - Can either log in with LoginUI or uncomment one dashboard at a time to see that section specifically.
        //new LoginUI();
        AdminDashboard controller = new AdminDashboard();
        //StudentDashboard sdv = new StudentDashboard("STU123");
        //AdvisorDashboard adv =new AdvisorDashboard("ADV123");

        //Db Connect
        Connection connection = DBConnection.getConnection();
        System.out.println("Database connection established!"); //Make sure SQL workbench is open

        /**
         * I was gonna put the user table creation code here, but it's in LoginController as it makes more sense there
         */
    }
}