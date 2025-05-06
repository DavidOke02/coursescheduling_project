package GraduationTracking.View;

import GraduationTracking.Controller.GraduationHomeViewController;

import javax.swing.*;
import java.awt.*;

public class GraduationHomeView extends JFrame {
    private JPanel graduationHomeView;
    private JLabel titleLabel;
    private JProgressBar degreeProgressBar;
    private JPanel mainContentPanel;
    private JTable coursesCompletedTable;
    private JTable coursesRemainingTable;
    private JTable inProgressTable;
    private JButton viewAcademicRecordButton;
    private JButton viewGraduationEligibilityButton;
    private JPanel buttonPanel;

    private GraduationHomeViewController controller;
    private String studentID;

    public GraduationHomeView(String studentID) {
        this.controller = new GraduationHomeViewController(this);
        this.studentID = studentID;

        // Initialize UI
        graduationHomeView = new JPanel(new BorderLayout());
        mainContentPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel = new JPanel(new FlowLayout());

        // Title
        titleLabel = new JLabel("Graduation Progress Tracker", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        graduationHomeView.add(titleLabel, BorderLayout.NORTH);

        // Progress bar
        degreeProgressBar = new JProgressBar();
        degreeProgressBar.setStringPainted(true);
        graduationHomeView.add(degreeProgressBar, BorderLayout.SOUTH);

        // Tables
        coursesCompletedTable = new JTable();
        coursesRemainingTable = new JTable();
        inProgressTable = new JTable();

        mainContentPanel.add(new JScrollPane(coursesCompletedTable));
        mainContentPanel.add(new JScrollPane(coursesRemainingTable));
        mainContentPanel.add(new JScrollPane(inProgressTable));

        graduationHomeView.add(mainContentPanel, BorderLayout.CENTER);

        // Buttons
        viewAcademicRecordButton = new JButton("View Academic Record");
        viewGraduationEligibilityButton = new JButton("View Graduation Eligibility");
        buttonPanel.add(viewAcademicRecordButton);
        buttonPanel.add(viewGraduationEligibilityButton);
        graduationHomeView.add(buttonPanel, BorderLayout.PAGE_END);

        setTitle("Graduation Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(200, 150));

        displayContent();
        controller.displayTables(studentID);
        controller.displayRemainingTables(studentID);
    }

    public void displayContent() {
        viewAcademicRecordButton.addActionListener(e -> {
            dispose();
            new AcademicRecordView(studentID);
        });

        viewGraduationEligibilityButton.addActionListener(e -> {
            dispose();
            new GraduationStatusView(studentID);
        });
    }

    // GETTER to use in StudentDashboard
    public JPanel getMainPanel() {
        return graduationHomeView;
    }

    public JTable getCoursesCompletedTable() {
        return coursesCompletedTable;
    }

    public JTable getCoursesRemainingTable() {
        return coursesRemainingTable;
    }

    public JTable getInProgressTable() {
        return inProgressTable;
    }

    public JProgressBar getDegreeProgressBar() {
        return degreeProgressBar;
    }
}
