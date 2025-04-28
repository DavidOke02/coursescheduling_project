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
                                  int credits, String departmentCode, int availableSeats) {

        System.out.println("Attempting to register new course: " + courseID + " - " + courseName);

        CustomCourse newCourse = new CustomCourse(
                courseID,
                courseName,
                instructor,
                credits,
                departmentCode,
                availableSeats
        );

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
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO Courses (id, courseName, professor, credits, department_code, availableSeats, dateEnrolled) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)");

            insertStatement.setString(1, course.getCourseID());
            insertStatement.setString(2, course.getCourseName());
            insertStatement.setString(3, course.getInstructor());
            insertStatement.setInt(4, course.getCredits());
            insertStatement.setString(5, course.getDepartmentCode());
            insertStatement.setInt(6, course.getAvailableSeats());
            insertStatement.setDate(7, course.getDateAdded());

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
                    "SELECT COUNT(*) FROM Courses WHERE id = ?");
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
