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
    private Map<String, String> completedCourses; //Will hold course ID and name


    /**
     * Default constructor initializing a student's record.
     */
    public StudentAcademicRecord(String studentID) {
        this.studentID = studentID;
        this.completedCredits = 0;
        this.degree = "Computer Science";
        this.currentGPA = 3.5;
        this.completedCourses = new HashMap<>();
        this.completedCourses.put("CS101", "A");
        this.completedCourses.put("CS201", "B+");
        this.completedCourses.put("MATH101", "A-");
        this.completedCourses.put("MATH201", "B");

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

    public Map<String, String> getCompletedCourses() {
        return completedCourses;
    }

    public void addCompletedCourse(String courseID, String grade) {
        this.completedCourses.put(courseID, grade);
    }

    public boolean hasCourseCompleted(String courseID) {
        return this.completedCourses.containsKey(courseID);
    }

    public String getCourseGrade(String courseID) {
        return this.completedCourses.get(courseID);
    }


}

