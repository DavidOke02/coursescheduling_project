package CourseManagement.Controller;

import CourseManagement.Model.Course;
import CourseManagement.View.AddCourseUI;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddCourseController {
    private AddCourseUI view;

    public AddCourseController() {
        System.out.println("AddCourseController started!");
    }

    public AddCourseController(AddCourseUI view){
        System.out.println("AddCourseController started!");
        this.view = view;
    }

    // Add course
    public void addCourse(Course course) {
        System.out.println("Attempting to add course");
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement addCourse = connection.prepareStatement(
                    "INSERT IGNORE INTO Course (id, name, credits, department_code, seats, professor_id, prerequisites, semester, timeslot) " +  // Include timeslot column
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            addCourse.setString(1, course.getCourseID());
            addCourse.setString(2, course.getCourseName());
            addCourse.setInt(3, course.getCredits());
            addCourse.setString(4, course.getDepartmentCode());
            addCourse.setInt(5, course.getAvailableSeats());
            addCourse.setString(6, course.getProfessor());
            addCourse.setString(7, course.getPrerequisites());
            addCourse.setString(8, course.getSemesterOffered());
            addCourse.setString(9, course.getTimeslot());

            addCourse.executeUpdate();
            System.out.println("Course added");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not add course.");
        }
    }
}
