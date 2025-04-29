package Scheduling.Controller;

import CourseManagement.Model.Course;
import Scheduling.Model.CustomCourse;
import Scheduling.View.CourseDetailView;
import Scheduling.View.CourseSearchView;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDetailSearchController {
    private CourseDetailView detailView;
    private CourseSearchView searchView;

    public CourseDetailSearchController() {
        System.out.println("CourseDetailSearchController started!");
    }

    public void setDetailView(CourseDetailView detailView) {
        this.detailView = detailView;
    }

    public void setSearchView(CourseSearchView searchView) {
        this.searchView = searchView;
    }

    public Course displayCourseDetails(String courseID) {
        return getCourseDetails(courseID);
    }

    public void updateSearchResults(List<CustomCourse> courses) {
        if (searchView != null) {
            //searchView.displaySearchResults(courses);
        }
    }

    public void addToSchedule (String studentID, String courseID) {
        System.out.println("Attempting to add to schedule");
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement addCourse = connection.prepareStatement(
                    "INSERT INTO Schedule (student_id, course_id, registration_status, waitlist_status) " +
                            "VALUES (?, ?, ?, ?)");
            addCourse.setString(1, studentID);
            addCourse.setString(2, courseID);
            addCourse.setString(3, "N"); //Means in enrollment cart while 'Y" means enrolled
            addCourse.setString(4, "O"); //O for open, Q for queued, F for full
            addCourse.executeUpdate();
            System.out.println("Course added");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not add course.");
        }
    }

public void performSearch(String searchType, String searchTerm) {
    List<CustomCourse> results;

    switch (searchType.toLowerCase()) {
        case "name":
            results = searchCoursesByName(searchTerm);
            break;
        case "department":
            results = searchCoursesByDepartment(searchTerm);
            break;
        case "instructor":
            results = searchCoursesByInstructor(searchTerm);
            break;
        default:
            results = getAllCourses();
            break;
    }

    updateSearchResults(results);
}

