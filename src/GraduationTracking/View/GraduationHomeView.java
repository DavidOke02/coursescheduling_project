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
        this.add(graduationHomeView);
        this.setTitle("Graduation Home");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setSize(800, 600);
        this.setMinimumSize(new Dimension(200,150));
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
            new GraduationStatusView();
        });
    }

    //Getters


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
