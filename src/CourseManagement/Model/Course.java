package CourseManagement.Model;

/**
 * A course with basic information
 */
public class Course {
    private String courseID;
    private String courseName;
    private int credits;
    private String departmentCode;
    private int availableSeats;
    private String professor;
    private String prerequisites;
    private String semesterOffered;

    /**
     * Default constructor initializing course with default values
     */
    public Course() {

    }

    public Course(String courseName, int credits) {
        this.courseName = courseName;
        this.credits = credits;
    }

    public Course(String id, String courseName, int credits, String departmentCode, int availableSeats, String professor, String prerequisites, String semesterOffered) {
        this.courseID = id;
        this.courseName = courseName;
        this.credits = credits;
        this.departmentCode = departmentCode;
        this.availableSeats = availableSeats;
        this.professor = professor;
        this.prerequisites = prerequisites;
        this.semesterOffered = semesterOffered;
    }

    // Getters and setters
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

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    public String getSemesterOffered() {
        return semesterOffered;
    }

    public void setSemesterOffered(String semesterOffered) {
        this.semesterOffered = semesterOffered;
    }
}
