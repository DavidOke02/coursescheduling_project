package Scheduling.Controller;

import Scheduling.View.StudentScheduleView;
import db.DBConnection;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

/*
 * Handles viewing the student's schedule.
 */
public class ScheduleViewer {
    /**
     * Displays the schedule for the student.
     */

    private StudentScheduleView view;
    private boolean tableRowsCreated;

    public ScheduleViewer(StudentScheduleView view) {
        createScheduleTable();
        this.view = view;
        tableRowsCreated = false;
    }

    public void createScheduleTable() {
        Connection connection = DBConnection.getConnection();
        try {
            Statement createUserTable = connection.createStatement();
            createUserTable.executeUpdate("CREATE TABLE IF NOT EXISTS Schedule (" +
                    "  `entry_id` INT NOT NULL AUTO_INCREMENT," +
                    "  `student_id` VARCHAR(10) NULL," +
                    "  `course_id` VARCHAR(45) NULL," +
                    "  `registration_status` VARCHAR(1) NULL DEFAULT 'N'," +
                    "  `waitlist_status` VARCHAR(1) NULL DEFAULT 'N'," +
                    "  FOREIGN KEY (`course_id`) REFERENCES Course (`id`)," +
                    "  PRIMARY KEY (`entry_id`))");
            System.out.println("Schedule table created, or already exists!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Could not create schedule table.");
        }
    }

    public void displayCourseList(String studentID) {
        Connection connection = DBConnection.getConnection();

        try {
            ResultSet resultSet;

            PreparedStatement displayCourseList = connection.prepareStatement(
                    "SELECT * FROM Schedule WHERE student_id = (?)");
            displayCourseList.setString(1, studentID);
            resultSet = displayCourseList.executeQuery();
            System.out.println("Displaying Course List...");

            // Cart Table Setup
            DefaultTableModel cartTableModel = (DefaultTableModel) view.getTable1().getModel();
            cartTableModel.setRowCount(0);

            // Enrollment Table Setup
            DefaultTableModel enrollmentTableModel = (DefaultTableModel) view.getTable2().getModel();
            enrollmentTableModel.setRowCount(0);

            if (!tableRowsCreated) {
                tableRowsCreated = true;
                cartTableModel.addColumn("Course ID");
                cartTableModel.addColumn("Registration Status");
                cartTableModel.addColumn("Waitlist Status");
                //cartTableModel.addColumn("Timeslot");  // Added column for timeslot

                enrollmentTableModel.addColumn("Course ID");
                enrollmentTableModel.addColumn("Registration Status");
                enrollmentTableModel.addColumn("Waitlist Status");
                //enrollmentTableModel.addColumn("Timeslot");  // Added column for timeslot
            }

            Object[] rowdata = new Object[3];

            if (!resultSet.isBeforeFirst()) {
                System.out.println("User table is empty.");
            }

            while (resultSet.next()) {
                try {
                    rowdata[0] = resultSet.getString("course_id");
                    rowdata[1] = resultSet.getString("registration_status");
                    rowdata[2] = resultSet.getString("waitlist_status");
                    //rowdata[3] = resultSet.getString("timeslot");  // Timeslot for each course

                    if (rowdata[1].toString().equals("Y")) {
                        enrollmentTableModel.addRow(rowdata);
                    } else {
                        cartTableModel.addRow(rowdata);
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Could not display course list.");
        }
    }

    public void moveToEnrollment(String studentID, String courseID) {
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement enrollSelectedCourse = connection.prepareStatement(
                    "UPDATE Schedule SET registration_status = 'Y' WHERE student_id = (?) AND course_id = (?)");
            enrollSelectedCourse.setString(1, studentID);
            enrollSelectedCourse.setString(2, courseID);
            enrollSelectedCourse.executeUpdate();
            System.out.println("Updating Schedule...");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Could not enroll in course..");
        }
    }


}
