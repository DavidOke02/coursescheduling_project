package AdvisorApproval;

import AdvisorApproval.Model.ApprovalRequest;
import AdvisorApproval.View.OverrideRequestForm;
import AdvisorApproval.View.RequestListUI;
import AdvisorApproval.Database.DatabaseUtil;
import AdvisorApproval.View.ScheduleApprovalUI;
import AdvisorApproval.View.StudentDecisionViewer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
     // Form for Students to request an "Override"
        SwingUtilities.invokeLater(() -> {
            OverrideRequestForm form = new OverrideRequestForm();
            form.setVisible(true);
        });

      // Page for Advisors to manage requests
        List<ApprovalRequest> requests = DatabaseUtil.getAllApprovalRequests();
        new RequestListUI(requests);

     // Form for Student to submit a set of courses to get approved
          SwingUtilities.invokeLater(() -> {
           ScheduleApprovalUI form = new ScheduleApprovalUI("RCW5372"); // just for test
           form.setVisible(true);
        });

        // Screen of a student to see the results from the advisor
        SwingUtilities.invokeLater(() -> new StudentDecisionViewer().createAndShowGUI());
    }
}
