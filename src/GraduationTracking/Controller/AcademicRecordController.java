package GraduationTracking.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import GraduationTracking.Model.StudentAcademicRecord;
import db.DBConnection;

public class AcademicRecordController {
    //create table (student_id - varchar(10), course_id - (Varchar(45), gpa - (double(3,2), status - varchar(1))

    public AcademicRecordController() {
        System.out.println("AcademicRecordController started!");
        createAcademicRecordTable();
    }

    public void createAcademicRecordTable() {
        Connection connection = DBConnection.getConnection();
        try {
            Statement createTable = connection.createStatement();
            createTable.executeUpdate("CREATE TABLE IF NOT EXISTS academic_record (" +
                    "entry_id INT NOT NULL AUTO_INCREMENT," +
                    "student_id varchar(10) NOT NULL," +
                    "course_id varchar(45) NOT NULL," +
                    "gpa double(3,2)," +
                    "credits int," +
                    "status varchar(1)," +
                    "PRIMARY KEY(entry_id)" +
                    ")");
            System.out.println("Academic record table created, or already exists!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Could not create academic record table.");
        }
    }

    //displayRecordTable(String studentID) (select * from academic_record where student_id = ?)
    public List<StudentAcademicRecord> displayRecordTable(String studentID) {
        System.out.println("Displaying academic records for student: " + studentID);
        Connection connection = DBConnection.getConnection();
        List<StudentAcademicRecord> records = new ArrayList<>();

        try {
            PreparedStatement displayRecords = connection.prepareStatement(
                    "SELECT * FROM academic_record WHERE student_id = ?");
            displayRecords.setString(1, studentID);
            ResultSet resultSet = displayRecords.executeQuery();

            //Getting total credits
            PreparedStatement getCompletedCredits = connection.prepareStatement(
                    "SELECT SUM(credits) FROM academic_record WHERE student_id = ?"
            );
            getCompletedCredits.setString(1, studentID);
            ResultSet resultGetTotalCredits = getCompletedCredits.executeQuery();
            int totalCredits = 0;
            if (resultGetTotalCredits.next()) {
                totalCredits = resultGetTotalCredits.getInt(1);
            }

            //Getting current gpa
            PreparedStatement getAverageGPA = connection.prepareStatement(
                    "SELECT AVG(gpa) FROM academic_record WHERE student_id = ?"
            );
            getAverageGPA.setString(1, studentID);
            ResultSet resultGetAverageGPA = getAverageGPA.executeQuery();
            double avgGPA = 0;
            if (resultGetAverageGPA.next()) {
                totalCredits = resultGetAverageGPA.getInt(1);
            }

            //Get course-gpa list
            PreparedStatement getListOfGrades = connection.prepareStatement("" +
                    "SELECT * FROM academic_record WHERE student_id = ?");
            getListOfGrades.setString(1, studentID);
            ResultSet resultGetTotalGrades = getListOfGrades.executeQuery();
            Map<String, Double> grades = new HashMap<>();
            while (resultGetTotalGrades.next()) {
                grades.put(resultGetTotalGrades.getString("course_id"), resultGetTotalGrades.getDouble("gpa"));
            }

            while (resultSet.next()) {
                StudentAcademicRecord record = new StudentAcademicRecord(
                        resultSet.getString("student_id"),
                        totalCredits,
                        "IST",
                        avgGPA,
                        grades
                );
                records.add(record);
            }

            if (records.isEmpty()) {
                System.out.println("No academic records found for student ID: " + studentID);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Could not display academic records.");
        }

        return records;
    }
}
    //create table (student_id - varchar(10), course_id - (Varchar(45), gpa - (double(3,2), status - varchar(1))

    //displayRecordTable(String studentID) (select * from academic_record where student_id = ?)
