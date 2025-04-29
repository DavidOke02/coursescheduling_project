package Authentication;

import AdvisorApproval.View.ScheduleApprovalUI;
import AdvisorApproval.View.StudentDecisionViewer;
import AdvisorApproval.View.OverrideRequestForm;
import Scheduling.View.StudentScheduleView;
import Authentication.LoginUI;

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
    private JButton backToDashboardButton;

    public StudentDashboard(String studentId) {
        this.studentId = studentId;

        setTitle("Student Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
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
        buttonPanel.setLayout(new GridLayout(5, 1, 10, 10));

        // Buttons
        submitApprovalButton = new JButton("Submit Approval Request");
        viewDecisionsButton = new JButton("View My Decisions");
        overrideRequestButton = new JButton("Override Request Form");
        goToScheduleButton = new JButton("View My Schedule");
        logoutButton = new JButton("Logout");

        // Add buttons to panel
        buttonPanel.add(submitApprovalButton);
        buttonPanel.add(viewDecisionsButton);
        buttonPanel.add(overrideRequestButton);
        buttonPanel.add(goToScheduleButton);
        buttonPanel.add(logoutButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Button actions
        submitApprovalButton.addActionListener(e -> openScheduleApprovalUI());
        viewDecisionsButton.addActionListener(e -> openStudentDecisionsViewer());
        overrideRequestButton.addActionListener(e -> openOverrideRequestForm());
        goToScheduleButton.addActionListener(e -> openStudentScheduleView());
        logoutButton.addActionListener(e -> logout());
    }

    private void openScheduleApprovalUI() {
        JFrame scheduleFrame = new ScheduleApprovalUI(studentId);
        addBackButton(scheduleFrame);
    }

    private void openStudentDecisionsViewer() {
        JFrame decisionFrame = new JFrame("View My Decisions");
        StudentDecisionViewer viewer = new StudentDecisionViewer(studentId);
        decisionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        decisionFrame.setSize(500, 400);
        decisionFrame.setLocationRelativeTo(null);
        decisionFrame.add(viewer.getMainPanel());
        addBackButton(decisionFrame);
    }

    private void openOverrideRequestForm() {
        JFrame overrideFrame = new JFrame("Override Request Form");
        OverrideRequestForm form = new OverrideRequestForm(studentId);
        overrideFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        overrideFrame.setSize(500, 400);
        overrideFrame.setLocationRelativeTo(null);
        overrideFrame.add(form.getMainPanel());
        addBackButton(overrideFrame);
    }

    private void openStudentScheduleView() {
        JFrame scheduleViewFrame = new JFrame("View My Schedule");
        StudentScheduleView scheduleView = new StudentScheduleView(studentId);
        scheduleViewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        scheduleViewFrame.setSize(500, 400);
        scheduleViewFrame.setLocationRelativeTo(null);
        scheduleViewFrame.add(scheduleView.getMainPanel());
        addBackButton(scheduleViewFrame);
    }

    private void logout() {
        dispose();
        JOptionPane.showMessageDialog(this, "You have been logged out.");
        new LoginUI().setVisible(true);
    }

    private void addBackButton(JFrame frame) {
        JButton backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(e -> {
            frame.dispose();
            this.setVisible(true);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
        this.setVisible(false);
    }

    public static void main(String[] args) {
        String studentId = "ABC123"; // Replace with actual login
        SwingUtilities.invokeLater(() -> new StudentDashboard(studentId));
    }
}
