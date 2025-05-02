package CourseManagement.Controller;

import CourseManagement.Model.Course;
import CourseManagement.Model.Professor;
import CourseManagement.View.CourseListView;
import CourseManagement.View.AddCourseUI;
import CourseManagement.View.CourseListView;
import db.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class CourseListViewController {
    private CourseListView view;
    private boolean tableRowsCreated;
    ProfessorManager profManager;
    PrerequisiteManager prereqManager;

    public CourseListViewController() {
        System.out.println("Displaying Course List View...");
        this.profManager = new ProfessorManager();
        this.prereqManager = new PrerequisiteManager();
        createCourseTable();
        tableRowsCreated = false; //For UI
    }

    public void setView(CourseListView view) {
        this.view = view;
    }

    // Example methods
    public void handleAddCourseButton() {
        SwingUtilities.invokeLater(() -> new AddCourseUI());
    }

    public void createCourseTable(){
        Connection connection = DBConnection.getConnection();

        //Setting up Professor for courses to interact with
        profManager.initializeCourseProfessors();

        try {
            Statement createUserTable = connection.createStatement();
            createUserTable.executeUpdate("CREATE TABLE IF NOT EXISTS Course (" +
                    "  `id` VARCHAR(10) NOT NULL," +
                    "  `name` VARCHAR(45) NULL," +
                    "  `credits` INT NULL," +
                    "  `department_code` VARCHAR(45) NULL," +
                    "  `seats` INT NULL," +
                    "  `professor_id` VARCHAR(10) NULL," +
                    "  `prerequisites` VARCHAR(500) NULL," +
                    "  `semester` VARCHAR(45) NULL," +
                    "  FOREIGN KEY (`professor_id`) REFERENCES Professor (`id`)," +
                    "  PRIMARY KEY (`id`))");
            System.out.println("Course table created, or already exists!");

            //Setting up Prereqs for courses to interact with
            prereqManager.initializeCoursePrerequisites();

        }catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println("Could not create course table");
        }
    }

    public void displayCourseList(String... deptCode){ //This is just a way to add optional filtering without making 2 almost the same funcs
        Connection connection = DBConnection.getConnection();

        try{
            ResultSet resultSet;
            if (deptCode.length >= 1){
                PreparedStatement displayFilteredCourseList = connection.prepareStatement("SELECT * FROM Course where department_code = (?)");
                displayFilteredCourseList.setString(1, deptCode[0]);
                resultSet = displayFilteredCourseList.executeQuery();
                System.out.println("Displaying Course Filtered List: " + deptCode[0]);
            }

            else {
                PreparedStatement displayCourseList = connection.prepareStatement("SELECT * FROM Course");
                resultSet = displayCourseList.executeQuery();
                System.out.println("Displaying Course List...");
            }

            DefaultTableModel tableModel = (DefaultTableModel) view.getTable().getModel();
            tableModel.setRowCount(0);
            if (!tableRowsCreated){
                tableModel.addColumn("Course Code");
                tableModel.addColumn("Course Name");
                tableModel.addColumn("Instructor");
                tableModel.addColumn("Department");
                tableModel.addColumn("Credits");
                tableRowsCreated = true;
            }
            Object [] rowdata = new Object[5];

            if(!resultSet.isBeforeFirst()){
                System.out.println("User table is empty.");
            }

            while (resultSet.next()) {
                try {
                    Course course = new Course(resultSet.getString("id"), resultSet.getString("name"), resultSet.getInt("credits"), resultSet.getString("department_code"), resultSet.getInt("seats"), resultSet.getString("professor_id"), resultSet.getString("prerequisites"), resultSet.getString("semester"));
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

//
}
//public void insertTestCourses(){
////        Connection connection = DBConnection.getConnection();
////        try {
////            Professor testProfessor1 = new Professor();
////            profManager.addProfessorToRoster(testProfessor1);
////            PreparedStatement addCourse = connection.prepareStatement(
////                    "INSERT INTO Course (id, name, credits, department_code, seats, professor_id, prerequisites, semester) " +
////                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
////            addCourse.setString(1, course.getCourseID());
////            addCourse.setString(2, course.getCourseName());
////            addCourse.setInt(3, course.getCredits());
////            addCourse.setString(4, course.getDepartmentCode());
////            addCourse.setInt(5, course.getAvailableSeats());
////            addCourse.setString(6, course.getProfessor());
////            addCourse.setString(7, course.getPrerequisites());
////            addCourse.setString(8, course.getSemesterOffered());
////            addCourse.executeUpdate();
////            System.out.println("Course added");
////        } catch (SQLException e) {
////            e.printStackTrace();
////            System.out.println("Could not add course.");
////        }
////    }