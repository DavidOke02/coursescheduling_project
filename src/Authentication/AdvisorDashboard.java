package Authentication;

import AdvisorApproval.Database.DatabaseUtil;
import AdvisorApproval.Model.ApprovalRequest;
import AdvisorApproval.View.RequestListUI;
import Authentication.LoginUI;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AdvisorDashboard extends JFrame {

    private String advisorId;
    private JPanel panel1;
    private JLabel welcomeLabel;
    private JPanel buttonPanel;
    private JButton viewApprovalRequestsButton;
    private JButton logoutButton;
    private JButton viewRequestListButton;

    public AdvisorDashboard(String advisorId) {
        this.advisorId = advisorId;

        setTitle("Advisor Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Label at the top
        welcomeLabel = new JLabel("Welcome, Advisor " + advisorId + "!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(welcomeLabel, BorderLayout.NORTH);

        // Panel for buttons
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));

        // Buttons
        viewApprovalRequestsButton = new JButton("View Approval Requests");
        logoutButton = new JButton("Logout");

        // Add buttons to panel
        buttonPanel.add(viewApprovalRequestsButton);
        buttonPanel.add(logoutButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Button actions
        viewApprovalRequestsButton.addActionListener(e -> openRequestListUI());
        logoutButton.addActionListener(e -> logout());
    }

    private void openRequestListUI() {
        // Open the RequestListUI
        List<ApprovalRequest> requests = DatabaseUtil.getAllApprovalRequests(); // Fetch requests
        new RequestListUI(requests); // Open the RequestListUI with the fetched requests
    }

    private void logout() {
        dispose(); // Close the current window
        JOptionPane.showMessageDialog(this, "You have been logged out.");
        System.exit(0); // Exit the application completely
        new LoginUI().setVisible(true); // Show the login UI
    }

    public static void main(String[] args) {
        String advisorId = "ADV123"; // Replace with actual login
        SwingUtilities.invokeLater(() -> new AdvisorDashboard(advisorId));
    }
}
