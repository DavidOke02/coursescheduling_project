import AdvisorApproval.Database.DatabaseUtil;
import AdvisorApproval.Model.ApprovalRequest;
import AdvisorApproval.Model.CourseOverride;
import AdvisorApproval.View.ScheduleApprovalUI;
import AdvisorApproval.View.StudentDecisionViewer;
import AdvisorApproval.View.OverrideRequestForm; // Assuming you have this class

import javax.swing.*;
import java.awt.*;

public class StudentDashboard extends JFrame {

    private String studentId;
    private JPanel panel1;
    private JLabel welcomeLabel;
    private JPanel buttonPanel;
    private JButton submitApprovalButton;
    private JButton viewDecisionsButton;
    private JButton logoutButton;
    private JButton overrideRequestButton; // New button for Override Request Form

    public StudentDashboard(String studentId) {
        this.studentId = studentId;

        setTitle("Student Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null); // center the window

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Label at the top
        JLabel welcomeLabel = new JLabel("Welcome, Student " + studentId + "!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(welcomeLabel, BorderLayout.NORTH);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10)); // 4 buttons stacked

        // Buttons
        submitApprovalButton = new JButton("Submit Approval Request");
        viewDecisionsButton = new JButton("View My Decisions");
        logoutButton = new JButton("Logout");
        overrideRequestButton = new JButton("Override Request Form"); // New button

        // Add buttons to panel
        buttonPanel.add(submitApprovalButton);
        buttonPanel.add(viewDecisionsButton);
        buttonPanel.add(overrideRequestButton); // Add new button
        buttonPanel.add(logoutButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Button actions
        submitApprovalButton.addActionListener(e -> openScheduleApprovalUI());
        viewDecisionsButton.addActionListener(e -> openStudentDecisionsViewer());
        overrideRequestButton.addActionListener(e -> openOverrideRequestForm()); // New action
        logoutButton.addActionListener(e -> logout());
    }

    private void openScheduleApprovalUI() {

        new ScheduleApprovalUI(studentId).setVisible(true);
    }

    private void openStudentDecisionsViewer() {

        new StudentDecisionViewer(studentId).createAndShowGUI();
    }

    private void openOverrideRequestForm() {

        new OverrideRequestForm(studentId).setVisible(true);
    }

    private void logout() {
        dispose();
        JOptionPane.showMessageDialog(this, "You have been logged out.");

    }

    public static void main(String[] args) {
        // In a real application, the student ID will be passed after successful login.
        // For testing purposes, hard-code the student ID here.
        String studentId = "12345"; // Replace this with actual student ID after login

        // Create and show the Student Dashboard with the pre-logged-in student ID
        SwingUtilities.invokeLater(() -> new StudentDashboard(studentId));
    }
}
