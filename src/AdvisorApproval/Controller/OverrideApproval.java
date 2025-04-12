package AdvisorApproval.Controller;

import AdvisorApproval.Model.CourseOverride;

// Controller: Handles override approvals
public class OverrideApproval {
    /**
     * Approves a course override request.
     */
    public boolean approveOverride(CourseOverride override) {
        if (override.getReason().equalsIgnoreCase("Prerequisite not met")) {
            System.out.println("Override for " + override.getCourseID() + " requires further review.");
            return false;
        } else {
            System.out.println("Override approved for " + override.getCourseID());
            return true;
        }
    }

}
