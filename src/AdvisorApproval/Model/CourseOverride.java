package AdvisorApproval.Model;

public class CourseOverride {
    private String overrideID;
    private String studentID;
    private String courseID;
    private String reason;
    private String status;

    public CourseOverride(String overrideID, String studentID, String courseID, String reason, String status) {
        this.overrideID = overrideID;
        this.studentID = studentID;
        this.courseID = courseID;
        this.reason = reason;
        this.status = status;
    }
    public String getOverrideID() {
        return overrideID;
    }

    public void setOverrideID(String overrideID) {
        this.overrideID = overrideID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
