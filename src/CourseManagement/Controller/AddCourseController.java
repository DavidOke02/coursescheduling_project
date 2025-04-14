package CourseManagement.Controller;

import CourseManagement.View.AddCourseUI;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class AddCourseController {
    private AddCourseUI view;

    public AddCourseController() {
        System.out.println("AddCourseController started!");
        this.view = new AddCourseUI();
    }

// add course
public void addCourse(String courseID, String Name, int credits, String departmentCode, int Seats, String professor, String prerequisites, String semester) {
    System.out.println("Attempting to add course");
    Connection connection = DBConnection.getConnection();
    try {
        PreparedStatement addCourse = connection.prepareStatement(
                "INSERT INTO Course (courseID, courseName, credits, departmentCode, availableSeats, professor, prerequisites, semesterOffered) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        addCourse.setString(1, courseID);
        addCourse.setString(2, Name);
        addCourse.setInt(3, credits);
        addCourse.setString(4, departmentCode);
        addCourse.setInt(5, Seats);
        addCourse.setString(6, professor);
        addCourse.setString(7, prerequisites);
        addCourse.setString(8, semester);
        addCourse.executeUpdate();
        System.out.println("Course added");
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Could not add course.");
    }
    }
}

