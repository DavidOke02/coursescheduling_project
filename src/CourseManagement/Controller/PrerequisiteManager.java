package CourseManagement.Controller;

import CourseManagement.Model.Course;
import CourseManagement.Model.Prerequisite;
import CourseManagement.Model.Professor;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Controller for managing prerequisites
 */
public class PrerequisiteManager {

    public PrerequisiteManager() {

    }

    /**
     * Add a prerequisite to a course
     */
    public void initializeCoursePrerequisites(){
        Connection connection = DBConnection.getConnection();
        try {
            Statement createProfessorTable = connection.createStatement();
            createProfessorTable.executeUpdate("CREATE TABLE IF NOT EXISTS Prerequisites (" +
                    "  `course_id` VARCHAR(10) NOT NULL," +
                    "  `prerequisite_id` VARCHAR(10) NOT NULL," +
                    "  FOREIGN KEY (`course_id`) REFERENCES Course (`id`)," +
                    "  FOREIGN KEY (`prerequisite_id`) REFERENCES Course (`id`),"+
                    "  PRIMARY KEY (`course_id`, `prerequisite_id`))");
            System.out.println("Prerequisite table created, or already exists!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println("Could not create prerequisite table");
        }
    }

    public void addPrerequisites(Prerequisite prerequisite){
        Connection connection = DBConnection.getConnection();

        try{
            PreparedStatement addProfessor = connection.prepareStatement("INSERT IGNORE INTO Prerequisites (course_id, prerequisite_id) VALUES (?,?)");
            addProfessor.setString(1, prerequisite.getCourseID());
            addProfessor.setString(2, prerequisite.getPrerequisiteCourseID());
            addProfessor.executeUpdate();
            System.out.println("Added Prerequisite to course.");
        } catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println("Could not add prerequisite to course");
        }
    }

}

