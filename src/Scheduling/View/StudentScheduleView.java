package Scheduling.View;

import Scheduling.Controller.ScheduleViewer;
import Scheduling.Controller.StudentDashboardController;

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

    // Constructor: no JFrame or setVisible here!
    public StudentScheduleView(String studentID) {
        this.controller = new ScheduleViewer(this);
        this.studentID = studentID;

        initializeButtons();
        controller.displayCourseList(studentID);
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
                new StudentDashboardController();
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
            SwingUtilities.getWindowAncestor(scheduleViewPanel).dispose();
            SwingUtilities.invokeLater(() -> new CourseSearchView().setVisible(true));
        });

        viewCourseButton.addActionListener(e -> {
            int selectedRowCart = table1.getSelectedRow();
            int selectedRowEnrollment = table2.getSelectedRow();
            Object selectedCourseID;

            if (selectedRowCart == -1 && selectedRowEnrollment == -1) {
                return;
            } else if (selectedRowCart != -1) {
                selectedCourseID = table1.getModel().getValueAt(selectedRowCart, 0);
            } else {
                selectedCourseID = table2.getModel().getValueAt(selectedRowEnrollment, 0);
            }

            SwingUtilities.getWindowAncestor(scheduleViewPanel).dispose();
            new CourseDetailView(selectedCourseID.toString(), studentID);
        });

        enrollInCourseButton.addActionListener(e -> {
            int selectedRowCart = table1.getSelectedRow();
            if (selectedRowCart != -1) {
                Object selectedCourseID = table1.getModel().getValueAt(selectedRowCart, 0);
                controller.moveToEnrollment(studentID, selectedCourseID.toString());
                controller.displayCourseList(studentID);
            }
        });
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

    // Public method to provide the full panel
    public JPanel getMainPanel() {
        return scheduleViewPanel;
    }
}
