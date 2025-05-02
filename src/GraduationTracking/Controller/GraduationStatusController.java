package GraduationTracking.Controller;

import GraduationTracking.View.GraduationStatusView;
import db.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GraduationStatusController {
    //calculate differences between GraduationRequirements and StudentAcademicRecord
    private GraduationStatusView view;
    private List<String> completedCourses = new ArrayList<>();
    private List<String> requiredCourses = new ArrayList<>();

    public GraduationStatusController(GraduationStatusView view) {
        createDegreeRequirementsTable();
        this.view = view;
    }

    public void createDegreeRequirementsTable(){
        Connection connection = DBConnection.getConnection();
        try {
            Statement createTable = connection.createStatement();
            createTable.executeUpdate("CREATE TABLE IF NOT EXISTS degree_requirements (" +
                    "degree_id INT NOT NULL," +
                    "course_id varchar(45) NOT NULL," +
                    "PRIMARY KEY(degree_id, course_id)" +
                    ")");
            System.out.println("Degree requirement table created, or already exists!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Could not degree requirement record table.");
        }
    }

    public boolean calculateGradStatus(String studentID){
        getCompletedCourses(studentID);
        getRequiredCourses(studentID);
        for (String course: requiredCourses){
            if (completedCourses.contains(course)){
                return false;
            }
        }
        return false;
    }

    public void getCompletedCourses(String studentID){
        Connection connection = DBConnection.getConnection();

        try {
            PreparedStatement displayRecords = connection.prepareStatement(
                    "SELECT * FROM academic_record WHERE student_id = ?");
            displayRecords.setString(1, studentID);
            ResultSet resultSet = displayRecords.executeQuery();
            while (resultSet.next()) {
                String course = resultSet.getString("course_id");
                completedCourses.add(course);
            }
        } catch (SQLException e) {
            e.getMessage();
            System.out.println("Can't get completed courses.");
        }
    }

    public void getRequiredCourses(String studentID){
        Connection connection = DBConnection.getConnection();

        try {
            PreparedStatement getRequiredCourses = connection.prepareStatement(
                    "SELECT * FROM degree_requirements WHERE degree_id = ?"
            );
            getRequiredCourses.setString(1, studentID);
            ResultSet resultSet = getRequiredCourses.executeQuery();
            while (resultSet.next()) {
                String course = resultSet.getString("course_id");
                requiredCourses.add(course);
            }
        } catch (SQLException e) {
            e.getMessage();
            System.out.println("Can't get required courses.");
        }
    }
}
