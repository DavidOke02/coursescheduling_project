import AdvisorApproval.Database.DatabaseUtil;
import AdvisorApproval.Model.ApprovalRequest;
import AdvisorApproval.View.RequestListUI;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AdvisorDashboard extends JFrame {

    private String loggedInUserRole; // Use role from login
    private JPanel panel1;
    private JLabel welcomeLabel;
    private JPanel buttonPanel;
    private JButton viewRequestListButton;
    private JButton logoutButton;

    public AdvisorDashboard(String loggedInUserRole) {
        this.loggedInUserRole = loggedInUserRole;

        setTitle("Advisor Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null); // center the window

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Label at the top
        welcomeLabel = new JLabel("Welcome to your Dashboard, " + loggedInUserRole + "!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(welcomeLabel, BorderLayout.NORTH);

        // Panel for buttons
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10)); // 2 buttons stacked

        // Buttons
        viewRequestListButton = new JButton("View Request List");
        logoutButton = new JButton("Logout");

        // Add buttons to panel
        buttonPanel.add(viewRequestListButton);
        buttonPanel.add(logoutButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Button actions
        viewRequestListButton.addActionListener(e -> openRequestListUI());
        logoutButton.addActionListener(e -> logout());
    }

    private void openRequestListUI() {
        // Retrieve approval requests from the database
        List<ApprovalRequest> approvalRequests = DatabaseUtil.getAllApprovalRequests();


        new RequestListUI(approvalRequests).setVisible(true);
    }

    private void logout() {
        dispose(); // Close this window
        JOptionPane.showMessageDialog(this, "You have been logged out.");
    }

    public static void main(String[] args) {

        String loggedInUserRole = "Advisor"; // This could be dynamically set based on login

        // Create and show the Advisor Dashboard with the logged-in role
        SwingUtilities.invokeLater(() -> new AdvisorDashboard(loggedInUserRole));
    }
}
