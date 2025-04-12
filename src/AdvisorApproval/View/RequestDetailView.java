package AdvisorApproval.View;

import AdvisorApproval.Model.ApprovalRequest;

// View: Displays details of a specific request
public class RequestDetailView {
    /**
     * Displays detailed information about a request.
     */
    public void showRequestDetails(ApprovalRequest request) {
        System.out.println("Request Details:");
        System.out.println("Request ID: " + request.getRequestID());
        System.out.println("Student ID: " + request.getStudentID());
        System.out.println("Course: " + request.getCourseID());
        System.out.println("Status: " + request.getStatus());
    }

}
