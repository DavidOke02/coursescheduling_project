package Scheduling.View;

import Scheduling.Controller.ScheduleViewer;
import Scheduling.Controller.StudentDashboardController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentScheduleView extends JFrame{
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

    //Setup
    public StudentScheduleView() {
        this.controller = new ScheduleViewer(); //Controller

        this.add(scheduleViewPanel);
        this.setTitle("Student Schedule");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(800, 600);
        this.setMinimumSize(new Dimension(200,150));
        initializeButtons();

    }

    //Button Logic
    public void initializeButtons() {
        makeClickable(getHomeNavLabel());
        makeClickable(getSchedulingNavLabel());
        makeClickable(getViewCurrentScheduleNavLabel());

        getHomeNavLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                StudentDashboardController homeTestController= new StudentDashboardController();
            }
        });

        getSchedulingNavLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                StudentScheduleHomeView studentScheduleHomeView = new StudentScheduleHomeView();
            }
        });

        getChangeViewButton().addActionListener(e -> {
            dispose();
            new StudentScheduleHomeView();
        });

        getChangeViewButton().addActionListener(e -> {
            System.out.println("Change View Clicked");
            getChangeViewButton().setText("Change View2");
        });
    }

    public void makeClickable(JLabel label) {
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    //Getters
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
}
