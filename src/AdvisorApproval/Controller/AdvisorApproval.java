package AdvisorApproval.Controller;

import AdvisorApproval.Model.ApprovalRequest;

// Controller: Manages advisor approvals
public class AdvisorApproval {
    /**
     * Approves a student's request.
     */
    public boolean approveRequest(ApprovalRequest request) {
        if (request.getStatus().equals("Pending")) {
            request.setStatus("Approved");
            System.out.println("Request " + request.getRequestID() + " approved.");
            return true;
        } else {
            System.out.println("Request " + request.getRequestID() + " cannot be approved.");
            return false;
        }
    }

}

