package Scheduling.View;

import Scheduling.Controller.ScheduleViewer;
import Authentication.StudentDashboard;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentScheduleView {
    private JPanel scheduleViewPanel;
    private JPanel enrollmentCartPanel;
    private JPanel enrollmentListPanel;
    private JLabel enrollmentCartLabel;
    private JLabel enrollmentListLabel;
    private JTable table1;
    private JTable table2;
    private JButton addCourseButton;
    private JButton viewCourseButton;
    private JPanel addPanel;
    private JPanel breadcrumbPanel;
    private JLabel homeNavLabel;
    private JLabel schedulingNavLabel;
    private JLabel viewCurrentScheduleNavLabel;
    private JButton cancelButton;
    private JButton enrollInCourseButton;

    private ScheduleViewer controller;
    private String studentID;

    // Constructor
    public StudentScheduleView(String studentID) {
        this.controller = new ScheduleViewer(this);
        this.studentID = studentID;

        initializeButtons();
        controller.displayCourseList(studentID);

        // Show panel inside a frame (if needed)
        JFrame frame = new JFrame("Student Schedule");
        frame.setContentPane(scheduleViewPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Button Logic
    public void initializeButtons() {
        makeClickable(getHomeNavLabel());
        makeClickable(getSchedulingNavLabel());
        makeClickable(getViewCurrentScheduleNavLabel());

        getHomeNavLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.getWindowAncestor(scheduleViewPanel).dispose();
                new StudentDashboard(studentID);
            }
        });

        getSchedulingNavLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.getWindowAncestor(scheduleViewPanel).dispose();
                new StudentScheduleHomeView();
            }
        });

        addCourseButton.addActionListener(e -> {
            SwingUtilities.getWindowAncestor(scheduleViewPanel).dispose(); // Close current schedule view
            SwingUtilities.invokeLater(() -> new CourseSearchView(studentID, this).setVisible(true)); // Open Course Search View
        });

        viewCourseButton.addActionListener(e -> {
            int selectedRowCart = table1.getSelectedRow();
            int selectedRowEnrollment = table2.getSelectedRow();
            Object selectedCourseID;

            if (selectedRowCart == -1 && selectedRowEnrollment == -1) {
                return;
            } else if (selectedRowCart != -1) {
                selectedCourseID = table1.getModel().getValueAt(selectedRowCart, 0);
            } else if (selectedRowEnrollment != -1) {
                selectedCourseID = table2.getModel().getValueAt(selectedRowEnrollment, 0);
            } else {
                return;
            }

            SwingUtilities.getWindowAncestor(scheduleViewPanel).dispose();
            new CourseDetailView(selectedCourseID.toString(), studentID);
        });

        enrollInCourseButton.addActionListener(e -> {
            int selectedRowCart = table1.getSelectedRow();
            if (selectedRowCart != -1) {
                Object selectedCourseID = table1.getModel().getValueAt(selectedRowCart, 0);
                controller.moveToEnrollment(studentID, selectedCourseID.toString());

                // Refresh the course list after adding the course to the enrollment list
                controller.displayCourseList(studentID);

                // Keep the window open and refresh the content to show the updated enrollment list
                updateScheduleView();
            }
        });
    }

    // Method to update the schedule view (i.e., refresh the tables)
    private void updateScheduleView() {
        // Reload the course list from the controller to update the enrollment cart and list
        controller.displayCourseList(studentID);

        // Revalidate and repaint the panel to refresh the UI components
        scheduleViewPanel.revalidate();
        scheduleViewPanel.repaint();
    }

    public void makeClickable(JLabel label) {
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

    public JButton getAddCourseButton() {
        return addCourseButton;
    }

    public JButton getModifyCourseButton() {
        return viewCourseButton;
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

    public JPanel getMainPanel() {
        return scheduleViewPanel;
    }

    public ScheduleViewer getController() {
        return controller;
    }
}
