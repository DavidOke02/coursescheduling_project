package Scheduling.Controller;

import CourseManagement.Model.Course;
import Scheduling.View.CourseDetailView;
import Scheduling.View.CourseSearchView;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDetailSearchController {
    private CourseDetailView detailView;
    private CourseSearchView searchView;

    public CourseDetailSearchController() {
        System.out.println("CourseDetailSearchController started!");
    }

    public void setDetailView(CourseDetailView detailView) {
        this.detailView = detailView;
    }

    public void setSearchView(CourseSearchView searchView) {
        this.searchView = searchView;
    }

    public Course displayCourseDetails(String courseID) {
        return getCourseDetails(courseID);
    }

    public Course getCourseDetails(String courseID) {
        System.out.println("Attempting to view course with ID: " + courseID);
        Course course = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement viewCourse = connection.prepareStatement(
                     "SELECT * FROM coursescheduling_db.Course WHERE id = ?")) {

            viewCourse.setString(1, courseID);
            try (ResultSet resultSet = viewCourse.executeQuery()) {
                if (resultSet.next()) {
                    course = new Course(
                            resultSet.getString("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("credits"),
                            resultSet.getString("department_code"),
                            resultSet.getInt("seats"),
                            resultSet.getString("professor_id"),
                            resultSet.getString("prerequisites"),
                            resultSet.getString("semester")
                    );
                    System.out.println("Course found: " + course.getCourseName());
                } else {
                    System.out.println("No course found with ID: " + courseID);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not view course details.");
        }

        return course;
    }

    // New method for searching courses by ID
    public List<Course> searchCoursesByID(String courseID) {
        System.out.println("Attempting to search for course with ID: " + courseID);
        List<Course> courses = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement searchCourse = connection.prepareStatement(
                     "SELECT * FROM coursescheduling_db.Course WHERE id LIKE ?")) {

            searchCourse.setString(1, "%" + courseID + "%");
            try (ResultSet resultSet = searchCourse.executeQuery()) {
                while (resultSet.next()) {
                    Course course = new Course(
                            resultSet.getString("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("credits"),
                            resultSet.getString("department_code"),
                            resultSet.getInt("seats"),
                            resultSet.getString("professor_id"),
                            resultSet.getString("prerequisites"),
                            resultSet.getString("semester")
                    );
                    courses.add(course);
                    System.out.println("Course found: " + course.getCourseName());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not search for courses.");
        }

        return courses;
    }

    public void addCourseToSchedule(String studentID, Course course) {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "INSERT INTO coursescheduling_db.Schedule (student_id, course_id) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, studentID);
                preparedStatement.setString(2, course.getCourseID());
                preparedStatement.executeUpdate();
                System.out.println("Course added to schedule for student: " + studentID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to add course to schedule.");
        }
    }
}
