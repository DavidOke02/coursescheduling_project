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
        createCourseTable();
    }
// making a table in the db
    public void createCourseTable() {
        Connection connection = DBConnection.getConnection();
        try {
            Statement createCourseTable = connection.createStatement();
            createCourseTable.executeUpdate("CREATE TABLE IF NOT EXISTS Course (" +
                    "  `id` INT NOT NULL AUTO_INCREMENT," +
                    "  `courseID` VARCHAR(45) NULL," +
                    "  `courseName` VARCHAR(100) NULL," +
                    "  `credits` INT NULL," +
                    "  `departmentCode` VARCHAR(45) NULL," +
                    "  `availableSeats` INT NULL," +
                    "  `professor` VARCHAR(100) NULL," +
                    "  `prerequisites` VARCHAR(255) NULL," +
                    "  `semesterOffered` VARCHAR(45) NULL," +
                    "  PRIMARY KEY (`id`))");
            System.out.println("Course table created, or already exists!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Could not create table: ");
        }
        }

// add course
public void addCourse(String courseID, String courseName, int credits, String departmentCode, int availableSeats, String professor, String prerequisites, String semesterOffered) {
    System.out.println("Attempting to add course");
    Connection connection = DBConnection.getConnection();
    try {
        PreparedStatement addCourse = connection.prepareStatement(
                "INSERT INTO Course (courseID, courseName, credits, departmentCode, availableSeats, professor, prerequisites, semesterOffered) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        addCourse.setString(1, courseID);
        addCourse.setString(2, courseName);
        addCourse.setInt(3, credits);
        addCourse.setString(4, departmentCode);
        addCourse.setInt(5, availableSeats);
        addCourse.setString(6, professor);
        addCourse.setString(7, prerequisites);
        addCourse.setString(8, semesterOffered);
        addCourse.executeUpdate();
        System.out.println("Course added");
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Could not add course.");
    }
    }
}

