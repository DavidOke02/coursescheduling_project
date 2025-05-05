package Scheduling.Controller;

import Scheduling.Model.CustomCourse;
import Scheduling.View.RegisterCourseView;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterCourseController {
    private RegisterCourseView registerView;

    public RegisterCourseController() {
        System.out.println("RegisterCourseController started!");
    }

    public void setRegisterView(RegisterCourseView registerView) {
        this.registerView = registerView;
    }

    public boolean registerCourse(String courseID, String courseName, String instructor,
                                  int credits, String departmentCode, int availableSeats,
                                  String prerequisites, String semester, String timeslot) {

        System.out.println("Attempting to register new course: " + courseID + " - " + courseName);

        // Creating a new CustomCourse object with the provided data
        CustomCourse newCourse = new CustomCourse(
                courseID,
                courseName,
                instructor,
                credits,
                departmentCode,
                availableSeats,
                prerequisites,
                semester,
                timeslot
        );

        // Attempting to add the course to the database
        boolean success = addCourseToDatabase(newCourse);

        if (success) {
            System.out.println("Successfully registered course: " + courseID);
            if (registerView != null) {
                registerView.displayRegistrationSuccess(newCourse);
            }
        } else {
            System.out.println("Failed to register course: " + courseID);
            if (registerView != null) {
                registerView.displayRegistrationFailure();
            }
        }

        return success;
    }

    private boolean addCourseToDatabase(CustomCourse course) {
        Connection connection = DBConnection.getConnection();

        try {
            // SQL query to insert the course details into the database with new column names
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO Course (id, Name, credits, department_code, Seats, prerequisites, semester, timeslot) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

            insertStatement.setString(1, course.getCourseID());  // Course ID
            insertStatement.setString(2, course.getCourseName());  // Course Name
            insertStatement.setInt(3, course.getCredits());  // Credits
            insertStatement.setString(4, course.getDepartmentCode());  // Department Code
            insertStatement.setInt(5, course.getAvailableSeats());  // Available Seats
            insertStatement.setString(6, course.getPrerequisites());  // Prerequisites
            insertStatement.setString(7, course.getSemester());  // Semester
            insertStatement.setString(8, course.getTimeslot());  // Timeslot

            int rowsAffected = insertStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Could not add course to database.");
            return false;
        }
    }

    public boolean courseExists(String courseID) {
        Connection connection = DBConnection.getConnection();

        try {
            PreparedStatement checkStatement = connection.prepareStatement(
                    "SELECT COUNT(*) FROM Course WHERE id = ?");
            checkStatement.setString(1, courseID);

            var resultSet = checkStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Could not check if course exists.");
        }

        return false;
    }
}
