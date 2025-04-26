package AdvisorApproval.View;

import AdvisorApproval.Database.DatabaseUtil;
import AdvisorApproval.Model.ApprovalRequest;
import AdvisorApproval.Model.CourseOverride;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StudentDecisionViewer {

    public void createAndShowGUI() {
        String studentId = JOptionPane.showInputDialog(null, "Enter your Student ID:", "Student Login", JOptionPane.PLAIN_MESSAGE);

        if (studentId == null || studentId.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Student ID is required.");
            return;
        }

        ArrayList<ApprovalRequest> requests = DatabaseUtil.getApprovalRequestsForStudent(studentId);
        ArrayList<CourseOverride> overrides = DatabaseUtil.getCourseOverridesForStudent(studentId);

        JFrame frame = new JFrame("Advisor Decisions for Student: " + studentId);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 700);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        // Display Approval Requests
        if (requests.isEmpty()) {
            JLabel noDataLabel = new JLabel("No advisor decisions found for student ID: " + studentId);
            noDataLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            noDataLabel.setForeground(Color.GRAY);
            noDataLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(Box.createVerticalGlue());
            mainPanel.add(noDataLabel);
            mainPanel.add(Box.createVerticalGlue());
        } else {
            for (ApprovalRequest req : requests) {
                JPanel card = new JPanel();
                card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
                card.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)
                ));
                card.setBackground(new Color(250, 250, 250));

                JLabel courseLabel = new JLabel("Course: " + req.getCourseId());
                courseLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));

                JLabel statusLabel = new JLabel("Status: " + req.getStatus());
                statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                statusLabel.setForeground(req.getStatus().equalsIgnoreCase("Approved") ? new Color(0, 128, 0) : Color.RED);

                JLabel advisorCommentLabel = new JLabel("<html><b>Advisor Comments:</b> " + (req.getAdvisorComment() != null ? req.getAdvisorComment() : "No comment") + "</html>");
                advisorCommentLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                advisorCommentLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

                card.add(courseLabel);
                card.add(statusLabel);
                card.add(advisorCommentLabel);
                card.add(Box.createRigidArea(new Dimension(0, 10)));

                JPanel cardWrapper = new JPanel();
                cardWrapper.setLayout(new FlowLayout(FlowLayout.CENTER));
                cardWrapper.add(card);
                mainPanel.add(cardWrapper);
                mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }

        // Separator line
        if (!overrides.isEmpty()) {
            JSeparator separator = new JSeparator();
            separator.setPreferredSize(new Dimension(600, 1));
            separator.setForeground(Color.GRAY);
            mainPanel.add(separator);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        }

        // Display Course Override Requests
        if (overrides.isEmpty()) {
            JLabel noOverrideDataLabel = new JLabel("No course override decisions found for student ID: " + studentId);
            noOverrideDataLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            noOverrideDataLabel.setForeground(Color.GRAY);
            noOverrideDataLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(noOverrideDataLabel);
        } else {
            JLabel overrideHeader = new JLabel("Course Override Decisions");
            overrideHeader.setFont(new Font("Segoe UI", Font.BOLD, 18));
            overrideHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(overrideHeader);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

            for (CourseOverride override : overrides) {
                JPanel card = new JPanel();
                card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
                card.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)
                ));
                card.setBackground(new Color(250, 250, 250));

                JLabel courseLabel = new JLabel("Course: " + override.getCourseID());
                courseLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));

                JLabel statusLabel = new JLabel("Status: " + override.getStatus());
                statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                statusLabel.setForeground(override.getStatus().equalsIgnoreCase("Approved") ? new Color(0, 128, 0) : Color.RED);

                JLabel reasonLabel = new JLabel("<html><b>Reason:</b> " + override.getReason() + "</html>");
                reasonLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                reasonLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

                card.add(courseLabel);
                card.add(statusLabel);
                card.add(reasonLabel);
                card.add(Box.createRigidArea(new Dimension(0, 10)));

                JPanel cardWrapper = new JPanel();
                cardWrapper.setLayout(new FlowLayout(FlowLayout.CENTER));
                cardWrapper.add(card);
                mainPanel.add(cardWrapper);
                mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        frame.getContentPane().add(scrollPane);

        frame.setVisible(true);
    }


//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new StudentDecisionViewer().createAndShowGUI());
//    }
}
