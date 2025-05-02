package GraduationTracking.Model;

import java.util.HashMap;
import java.util.Map;

/*
 * Model: Represents a student's academic record including courses taken.
 */
public class StudentAcademicRecord {
    private String studentID;
    private int completedCredits;
    private String degree;
    private double currentGPA;
    private Map<String, Double> completedCourses; //Will hold name and gpa


    /**
     * Default constructor initializing a student's record.
     */
    public StudentAcademicRecord(String studentID, int completedCredits, String degree, double currentGPA, Map<String, Double> completedCourses) {
        this.studentID = studentID;
        this.completedCredits = completedCredits;
        this.degree = degree;
        this.currentGPA = currentGPA;
        this.completedCourses = new HashMap<>();
    }

    /**
     * Returns completed credits.
     * @return int number of completed credits.
     */
    public int getCompletedCredits() {
        return completedCredits;
    }

    public void setCompletedCredits(int completedCredits) {
        this.completedCredits = completedCredits;
    }

    /**
     * Returns student ID.
     * @return String student ID.
     */
    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID){
        this.studentID = studentID;

    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public double getCurrentGPA() {
        return currentGPA;
    }

    public void setCurrentGPA(double currentGPA) {
        this.currentGPA = currentGPA;
    }

    public Map<String, Double> getCompletedCourses() {
        return completedCourses;
    }

    public void addCompletedCourse(String courseID, Double gpa) {
        this.completedCourses.put(courseID, gpa);
    }

    public boolean hasCourseCompleted(String courseID) {
        return this.completedCourses.containsKey(courseID);
    }

    public Double getCourseGPA(String courseGPA) {
        return this.completedCourses.get(courseGPA);
    }


}

