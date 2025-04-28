package Scheduling.Model;

import java.sql.Date;
import java.time.LocalDate;

public class CustomCourse {
    private String courseID;
    private String courseName;
    private String instructor;
    private int credits;
    private String departmentCode;
    private int availableSeats;
    private Date dateAdded;

    public CustomCourse() {
        this.dateAdded = Date.valueOf(LocalDate.now());
    }

    public CustomCourse(String courseID, String courseName, String instructor, int credits, String departmentCode, int availableSeats) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.instructor = instructor;
        this.credits = credits;
        this.departmentCode = departmentCode;
        this.availableSeats = availableSeats;
        this.dateAdded = Date.valueOf(LocalDate.now());
    }

    // Getters and Setters (same as you had before)

    @Override
    public String toString() {
        return courseID + " - " + courseName;
    }
}