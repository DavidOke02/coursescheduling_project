package AdvisorApproval.Model;

// Model: Represents an approval request submitted by a student
public class ApprovalRequest {
    private String requestID;
    private String studentID;
    private String courseID;
    private String status;

    /**
     * Default constructor for ApprovalRequest.
     */
    public ApprovalRequest() {
        this.requestID = "0001";
        this.studentID = "012345678";
        this.courseID = "IST101";
        this.status = "Pending";
    }

    /**
     * Returns the request ID.
     */
    public String getRequestID() {
        return requestID;
    }

    /**
     * Returns the student ID associated with the request.
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * Returns the course ID related to the request.
     */
    public String getCourseID() {
        return courseID;
    }

    /**
     * Returns the status of the approval request.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the approval request.
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
