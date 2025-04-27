package Scheduling.Controller;

import Scheduling.Model.CustomCourse;
import Scheduling.Model.LionPathCourse;
import Scheduling.Model.CourseAdapter;
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

    public void displayCourseDetails(String courseID) {
        CustomCourse course = getCourseDetails(courseID);
        if (course != null && detailView != null) {
            detailView.displayCourse(course);
        }
    }

    public void updateSearchResults(List<CustomCourse> courses) {
        if (searchView != null) {
            searchView.displaySearchResults(courses);
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

    public CustomCourse getCourseDetails(String courseID) {
        System.out.println("Attempting to view course with ID: " + courseID);
        Connection connection = DBConnection.getConnection();
        CustomCourse course = null;

        try {
            PreparedStatement viewCourse = connection.prepareStatement(
                    "SELECT * FROM Courses WHERE id = ?");
            viewCourse.setString(1, courseID);
            ResultSet resultSet = viewCourse.executeQuery();

            if (resultSet.next()) {
                LionPathCourse lionPathCourse = new LionPathCourse(
                        resultSet.getString("id"),
                        resultSet.getString("courseName"),
                        resultSet.getString("professor"),
                        resultSet.getInt("credits"),
                        resultSet.getString("departmentCode"),
                        resultSet.getInt("availableSeats"),
                        resultSet.getString("dateEnrolled")
                );

                course = new CourseAdapter(lionPathCourse);

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

    public List<CustomCourse> searchCoursesByName(String courseName) {
        System.out.println("Searching for courses containing: " + courseName);
        Connection connection = DBConnection.getConnection();
        List<CustomCourse> results = new ArrayList<>();

        try {
            PreparedStatement searchQuery = connection.prepareStatement(
                    "SELECT * FROM Courses WHERE courseName LIKE ?");
            searchQuery.setString(1, "%" + courseName + "%");
            ResultSet resultSet = searchQuery.executeQuery();

            while (resultSet.next()) {
                LionPathCourse lionPathCourse = new LionPathCourse(
                        resultSet.getString("id"),
                        resultSet.getString("courseName"),
                        resultSet.getString("professor"),
                        resultSet.getInt("credits"),
                        resultSet.getString("departmentCode"),
                        resultSet.getInt("availableSeats"),
                        resultSet.getString("dateEnrolled")
                );

                results.add(new CourseAdapter(lionPathCourse));
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
                    "SELECT * FROM Courses WHERE departmentCode = ?");
            searchQuery.setString(1, departmentCode);
            ResultSet resultSet = searchQuery.executeQuery();

            while (resultSet.next()) {
                LionPathCourse lionPathCourse = new LionPathCourse(
                        resultSet.getString("id"),
                        resultSet.getString("courseName"),
                        resultSet.getString("professor"),
                        resultSet.getInt("credits"),
                        resultSet.getString("departmentCode"),
                        resultSet.getInt("availableSeats"),
                        resultSet.getString("dateEnrolled")
                );

                results.add(new CourseAdapter(lionPathCourse));
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
                    "SELECT * FROM Courses WHERE professor LIKE ?");
            searchQuery.setString(1, "%" + instructorName + "%");
            ResultSet resultSet = searchQuery.executeQuery();

            while (resultSet.next()) {
                LionPathCourse lionPathCourse = new LionPathCourse(
                        resultSet.getString("id"),
                        resultSet.getString("courseName"),
                        resultSet.getString("professor"),
                        resultSet.getInt("credits"),
                        resultSet.getString("departmentCode"),
                        resultSet.getInt("availableSeats"),
                        resultSet.getString("dateEnrolled")
                );

                results.add(new CourseAdapter(lionPathCourse));
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
            PreparedStatement query = connection.prepareStatement("SELECT * FROM Courses");
            ResultSet resultSet = query.executeQuery();

            while (resultSet.next()) {
                LionPathCourse lionPathCourse = new LionPathCourse(
                        resultSet.getString("id"),
                        resultSet.getString("courseName"),
                        resultSet.getString("professor"),
                        resultSet.getInt("credits"),
                        resultSet.getString("departmentCode"),
                        resultSet.getInt("availableSeats"),
                        resultSet.getString("dateEnrolled")
                );

                allCourses.add(new CourseAdapter(lionPathCourse));
            }

            System.out.println("Retrieved " + allCourses.size() + " courses.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Could not retrieve all courses.");
        }

        return allCourses;
    }
}