package Scheduling.View;

import CourseManagement.Model.Course;
import Scheduling.Controller.ScheduleViewer;
import Scheduling.Controller.StudentDashboardController;

import javax.swing.*;
import javax.swing.table.TableModel;
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
    private JButton addCourseButton;
    private JButton viewCourseButton;
    private JPanel addPanel;
    private JPanel breadcrumbPanel;
    private JLabel homeNavLabel;
    private JLabel schedulingNavLabel;
    private JLabel viewCurrentScheduleNavLabel;
    private JButton cancelButton;

    private ScheduleViewer controller;
    private String studentID;

    //Setup
    public StudentScheduleView(String studentID) {
        this.controller = new ScheduleViewer(this); //Controller
        this.studentID = studentID;

        this.add(scheduleViewPanel);
        this.setTitle("Student Schedule");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(800, 600);
        this.setMinimumSize(new Dimension(200,150));
        initializeButtons();
        controller.displayCourseList(studentID);
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

        addCourseButton.addActionListener(e -> {
            dispose();
            //new RegisterCourseView();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new CourseSearchView().setVisible(true);
                }
            });
        });

        viewCourseButton.addActionListener(e -> {
            int selectedRowCart = table1.getSelectedRow();
            int selectedRowEnrollment = table2.getSelectedRow();
            Object selectedCourseID;
            if (selectedRowCart == -1 && selectedRowEnrollment == -1) {
                return;
            } else if (selectedRowCart != -1) {
                TableModel table1Model = (TableModel) table1.getModel();
                selectedCourseID = table1Model.getValueAt(selectedRowCart, 0);
            } else if (selectedRowEnrollment !=1 ) {
                TableModel table2Model = (TableModel) table2.getModel();
                selectedCourseID = table2Model.getValueAt(selectedRowEnrollment, 0);
            } else {
                return;
            }
            dispose();
            new CourseDetailView(selectedCourseID.toString(), studentID);
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

    // New: Get Main Panel
    public JPanel getMainPanel() {
        return scheduleViewPanel;
    }
}
