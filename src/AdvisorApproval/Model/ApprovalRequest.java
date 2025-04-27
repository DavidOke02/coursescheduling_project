package AdvisorApproval.Model;

public class ApprovalRequest {
    private int requestId;
    private String studentId;
    private String courseId;
    private String comment;
    private String advisorComment;
    private String status;
    private String term;


    public ApprovalRequest(Integer requestId, String studentId, String courseId, String comment, String advisorComment, String status, String term) {
        if (requestId == null) {
            this.requestId = (int) (System.currentTimeMillis() % Integer.MAX_VALUE); // generate unique ID
        } else {
            this.requestId = requestId;
        }
        this.studentId = studentId;
        this.courseId = courseId;
        this.comment = comment;
        this.advisorComment = advisorComment;
        this.status = status;
        this.term = term;
    }



    // Getters and Setters
    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAdvisorComment() {
        return advisorComment;
    }

    public void setAdvisorComment(String advisorComment) {
        this.advisorComment = advisorComment;
    }
}
