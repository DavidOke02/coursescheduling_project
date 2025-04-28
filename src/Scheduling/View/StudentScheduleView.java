package Scheduling.View;

import Scheduling.Controller.ScheduleViewer;
import Scheduling.Controller.StudentDashboardController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentScheduleView extends JFrame {
    private JPanel scheduleViewPanel;
    private JPanel enrollmentCartPanel;
    private JPanel enrollmentListPanel;
    private JLabel enrollmentCartLabel;
    private JLabel enrollmentListLabel;
    private JTable table1;
    private JTable table2;
    private JButton changeViewButton;
    private JButton modifyCourseButton;
    private JPanel addPanel;
    private JPanel breadcrumbPanel;
    private JLabel homeNavLabel;
    private JLabel schedulingNavLabel;
    private JLabel viewCurrentScheduleNavLabel;
    private JButton cancelButton;

    private ScheduleViewer controller;

    // Constructor
    public StudentScheduleView(String studentId) {
        this.controller = new ScheduleViewer(); // Controller

        this.add(scheduleViewPanel);
        this.setTitle("Student Schedule");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setMinimumSize(new Dimension(200, 150));
        this.setVisible(true);

        initializeButtons();
    }

    // Button Logic
    private void initializeButtons() {
        makeClickable(homeNavLabel);
        makeClickable(schedulingNavLabel);
        makeClickable(viewCurrentScheduleNavLabel);

        homeNavLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new StudentDashboardController();
            }
        });

        schedulingNavLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new StudentScheduleHomeView();
            }
        });

        changeViewButton.addActionListener(e -> {
            System.out.println("Change View Clicked");
            dispose();
            new StudentScheduleHomeView();
        });

        // Optional: if you want to toggle the button text every time it's clicked
        /*
        changeViewButton.addActionListener(e -> {
            System.out.println("Change View Clicked");
            if (changeViewButton.getText().equals("Change View")) {
                changeViewButton.setText("Change View 2");
            } else {
                changeViewButton.setText("Change View");
            }
        });
        */
    }

    private void makeClickable(JLabel label) {
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    // Getters
    public JPanel getScheduleViewPanel() {
        return scheduleViewPanel;
    }

    public JPanel getEnrollmentCartPanel() {
        return enrollmentCartPanel;
    }

    public JPanel getEnrollmentListPanel() {
        return enrollmentListPanel;
    }

    public JLabel getEnrollmentCartLabel() {
        return enrollmentCartLabel;
    }

    public JLabel getEnrollmentListLabel() {
        return enrollmentListLabel;
    }

    public JTable getTable1() {
        return table1;
    }

    public JTable getTable2() {
        return table2;
    }

    public JButton getChangeViewButton() {
        return changeViewButton;
    }

    public JButton getModifyCourseButton() {
        return modifyCourseButton;
    }

    public JPanel getAddPanel() {
        return addPanel;
    }

    public JPanel getBreadcrumbPanel() {
        return breadcrumbPanel;
    }

    public JLabel getHomeNavLabel() {
        return homeNavLabel;
    }

    public JLabel getSchedulingNavLabel() {
        return schedulingNavLabel;
    }

    public JLabel getViewCurrentScheduleNavLabel() {
        return viewCurrentScheduleNavLabel;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    // New: Get Main Panel
    public JPanel getMainPanel() {
        return scheduleViewPanel;
    }
}
