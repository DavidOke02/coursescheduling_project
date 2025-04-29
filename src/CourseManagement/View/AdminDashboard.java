package CourseManagement.View;

import CourseManagement.Controller.CourseListViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard extends JFrame {

    private JPanel dashboardPanel;
    private JButton addCoursesButton;
    private JButton viewCoursesButton;
    private JButton manageProfessorButton;
    private JButton logoutButton;
    private JButton backToDashboardButton;
    private JLabel adminDashboardLabel;

    private CardLayout cardLayout;
    private JPanel cardPanel;

    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 650);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout());

        // Initialize CardLayout and main panel that holds all screens
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Create the Admin Dashboard panel
        dashboardPanel = new JPanel();
        dashboardPanel.setLayout(new GridLayout(6, 1, 10, 10)); // 6 rows, 1 column (one extra row for label)
        adminDashboardLabel = new JLabel("Admin Dashboard", JLabel.CENTER);
        adminDashboardLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Font style and size
        dashboardPanel.add(adminDashboardLabel); // Add label to the panel

        // Initialize buttons
        addCoursesButton = new JButton("Add New Course");
        viewCoursesButton = new JButton("View Courses");
        manageProfessorButton = new JButton("Manage Professors");
        logoutButton = new JButton("Logout");
        backToDashboardButton = new JButton("Back to Dashboard");

        // Add buttons to the panel
        dashboardPanel.add(addCoursesButton);
        dashboardPanel.add(viewCoursesButton);
        dashboardPanel.add(manageProfessorButton);
        dashboardPanel.add(logoutButton);

        // Action listeners for each button
        addCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Navigate to Add Course screen inside the same window
                cardLayout.show(cardPanel, "Add Course");
            }
        });

        viewCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Navigate to View Courses screen inside the same window
                cardLayout.show(cardPanel, "View Courses");
            }
        });

        manageProfessorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement Manage Professor functionality
                JOptionPane.showMessageDialog(AdminDashboard.this, "Manage Professors functionality is not implemented yet.");
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logout action (maybe navigate back to login screen)
                JOptionPane.showMessageDialog(AdminDashboard.this, "You have been logged out.");
                System.exit(0); // Close the application
            }
        });

        backToDashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Navigate back to dashboard screen (could reset everything or do any cleanup)
                cardLayout.show(cardPanel, "Dashboard");
            }
        });

        // Create the AddCourseUI and CourseListView panels
        AddCourseUI addCourseUI = new AddCourseUI();
        CourseListView courseListView = new CourseListView();

        // Create a panel for the dashboard, add it to the card layout
        cardPanel.add(dashboardPanel, "Dashboard");
        // Add the other panels (Add Course, View Courses) to the card layout
        cardPanel.add(addCourseUI, "Add Course");
        cardPanel.add(courseListView, "View Courses");

        // Add the card panel to the frame
        add(cardPanel, BorderLayout.CENTER);

        // Make the dashboard visible
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminDashboard());
    }
}
