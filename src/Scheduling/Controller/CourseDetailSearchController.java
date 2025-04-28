package Scheduling.Controller;

import Scheduling.Model.LionPathCourse;
import db.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CourseDetailSearchController {

    private Connection connection;

    public CourseDetailSearchController() {
        this.connection = DBConnection.getConnection();
    }

    // Method to add a course to the database
    public void addCourseToDB(LionPathCourse course) {
        try {
            String query = "INSERT INTO courses (courseID, courseTitle, instructorName, credits, departmentName, availableSeats, dateEnrolled) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, course.getCourseID());
            statement.setString(2, course.getCourseTitle());
            statement.setString(3, course.getInstructorName());
            statement.setInt(4, course.getCredits());
            statement.setString(5, course.getDepartmentName());
            statement.setInt(6, course.getAvailableSeats());
            statement.setString(7, course.getDateEnrolled());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LionPathCourse searchByCourseID(String courseID) {
        try {
            String query = "SELECT * FROM courses WHERE courseID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, courseID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new LionPathCourse(
                        resultSet.getString("courseID"),
                        resultSet.getString("courseTitle"),
                        resultSet.getString("instructorName"),
                        resultSet.getInt("credits"),
                        resultSet.getString("departmentName"),
                        resultSet.getInt("availableSeats"),
                        resultSet.getString("dateEnrolled")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<LionPathCourse> searchByDepartment(String departmentName) {
        List<LionPathCourse> courses = new ArrayList<>();
        try {
            String query = "SELECT * FROM courses WHERE departmentName = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, departmentName);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                courses.add(new LionPathCourse(
                        resultSet.getString("courseID"),
                        resultSet.getString("courseTitle"),
                        resultSet.getString("instructorName"),
                        resultSet.getInt("credits"),
                        resultSet.getString("departmentName"),
                        resultSet.getInt("availableSeats"),
                        resultSet.getString("dateEnrolled")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public List<LionPathCourse> searchByInstructor(String instructorName) {
        List<LionPathCourse> courses = new ArrayList<>();
        try {
            String query = "SELECT * FROM courses WHERE instructorName = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, instructorName);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                courses.add(new LionPathCourse(
                        resultSet.getString("courseID"),
                        resultSet.getString("courseTitle"),
                        resultSet.getString("instructorName"),
                        resultSet.getInt("credits"),
                        resultSet.getString("departmentName"),
                        resultSet.getInt("availableSeats"),
                        resultSet.getString("dateEnrolled")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public List<LionPathCourse> getAllCourses() {
        List<LionPathCourse> courses = new ArrayList<>();
        try {
            String query = "SELECT * FROM courses";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                courses.add(new LionPathCourse(
                        resultSet.getString("courseID"),
                        resultSet.getString("courseTitle"),
                        resultSet.getString("instructorName"),
                        resultSet.getInt("credits"),
                        resultSet.getString("departmentName"),
                        resultSet.getInt("availableSeats"),
                        resultSet.getString("dateEnrolled")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
}
