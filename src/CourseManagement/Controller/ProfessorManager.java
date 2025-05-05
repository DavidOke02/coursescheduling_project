package CourseManagement.Controller;

import CourseManagement.Model.Course;
import CourseManagement.Model.Professor;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Controller for assigning professors to courses
 */
public class ProfessorManager {
    /**
     * Assign a professor to a course.
     */

    public ProfessorManager() {

    }

    public void initializeCourseProfessors(){
        Connection connection = DBConnection.getConnection();
        try {
            Statement createProfessorTable = connection.createStatement();
            createProfessorTable.executeUpdate("CREATE TABLE IF NOT EXISTS Professor (" +
                    "  `id` VARCHAR(10) NOT NULL," +
                    "  `name` VARCHAR(45) NULL," +
                    "  `department` VARCHAR(45) NULL," +
                    "  PRIMARY KEY (`id`))");
            System.out.println("Professor table created, or already exists!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println("Could not create professor table");
        }
    }

    public void addProfessorToRoster(Professor professor){
        Connection connection = DBConnection.getConnection();

        try{
            PreparedStatement addProfessor = connection.prepareStatement("INSERT IGNORE INTO Professor (id, name, department) VALUES (?,?,?)");
            addProfessor.setString(1, professor.getProfessorID());
            addProfessor.setString(2, professor.getName());
            addProfessor.setString(3, professor.getDepartment());
            addProfessor.executeUpdate();
            System.out.println("Added Professor to roster.");
        } catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println("Could not add professor");
        }
    }

}

