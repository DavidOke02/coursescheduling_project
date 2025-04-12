package GraduationTracking.Model;

import java.util.HashMap;
import java.util.Map;

/*
 * Model: Represents a degree plan mapping required courses.
 */
public class DegreePlan {
    private String degreeName;

    /**
     * Default constructor for a degree plan.
     */
    private String studentID;
    private String degree;
    private int plannedCredits;
    private Map<String, String> plannedCourses; // CourseID -> Semester

    public DegreePlan() {
        this.studentID = "012345678";
        this.degree = "Computer Science";
        this.plannedCredits = 120;
        this.plannedCourses = new HashMap<>();
        this.plannedCourses.put("CS301", "Fall 2025");
        this.plannedCourses.put("CS401", "Spring 2026");
        this.plannedCourses.put("CS450", "Fall 2025");
        this.plannedCourses.put("CS490", "Spring 2026");
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    /**
     * Returns the degree name.
     * @return String degree name.
     */
    public String getDegree() {
        return degreeName;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public int getPlannedCredits() {
        return plannedCredits;
    }

    public void setPlannedCredits(int plannedCredits) {
        this.plannedCredits = plannedCredits;
    }

    public Map<String, String> getPlannedCourses() {
        return plannedCourses;
    }

    public void addPlannedCourse(String courseID, String semester) {
        this.plannedCourses.put(courseID, semester);
    }

    public boolean isCoursePlanned(String courseID) {
        return this.plannedCourses.containsKey(courseID);
    }

    /**
     * Returns the required courses.
     * @return Map array of required courses.
     */
    public String getPlannedSemester(String courseID) {
        return this.plannedCourses.get(courseID);
    }

    public void removePlannedCourse(String courseID) {
        this.plannedCourses.remove(courseID);
    }
}

