package Scheduling.Model;

/*
 * Represents a course with a unique ID, name, and credit count.
 */
public class CustomCourse extends CourseManagement.Model.Course {
    private String customCourseID;
    private String courseName;

    /**
     * Default constructor initializing course with default values.
     */
    public CustomCourse() {
        super("Introduction to Technology", 3);
        this.customCourseID = "IST101";
    }

    /**
     * Returns the course ID.
     */
    public String getCustomCourseID() {
        return customCourseID;
    }

    /**
     * Sets the course name.
     */
    public void setCourseName(String newCourseName) {
        this.courseName = newCourseName;
    }

    /**
     * Returns the course name.
     */
    public String getCourseName() {
        return courseName;
    }
}

