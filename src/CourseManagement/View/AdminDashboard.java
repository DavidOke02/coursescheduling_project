package CourseManagement.View;

import CourseManagement.Controller.AdminDashboardController;
import CourseManagement.Controller.CourseListViewController;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame{
    private JPanel dashboardPanel;
    private JButton addCoursesButton;
    private JButton viewCoursesButton;
    private JLabel adminDashboardLabel;
    private AdminDashboardController controller;

    public AdminDashboard() {
        this.controller = new AdminDashboardController();

        this.add(dashboardPanel);
        this.setTitle("Admin Dashboard");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(800, 600);
        this.setMinimumSize(new Dimension(200,150));
        initializeButtons();
    }

    public void initializeButtons(){
        this.addCoursesButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> new AddCourseUI());
        });

        this.viewCoursesButton.addActionListener(e -> {
            CourseListView.main(new String[0]);
        });
    }

    public JPanel getDashboardPanel() {
        return dashboardPanel;
    }

    public JButton getAddCoursesButton() {
        return addCoursesButton;
    }

    public JButton getViewCoursesButton() {
        return viewCoursesButton;
    }


    public JLabel getAdminDashboardLabel() {
        return adminDashboardLabel;
    }


}
