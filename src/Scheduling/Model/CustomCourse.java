package Scheduling.Model;

public class CustomCourse {
    private String courseID;
    private String courseName;
    private String instructor;
    private int credits;
    private String departmentCode;
    private int availableSeats;
    private String prerequisites;
    private String semester;

    // Constructor with 8 parameters
    public CustomCourse(String courseID, String courseName, String instructor, int credits,
                        String departmentCode, int availableSeats, String prerequisites, String semester) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.instructor = instructor;
        this.credits = credits;
        this.departmentCode = departmentCode;
        this.availableSeats = availableSeats;
        this.prerequisites = prerequisites;
        this.semester = semester;
    }

    // Getters and Setters
    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
