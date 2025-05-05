package CourseManagement.Controller;

import CourseManagement.Model.Course;
import CourseManagement.View.CourseDetailView;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModifyCourseDetails {

    public ModifyCourseDetails() {
        System.out.println("ModifyCourseDetails started!");
    }

    // View a course
    public Course viewCourse(String courseId) {
        System.out.println("Attempting to view course with ID: " + courseId);
        Connection connection = DBConnection.getConnection();
        Course course = null;

        try {
            PreparedStatement viewCourse = connection.prepareStatement(
                    "SELECT * FROM Course WHERE id = ?");
            viewCourse.setString(1, courseId);
            ResultSet resultSet = viewCourse.executeQuery();

            if (resultSet.next()) {
                course = new Course(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("credits"),
                        resultSet.getString("department_code"),
                        resultSet.getInt("seats"),
                        resultSet.getString("professor_id"),
                        resultSet.getString("prerequisites"),
                        resultSet.getString("semester"),
                        resultSet.getString("timeslot")
                );
                System.out.println("Course found: " + course.getCourseName());
                System.out.println("ID: " + course.getCourseID());
                System.out.println("Name: " + course.getCourseName());
                System.out.println("Credits: " + course.getCredits());
                System.out.println("Department: " + course.getDepartmentCode());
                System.out.println("Available Seats: " + course.getAvailableSeats());
                System.out.println("Professor: " + course.getProfessor());
                System.out.println("Prerequisites: " + course.getPrerequisites());
                System.out.println("Semester: " + course.getSemesterOffered());
                System.out.println("Timeslot: " + course.getTimeslot());
            } else {
                System.out.println("No course found with ID: " + courseId);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Could not view course.");
        }

        return course;
    }

    // Update course
    public void updateCourse(Course course) {
        System.out.println("Attempting to update course...");
        Connection connection = DBConnection.getConnection();
        boolean success = false;

        try {
            PreparedStatement updateCourse = connection.prepareStatement(
                    "UPDATE Course SET name = ?, credits = ?, department_code = ?, " +
                            "seats = ?, professor_id = ?, prerequisites = ?, semester = ?, timeslot = ? " +
                            "WHERE id = ?");

            updateCourse.setString(1, course.getCourseName());
            updateCourse.setInt(2, course.getCredits());
            updateCourse.setString(3, course.getDepartmentCode());
            updateCourse.setInt(4, course.getAvailableSeats());
            updateCourse.setString(5, course.getProfessor());
            updateCourse.setString(6, course.getPrerequisites());
            updateCourse.setString(7, course.getSemesterOffered());
            updateCourse.setString(8, course.getTimeslot());
            updateCourse.setString(9, course.getCourseID());

            int rowsAffected = updateCourse.executeUpdate();
            success = (rowsAffected > 0);

            if (success) {
                System.out.println("Course updated successfully");
            } else {
                System.out.println("No course was updated - course ID might not exist");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Could not update course.");
        }
    }

    // Delete course
    public void deleteCourse(String courseId) {
        System.out.println("Attempting to delete course...");
        Connection connection = DBConnection.getConnection();
        boolean success = false;

        try {
            PreparedStatement deletePrereqs = connection.prepareStatement(
                    "DELETE FROM Prerequisites WHERE course_id = ? OR prerequisite_id = ?");
            deletePrereqs.setString(1, courseId);
            deletePrereqs.setString(2, courseId);
            deletePrereqs.executeUpdate();

            PreparedStatement deleteCourse = connection.prepareStatement(
                    "DELETE FROM Course WHERE id = ?");
            deleteCourse.setString(1, courseId);

            int rowsAffected = deleteCourse.executeUpdate();
            success = (rowsAffected > 0);

            if (success) {
                System.out.println("Course deleted successfully");
            } else {
                System.out.println("No course was deleted - course ID might not exist");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Could not delete course.");
        }
    }
}
