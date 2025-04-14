package CourseManagement.Controller;

import CourseManagement.Model.Course;
import CourseManagement.View.CourseListView;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseListViewController {
    CourseListView courseListView;

    public CourseListViewController() {
        System.out.println("Displaying Course List View...");
        courseListView = new CourseListView(this);
    }

    public void displayCourseList(){
        Connection connection = DBConnection.getConnection();

        try{
            PreparedStatement displayCourseList = connection.prepareStatement("SELECT * FROM Course");
            ResultSet resultSet = displayCourseList.executeQuery();
    } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Could not display course list.");
        }
}
