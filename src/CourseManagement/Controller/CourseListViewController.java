package CourseManagement.Controller;

import CourseManagement.Model.Course;
import CourseManagement.Model.Prerequisite;
import CourseManagement.Model.Professor;
import CourseManagement.View.CourseListView;
import CourseManagement.View.AddCourseUI;
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
        insertTestCourses();
        tableRowsCreated = false; // For UI
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

        // Setting up Professor for courses to interact with
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
                    "  `timeslot` VARCHAR(45) NULL," +
                    "  FOREIGN KEY (`professor_id`) REFERENCES Professor (`id`)," +
                    "  PRIMARY KEY (`id`))");
            System.out.println("Course table created, or already exists!");

            // Setting up Prereqs for courses to interact with
            prereqManager.initializeCoursePrerequisites();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Could not create course table");
        }
    }

    public void displayCourseList(String... deptCode) {  // This is just a way to add optional filtering without making 2 almost the same funcs
        Connection connection = DBConnection.getConnection();

        try {
            ResultSet resultSet;
            if (deptCode.length >= 1) {
                PreparedStatement displayFilteredCourseList = connection.prepareStatement("SELECT * FROM Course WHERE department_code = (?)");
                displayFilteredCourseList.setString(1, deptCode[0]);
                resultSet = displayFilteredCourseList.executeQuery();
                System.out.println("Displaying Course Filtered List: " + deptCode[0]);
            } else {
                PreparedStatement displayCourseList = connection.prepareStatement("SELECT * FROM Course");
                resultSet = displayCourseList.executeQuery();
                System.out.println("Displaying Course List...");
            }

            DefaultTableModel tableModel = (DefaultTableModel) view.getTable().getModel();
            tableModel.setRowCount(0);

            // Checking if columns already exist or not
            if (!tableRowsCreated) {
                tableModel.addColumn("Course Code");
                tableModel.addColumn("Course Name");
                tableModel.addColumn("Instructor");
                tableModel.addColumn("Department");
                tableModel.addColumn("Credits");
                //tableModel.addColumn("Timeslot");
                tableRowsCreated = true;
            }

            Object[] rowdata = new Object[6];

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Course table is empty.");
            }

            while (resultSet.next()) {
                try {
                    Course course = new Course(
                            resultSet.getString("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("credits"),
                            resultSet.getString("department_code"),
                            resultSet.getInt("seats"),
                            resultSet.getString("professor_id"),
                            resultSet.getString("prerequisites"),
                            resultSet.getString("semester"),
                            resultSet.getString("timeslot")
                    );
                    // Add course to table
                    rowdata[0] = course.getCourseID();
                    rowdata[1] = course.getCourseName();
                    rowdata[2] = course.getProfessor();
                    rowdata[3] = course.getDepartmentCode();
                    rowdata[4] = course.getCredits();
                    rowdata[5] = course.getTimeslot();
                    tableModel.addRow(rowdata);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Could not display course list.");
        }
    }

    public void insertTestCourses(){
        Connection connection = DBConnection.getConnection();
        try {
            Professor testProfessor1 = new Professor("JMD123", "John Doe", "IST");
            Professor testProfessor2 = new Professor("JAD123", "Jane Doe", "MATH");
            profManager.addProfessorToRoster(testProfessor1);
            profManager.addProfessorToRoster(testProfessor2);
            PreparedStatement addTestCourse1 = connection.prepareStatement(
                    "INSERT IGNORE INTO Course (id, name, credits, department_code, seats, professor_id, prerequisites, semester) " +
                            "VALUES ('IST412', 'Complex Sys', 3, 'IST', 20, 'JMD123', '', 'Spring 2025')");
            addTestCourse1.executeUpdate();

            //Course 2
            PreparedStatement addTestCourse2 = connection.prepareStatement(
                    "INSERT IGNORE INTO Course (id, name, credits, department_code, seats, professor_id, prerequisites, semester) " +
                            "VALUES ('IST512', 'Complex Sys II', 3, 'IST', 30, 'JMD123', 'IST412', 'Spring 2025')");
            addTestCourse2.executeUpdate();
            Prerequisite testPrereq1 = new Prerequisite("IST512", "IST412");
            prereqManager.addPrerequisites(testPrereq1);

            //Course 3
            PreparedStatement addTestCourse3 = connection.prepareStatement(
                    "INSERT IGNORE INTO Course (id, name, credits, department_code, seats, professor_id, prerequisites, semester) " +
                            "VALUES ('IST411', 'Adv Web Dev', 3, 'IST', 40, 'JMD123', '', 'Spring 2025')");
            addTestCourse3.executeUpdate();

            //Course 4
            PreparedStatement addTestCourse4 = connection.prepareStatement(
                    "INSERT IGNORE INTO Course (id, name, credits, department_code, seats, professor_id, prerequisites, semester) " +
                            "VALUES ('MATH 360', 'Discrete Math', 4, 'MATH', 50, 'JAD123', '', 'Spring 2025')");
            addTestCourse4.executeUpdate();

            System.out.println("Test course added");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not add course.");
        }
    }
}
