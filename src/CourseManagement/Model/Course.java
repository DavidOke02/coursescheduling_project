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

    /**
     * Default constructor initializing course with default values
     */
    public Course() {
        this.courseID = "IST101";
        this.courseName = "Introduction to Technology";
        this.credits = 3;
        this.departmentCode = "IST";
        this.availableSeats = 30;
    }

    public Course(String courseName, int credits) {
        this.courseID = "IST101";
        this.courseName = "Introduction to Technology";
        this.credits = 3;
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
}

