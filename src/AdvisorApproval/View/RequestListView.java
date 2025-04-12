package AdvisorApproval.View;

import AdvisorApproval.Model.ApprovalRequest;

import java.util.List;

// View: Displays a list of approval requests
public class RequestListView {
    /**
     * Displays all approval requests.
     */
    public void showRequests(List<ApprovalRequest> requests) {
        System.out.println("Approval Requests:");
        for (ApprovalRequest req : requests) {
            System.out.println("Request ID: " + req.getRequestID() +
                    ", Student ID: " + req.getStudentID() +
                    ", Course: " + req.getCourseID() +
                    ", Status: " + req.getStatus());
        }
    }

}

