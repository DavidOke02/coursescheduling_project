package AdvisorApproval.Controller;

import AdvisorApproval.Model.ApprovalRequest;
import AdvisorApproval.Model.CourseOverride;

// Controller: Manages advisor approvals
public class AdvisorApproval {

    /**
     * Approves a student's general request.
     */
    public boolean approveRequest(ApprovalRequest request) {
        if (request.getStatus().equals("Pending")) {
            request.setStatus("Approved");
            System.out.println("Request " + request.getRequestId() + " approved.");
            return true;
        } else {
            System.out.println("Request " + request.getRequestId() + " cannot be approved.");
            return false;
        }
    }


    public boolean approveOverride(CourseOverride overrideRequest) {
        if (overrideRequest.getStatus().equals("Pending")) {
            overrideRequest.setStatus("Approved");
            System.out.println("Override " + overrideRequest.getOverrideID() +
                    " approved for student " + overrideRequest.getStudentID() +
                    " for course " + overrideRequest.getCourseID() + ".");
            return true;
        } else {
            System.out.println("Override " + overrideRequest.getOverrideID() + " cannot be approved.");
            return false;
        }
    }
}
