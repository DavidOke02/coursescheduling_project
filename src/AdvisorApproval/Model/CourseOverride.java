package AdvisorApproval.Model;

// Model: Represents a course override request
public class CourseOverride {
    private String overrideID;
    private String studentID;
    private String courseID;
    private String reason;

    /**
     * Default constructor for CourseOverride.
     */
    public CourseOverride() {
        this.overrideID = "O0001";
        this.studentID = "012345678";
        this.courseID = "IST101";
        this.reason = "Prerequisite not met";
    }

    /**
     * Returns the override ID.
     */
    public String getOverrideID() {
        return overrideID;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getCourseID() {

        return courseID;
    }

    public String getReason() {
        return reason;
    }
}