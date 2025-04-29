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

    public void addCourseToSchedule (String studentID, Course course) {
        System.out.println("Attempting to add to schedule");
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement addCourse = connection.prepareStatement(
                    "INSERT INTO Schedule (student_id, course_id, registration_status, waitlist_status) " +
                            "VALUES (?, ?, ?, ?)");
            addCourse.setString(1, studentID);
            addCourse.setString(2, course.getCourseID());
            addCourse.setString(3, "N"); //Means in enrollment cart while 'Y" means enrolled
            addCourse.setString(4, "O"); //O for open, Q for queued, F for full
            addCourse.executeUpdate();
            System.out.println("Course added to schedule!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not add course.");
        }
    }
}
