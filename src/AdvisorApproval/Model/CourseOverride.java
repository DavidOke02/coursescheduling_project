package AdvisorApproval.Model;

public class CourseOverride {
    private int overrideID;

    public int getOverrideID() {
        return overrideID;
    }

    public void setOverrideID(int overrideID) {
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    private String studentID;
    private String courseID;
    private String reason;
    private String status;
    private String comment;


    public CourseOverride(Integer overrideID, String studentID, String courseID, String reason, String status, String comment) {
        this.overrideID = overrideID;
        this.studentID = studentID;
        this.courseID = courseID;
        this.reason = reason;
        this.status = status;
        this.comment = comment;
    }

}
