package CourseManagement.Controller;

import CourseManagement.Model.Course;
import CourseManagement.View.CourseListView;
import CourseManagement.View.AddCourseUI;
import CourseManagement.View.CourseListView;
import db.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class CourseListViewController {
    CourseListView view;

    public CourseListViewController() {
        System.out.println("Displaying Course List View...");
        //CourseListView view = new CourseListView(this);
        createCourseTable();
    }

    public void setView(CourseListView view) {
        this.view = view;
    }

    // Example methods
    public void handleAddCourseButton() {
        SwingUtilities.invokeLater(() -> new AddCourseUI());
    }

    public void filterCoursesByDepartment(String deptCode) {
        System.out.println("Filtering for department: " + deptCode);
    }

    public void createCourseTable(){
        Connection connection = DBConnection.getConnection();

        try {
            Statement createUserTable = connection.createStatement();
            createUserTable.executeUpdate("CREATE TABLE IF NOT EXISTS Course (" +
                    "  `id` VARCHAR(10) NOT NULL," +
                    "  `name` VARCHAR(45) NULL," +
                    "  `credits` INT NULL," +
                    "  `departmentcode` VARCHAR(45) NULL," +
                    "  `seats` INT NULL," +
                    "  `professor` VARCHAR(45) NULL," +
                    "  `prerequisites` VARCHAR(500) NULL," +
                    "  `semester` VARCHAR(45) NULL," +
                    "  PRIMARY KEY (`id`))");
            System.out.println("Course table created, or already exists!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println("Could not create course table");
        }
    }

    public void displayCourseList(){
        Connection connection = DBConnection.getConnection();

        try{
            PreparedStatement displayCourseList = connection.prepareStatement("SELECT * FROM Course");
            ResultSet resultSet = displayCourseList.executeQuery();

            DefaultTableModel tableModel = (DefaultTableModel) view.getTable().getModel();
            tableModel.setRowCount(0);
            tableModel.addColumn("Course Code");
            tableModel.addColumn("Course Name");
            tableModel.addColumn("Instructor");
            tableModel.addColumn("Department");
            tableModel.addColumn("Credits");
            Object [] rowdata = new Object[5];

            if(!resultSet.isBeforeFirst()){
                System.out.println("User table is empty.");
            }

            while (resultSet.next()) {
                try {
                    Course course = new Course(resultSet.getString("id"), resultSet.getString("name"), resultSet.getInt("credits"), resultSet.getString("departmentcode"), resultSet.getInt("seats"), resultSet.getString("professor"), resultSet.getString("prerequisites"), resultSet.getString("semester"));
                    //Add course to table
                    rowdata[0] = course.getCourseID();
                    rowdata[1] = course.getCourseName();
                    rowdata[2] = course.getProfessor();
                    rowdata[3] = course.getDepartmentCode();
                    rowdata[4] = course.getCredits();
                    tableModel.addRow(rowdata);
                }catch (SQLException e){
                    System.out.println(e.getMessage());
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Could not display course list.");
        }
    }
}
