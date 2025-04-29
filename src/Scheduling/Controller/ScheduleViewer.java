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

    public ScheduleViewer(StudentScheduleView view) {
        createScheduleTable();
        this.view = view;
    }

    public void createScheduleTable(){
        Connection connection = DBConnection.getConnection();
        try {
            Statement createUserTable = connection.createStatement();
            createUserTable.executeUpdate("CREATE TABLE IF NOT EXISTS Schedule (" +
                    "  `entry_id` VARCHAR(10) NOT NULL," +
                    "  `student_id` VARCHAR(10) NULL," +
                    "  `course_id` VARCHAR(45) NULL," +
                    "  `registration_status` VARCHAR(1) NULL," +
                    "  `waitlist_status` VARCHAR(1) NULL," +
                    "  FOREIGN KEY (`course_id`) REFERENCES Course (`id`)," +
                    "  PRIMARY KEY (`entry_id`))");
            System.out.println("Course table created, or already exists!");

        }catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println("Could not create course table");
        }
    }


    public void displayCourseList(String studentID){ //This is just a way to add optional filtering without making 2 almost the same funcs
        Connection connection = DBConnection.getConnection();

        try{
            ResultSet resultSet;

            PreparedStatement displayCourseList = connection.prepareStatement("SELECT * FROM Schedule where student_id = (?)");
            displayCourseList.setString(1, studentID);
            resultSet = displayCourseList.executeQuery();
            System.out.println("Displaying Course List...");

            //Cart Table Setup
            DefaultTableModel cartTableModel = (DefaultTableModel) view.getTable1().getModel();
            cartTableModel.setRowCount(0);
            cartTableModel.addColumn("Course ID");
            cartTableModel.addColumn("Registration Status");
            cartTableModel.addColumn("Waitlist Status");

            //Enrollment Table setup
            DefaultTableModel enrollmentTableModel = (DefaultTableModel) view.getTable2().getModel();
            enrollmentTableModel.setRowCount(0);
            enrollmentTableModel.addColumn("Course ID");
            enrollmentTableModel.addColumn("Registration Status");
            enrollmentTableModel.addColumn("Waitlist Status");

            Object [] rowdata = new Object[3];

            if(!resultSet.isBeforeFirst()){
                System.out.println("User table is empty.");
            }

            while (resultSet.next()) {
                try {
                    //Course course = new Course(resultSet.getString("id"), resultSet.getString("name"), resultSet.getInt("credits"), resultSet.getString("department_code"), resultSet.getInt("seats"), resultSet.getString("professor_id"), resultSet.getString("prerequisites"), resultSet.getString("semester"));
                    //Add course to table
                    rowdata[0] = resultSet.getString("course_id");
                    rowdata[1] = resultSet.getString("registration_status");
                    rowdata[2] = resultSet.getString("waitlist_status");

                    if (rowdata[1].toString().equals("Y")){
                        enrollmentTableModel.addRow(rowdata);
                    }
                    else {cartTableModel.addRow(rowdata);}
                }catch (SQLException e){
                    System.out.println(e.getMessage());
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Could not display course list.");
        }
    }

    //ScheduleUpdate and WithdrawFromCourse will now just be methods in here
}

/*
 * Handles student login authentication.
 */
