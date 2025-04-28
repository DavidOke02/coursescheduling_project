package AdvisorApproval.View;

import AdvisorApproval.Model.ApprovalRequest;
import AdvisorApproval.Model.CourseOverride;
import AdvisorApproval.Database.DatabaseUtil;
import Authentication.AdvisorDashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RequestListUI extends JFrame {
    private List<ApprovalRequest> allRequests;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JPanel studentListPanel;
    private JPanel requestListPanel;
    private JButton backButton;
    private JButton finishButton;
    private JButton dashboardButton; // NEW BUTTON
    private String currentStudentID;

    public RequestListUI(List<ApprovalRequest> requests) {
        this.allRequests = requests;

        setTitle("Advisor Approval System");
        setSize(750, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        studentListPanel = new JPanel();
        studentListPanel.setLayout(new BoxLayout(studentListPanel, BoxLayout.Y_AXIS));
        JScrollPane studentScrollPane = new JScrollPane(studentListPanel);
        studentScrollPane.setBorder(BorderFactory.createTitledBorder("Select a Student"));

        requestListPanel = new JPanel();
        requestListPanel.setLayout(new BoxLayout(requestListPanel, BoxLayout.Y_AXIS));
        JScrollPane requestScrollPane = new JScrollPane(requestListPanel);
        requestScrollPane.setBorder(BorderFactory.createTitledBorder("Review Course Requests"));

        cardPanel.add(studentScrollPane, "StudentList");
        cardPanel.add(requestScrollPane, "RequestReview");

        add(cardPanel, BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);

        renderStudentList();
        setVisible(true);
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // Back to Dashboard button
        dashboardButton = new JButton("Back to Dashboard");
        dashboardButton.addActionListener(e -> {
            dispose(); // Close this window
            String advisorId = "ADV123";
            new AdvisorDashboard(advisorId);
        });

        // Back button for student list navigation
        backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "StudentList"));
        backButton.setVisible(false);

        // Finish button for completing the review
        finishButton = new JButton("Finish");
        finishButton.addActionListener(this::handleFinish);
        finishButton.setVisible(false);

        bottomPanel.add(dashboardButton); // Add Dashboard button first
        bottomPanel.add(backButton);
        bottomPanel.add(finishButton);

        return bottomPanel;
    }

    private void renderStudentList() {
        studentListPanel.removeAll();

        Map<String, List<ApprovalRequest>> groupedByStudent = allRequests.stream()
                .collect(Collectors.groupingBy(ApprovalRequest::getStudentId));

        for (String studentID : groupedByStudent.keySet()) {
            JButton studentButton = new JButton("Review requests from " + studentID);
            studentButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            studentButton.addActionListener(e -> {
                currentStudentID = studentID;
                renderRequestsForStudent(groupedByStudent.get(studentID));
                cardLayout.show(cardPanel, "RequestReview");
                backButton.setVisible(true);
                finishButton.setVisible(true);
            });

            studentListPanel.add(Box.createVerticalStrut(10));
            studentListPanel.add(studentButton);
        }

        studentListPanel.revalidate();
        studentListPanel.repaint();
    }

    private void renderRequestsForStudent(List<ApprovalRequest> requests) {
        requestListPanel.removeAll();

        // Fetch course overrides for the current student
        List<CourseOverride> courseOverrides = DatabaseUtil.getCourseOverridesForStudent(currentStudentID);

        // Render approval requests
        for (ApprovalRequest request : requests) {
            JPanel panel = new JPanel(new BorderLayout(10, 10));
            panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            panel.setMaximumSize(new Dimension(700, 130));

            JLabel infoLabel = new JLabel("Course: " + request.getCourseId() +
                    " | Comment: " + request.getComment());
            panel.add(infoLabel, BorderLayout.NORTH);

            JPanel centerPanel = new JPanel(new BorderLayout());

            JTextArea advisorComment = new JTextArea(3, 50);
            advisorComment.setBorder(BorderFactory.createTitledBorder("Advisor Comment"));
            centerPanel.add(new JScrollPane(advisorComment), BorderLayout.CENTER);

            JPanel buttonsPanel = new JPanel();
            JButton approveBtn = new JButton("Approve");
            JButton rejectBtn = new JButton("Reject");

            approveBtn.addActionListener(e -> {
                String comment = advisorComment.getText().trim();
                request.setStatus("Approved");
                request.setAdvisorComment(comment);
                DatabaseUtil.updateApprovalRequestStatus(
                        request.getStudentId(),
                        request.getCourseId(),
                        "Approved",
                        comment
                );
                approveBtn.setEnabled(false);
                rejectBtn.setEnabled(false);
                advisorComment.setEditable(false);
                approveBtn.setText("Approved");
            });

            rejectBtn.addActionListener(e -> {
                String comment = advisorComment.getText().trim();
                request.setStatus("Rejected");
                request.setAdvisorComment(comment);
                DatabaseUtil.updateApprovalRequestStatus(
                        request.getStudentId(),
                        request.getCourseId(),
                        "Rejected",
                        comment
                );
                approveBtn.setEnabled(false);
                rejectBtn.setEnabled(false);
                advisorComment.setEditable(false);
                rejectBtn.setText("Rejected");
            });

            buttonsPanel.add(approveBtn);
            buttonsPanel.add(rejectBtn);
            centerPanel.add(buttonsPanel, BorderLayout.SOUTH);

            panel.add(centerPanel, BorderLayout.CENTER);
            requestListPanel.add(panel);
        }

        // Render course overrides
        for (CourseOverride override : courseOverrides) {
            JPanel panel = new JPanel(new BorderLayout(10, 10));
            panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            panel.setMaximumSize(new Dimension(700, 130));

            JLabel infoLabel = new JLabel("Override Course: " + override.getCourseID() +
                    " | Reason: " + override.getReason());
            panel.add(infoLabel, BorderLayout.NORTH);

            JPanel centerPanel = new JPanel(new BorderLayout());
            panel.setBackground(Color.YELLOW);

            JTextArea advisorComment = new JTextArea(3, 50);
            advisorComment.setBorder(BorderFactory.createTitledBorder("Advisor Comment"));
            centerPanel.add(new JScrollPane(advisorComment), BorderLayout.CENTER);

            JPanel buttonsPanel = new JPanel();
            JButton approveBtn = new JButton("Approve");
            JButton rejectBtn = new JButton("Reject");

            approveBtn.addActionListener(e -> {
                String comment = advisorComment.getText().trim();
                override.setStatus("Approved");
                DatabaseUtil.updateCourseOverrideStatus(
                        override.getOverrideID(),
                        "Approved",
                        override.getReason(),
                        comment
                );
                approveBtn.setEnabled(false);
                rejectBtn.setEnabled(false);
                advisorComment.setEditable(false);
                approveBtn.setText("Approved");
            });

            rejectBtn.addActionListener(e -> {
                String comment = advisorComment.getText().trim();
                override.setStatus("Rejected");
                DatabaseUtil.updateCourseOverrideStatus(
                        override.getOverrideID(),
                        "Rejected",
                        override.getReason(),
                        comment
                );
                approveBtn.setEnabled(false);
                rejectBtn.setEnabled(false);
                advisorComment.setEditable(false);
                rejectBtn.setText("Rejected");
            });

            buttonsPanel.add(approveBtn);
            buttonsPanel.add(rejectBtn);
            centerPanel.add(buttonsPanel, BorderLayout.SOUTH);

            panel.add(centerPanel, BorderLayout.CENTER);
            requestListPanel.add(panel);
        }

        requestListPanel.revalidate();
        requestListPanel.repaint();
    }

    private void handleFinish(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "Decisions for " + currentStudentID + " have been sent to the student.");
        finishButton.setEnabled(false);
        finishButton.setText("Finished");
    }

// Uncomment for manual testing
//    public static void main(String[] args) {
//        List<ApprovalRequest> requests = DatabaseUtil.getAllApprovalRequests();
//        new RequestListUI(requests);
//    }
}