public Course getCourseDetails(String courseID) {
    System.out.println("Attempting to view course with ID: " + courseID);
    Connection connection = DBConnection.getConnection();
    Course course = null;

    try {
        PreparedStatement viewCourse = connection.prepareStatement(
                "SELECT * FROM Course WHERE id = ?");
        viewCourse.setString(1, courseID);
        ResultSet resultSet = viewCourse.executeQuery();

        if (resultSet.next()) {
            course = new Course(
                    resultSet.getString("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("credits"),
                    resultSet.getString("department_code"),
                    resultSet.getInt("seats"),
                    resultSet.getString("professor_id"),
                    resultSet.getString("prerequisites"),
                    resultSet.getString("semester")
            );

            System.out.println("Course found: " + course.getCourseName());
        } else {
            System.out.println("No course found with ID: " + courseID);
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
        System.out.println("Could not view course details.");
    }

    return course;
}

public ArrayList<Course> searchCoursesByID(String courseName) {
    System.out.println("Searching for courses containing: " + courseName);
    Connection connection = DBConnection.getConnection();
    ArrayList<Course> results = new ArrayList<>();

    try {
        PreparedStatement searchQuery = connection.prepareStatement(
                "SELECT * FROM Course WHERE id LIKE ?");
        searchQuery.setString(1, "%" + courseName + "%");
        ResultSet resultSet = searchQuery.executeQuery();

        while (resultSet.next()) {
            results.add(new Course(
                    resultSet.getString("id"),
                    resultSet.getString("name"),
                    resultSet.getString("professor_id"),
                    resultSet.getInt("credits"),
                    resultSet.getString("department_code"),
                    resultSet.getInt("seats")
            ));
        }

        System.out.println("Found " + results.size() + " courses matching: " + courseName);
    } catch (SQLException e) {
        System.out.println(e.getMessage());
        System.out.println("Could not search courses by name.");
    }

    return results;
}

public List<CustomCourse> searchCoursesByName(String courseName) {
    System.out.println("Searching for courses containing: " + courseName);
    Connection connection = DBConnection.getConnection();
    List<CustomCourse> results = new ArrayList<>();

    try {
        PreparedStatement searchQuery = connection.prepareStatement(
                "SELECT * FROM Course WHERE name LIKE ?");
        searchQuery.setString(1, "%" + courseName + "%");
        ResultSet resultSet = searchQuery.executeQuery();

        while (resultSet.next()) {
            results.add(new CustomCourse(
                    resultSet.getString("id"),
                    resultSet.getString("name"),
                    resultSet.getString("professor_id"),
                    resultSet.getInt("credits"),
                    resultSet.getString("department_code"),
                    resultSet.getInt("seats")
            ));
        }

        System.out.println("Found " + results.size() + " courses matching: " + courseName);
    } catch (SQLException e) {
        System.out.println(e.getMessage());
        System.out.println("Could not search courses by name.");
    }

    return results;
}

public List<CustomCourse> searchCoursesByDepartment(String departmentCode) {
    System.out.println("Searching for courses in department: " + departmentCode);
    Connection connection = DBConnection.getConnection();
    List<CustomCourse> results = new ArrayList<>();

    try {
        PreparedStatement searchQuery = connection.prepareStatement(
                "SELECT * FROM Course WHERE department_code = ?");
        searchQuery.setString(1, departmentCode);
        ResultSet resultSet = searchQuery.executeQuery();

        while (resultSet.next()) {
            results.add(new CustomCourse(
                    resultSet.getString("id"),
                    resultSet.getString("name"),
                    resultSet.getString("professor_id"),
                    resultSet.getInt("credits"),
                    resultSet.getString("department_code"),
                    resultSet.getInt("seats")
            ));
        }

        System.out.println("Found " + results.size() + " courses in department: " + departmentCode);
    } catch (SQLException e) {
        System.out.println(e.getMessage());
        System.out.println("Could not search courses by department.");
    }

    return results;
}

public List<CustomCourse> searchCoursesByInstructor(String instructorName) {
    System.out.println("Searching for courses taught by: " + instructorName);
    Connection connection = DBConnection.getConnection();
    List<CustomCourse> results = new ArrayList<>();

    try {
        PreparedStatement searchQuery = connection.prepareStatement(
                "SELECT * FROM Course WHERE professor LIKE ?");
        searchQuery.setString(1, "%" + instructorName + "%");
        ResultSet resultSet = searchQuery.executeQuery();

        while (resultSet.next()) {
            results.add(new CustomCourse(
                    resultSet.getString("id"),
                    resultSet.getString("name"),
                    resultSet.getString("professor_id"),
                    resultSet.getInt("credits"),
                    resultSet.getString("department_code"),
                    resultSet.getInt("seats")
            ));
        }

        System.out.println("Found " + results.size() + " courses taught by: " + instructorName);
    } catch (SQLException e) {
        System.out.println(e.getMessage());
        System.out.println("Could not search courses by instructor.");
    }

    return results;
}

public List<CustomCourse> getAllCourses() {
    System.out.println("Retrieving all courses...");
    Connection connection = DBConnection.getConnection();
    List<CustomCourse> allCourses = new ArrayList<>();

    try {
        PreparedStatement query = connection.prepareStatement("SELECT * FROM Course");
        ResultSet resultSet = query.executeQuery();

        while (resultSet.next()) {
            allCourses.add(new CustomCourse(
                    resultSet.getString("id"),
                    resultSet.getString("name"),
                    resultSet.getString("professor_id"),
                    resultSet.getInt("credits"),
                    resultSet.getString("department_code"),
                    resultSet.getInt("availableSeats")
            ));
        }

        System.out.println("Retrieved " + allCourses.size() + " courses.");
    } catch (SQLException e) {
        System.out.println(e.getMessage());
        System.out.println("Could not retrieve all courses.");
    }

    return allCourses;
}
}