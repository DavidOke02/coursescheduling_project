package Authentication;

import AdvisorApproval.View.ScheduleApprovalUI;
import AdvisorApproval.View.StudentDecisionViewer;
import AdvisorApproval.View.OverrideRequestForm;
import Scheduling.View.StudentScheduleView;

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
    private JButton overrideRequestButton;
    private JButton goToScheduleButton;

    public StudentDashboard(String studentId) {
        this.studentId = studentId;

        setTitle("Student Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Label at the top
        welcomeLabel = new JLabel("Welcome, Student " + studentId + "!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(welcomeLabel, BorderLayout.NORTH);

        // Panel for buttons
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10));

        // Buttons
        submitApprovalButton = new JButton("Submit Approval Request");
        viewDecisionsButton = new JButton("View My Decisions");
        logoutButton = new JButton("Logout");
        overrideRequestButton = new JButton("Override Request Form");

        // Add buttons to panel
        buttonPanel.add(submitApprovalButton);
        buttonPanel.add(viewDecisionsButton);
        buttonPanel.add(overrideRequestButton);
        buttonPanel.add(logoutButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Button actions
        submitApprovalButton.addActionListener(e -> openScheduleApprovalUI());
        viewDecisionsButton.addActionListener(e -> openStudentDecisionsViewer());
        overrideRequestButton.addActionListener(e -> openOverrideRequestForm());
        logoutButton.addActionListener(e -> logout());
        goToScheduleButton.addActionListener(e -> {new StudentScheduleView();
        });

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
        String studentId = "ABC123"; // Replace this with actual student ID after login
        SwingUtilities.invokeLater(() -> new StudentDashboard(studentId));
    }
}
