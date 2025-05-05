package GraduationTracking.Controller;

import CourseManagement.Model.Course;
import GraduationTracking.View.GraduationHomeView;
import db.DBConnection;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GraduationHomeViewController {
    //SQL to pull....
    //Method to get courses taken (SELECT * FROM academic_record where student_id = ? and status = ?)
    //Method to get what's in progress (SELECT * FROM academic_record where student_id = ? and status = ?)
    //Method to get what's left (SELECT * FROM degree_requirements where course_id not in (select course_id from academic_record);
    private GraduationHomeView view;
    private boolean tableRowsCreated;

    public GraduationHomeViewController(GraduationHomeView view) {
        this.view = view;
        this.tableRowsCreated = false;
        view.getDegreeProgressBar().setMaximum(5);
        view.getDegreeProgressBar().setValue(2);
    }

    public void displayTables(String studentID){
        Connection connection = DBConnection.getConnection();

        try {
            ResultSet resultSet;
            PreparedStatement displayCourseList = connection.prepareStatement("SELECT * FROM academic_record where student_id = ?");
                displayCourseList.setString(1, studentID);
                resultSet = displayCourseList.executeQuery();
                System.out.println("Displaying Course List...");

            DefaultTableModel ipTableModel = (DefaultTableModel) view.getInProgressTable().getModel();;
            ipTableModel.setRowCount(0);  // Resetting table rows before adding new data

            DefaultTableModel completedTableModel = (DefaultTableModel) view.getCoursesCompletedTable().getModel();;
            completedTableModel.setRowCount(0);

            // Initialize columns if not already created
            if (!tableRowsCreated) {
                ipTableModel.addColumn("Course ID (In-Progress)");
                completedTableModel.addColumn("Course ID (Completed)");
                tableRowsCreated = true;
            }

            Object[] rowdata = new Object[1];

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Course table is empty.");
            }

            while (resultSet.next()) {
                try {
                    // Add course to table
                    rowdata[0] = resultSet.getString("course_id");
                    if (resultSet.getString("status").equals("I")){
                        ipTableModel.addRow(rowdata);
                    }
                    else {
                        completedTableModel.addRow(rowdata);
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Could not display course tables.");
        }
    }

    public void displayRemainingTables(String studentID){
        Connection connection = DBConnection.getConnection();

        try {
            ResultSet resultSet;
            PreparedStatement displayCourseList = connection.prepareStatement("SELECT * FROM degree_requirements where degree_id = ? and course_id not in (select course_id from academic_record where student_id = ?);");
            displayCourseList.setInt(1, 1);
            displayCourseList.setString(2, studentID);
            resultSet = displayCourseList.executeQuery();
            System.out.println("Displaying Course List...");

            DefaultTableModel remainingTableModel = (DefaultTableModel) view.getCoursesRemainingTable().getModel();;
            remainingTableModel.setRowCount(0);

            // Initialize columns if not already created
            if (tableRowsCreated) {
                remainingTableModel.addColumn("Course ID (Remaining)");
                tableRowsCreated = true;
            }

            Object[] rowdata = new Object[1];

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Course table is empty.");
            }

            while (resultSet.next()) {
                try {
                    // Add course to table
                    rowdata[0] = resultSet.getString("course_id");
                    remainingTableModel.addRow(rowdata);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Could not display remaining course table.");
        }
    }


}
