package CourseManagement.Controller;

import CourseManagement.Model.Course;
import CourseManagement.View.CourseListView;
import db.DBConnection;

import java.sql.*;

public class CourseListViewController {
    CourseListView courseListView;

    public CourseListViewController() {
        System.out.println("Displaying Course List View...");
        //courseListView = new CourseListView(this);
        createCourseTable();
    }

    public void createCourseTable(){
        Connection connection = DBConnection.getConnection();

        try {
            Statement createUserTable = connection.createStatement();
            createUserTable.executeUpdate("CREATE TABLE IF NOT EXISTS Course (" +
                    "  `id` INT NOT NULL AUTO_INCREMENT," +
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

            if(!resultSet.isBeforeFirst()){
                System.out.println("User table is empty.");
            }

            while (resultSet.next()) {
                try {
                    Course course = new Course(resultSet.getString("id"), resultSet.getString("name"), resultSet.getInt("credits"), resultSet.getString("departmentcode"), resultSet.getInt("seats"), resultSet.getString("professor"), resultSet.getString("prerequisites"), resultSet.getString("semester"));
                    //Add course to table
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
