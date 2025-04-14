package CourseManagement.View;

import CourseManagement.Controller.AdminDashboardController;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame{
    private JPanel dashboardPanel;
    private JButton addCoursesButton;
    private JButton viewCoursesButton;
    private JLabel adminDashboardLabel;
private AdminDashboardController controller;
    //Add course button
    //View Course button

    public AdminDashboard(AdminDashboardController controller) {
        this.controller = controller;

        this.add(dashboardPanel);
        this.setTitle("Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(800, 600);
        this.setMinimumSize(new Dimension(200,150));
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

    public void setDashboardPanel(JPanel dashboardPanel) {
        this.dashboardPanel = dashboardPanel;
    }

    public void setAddCoursesButton(JButton addCoursesButton) {
        this.addCoursesButton = addCoursesButton;
    }

    public void setViewCoursesButton(JButton viewCoursesButton) {
        this.viewCoursesButton = viewCoursesButton;
    }

    public JLabel getAdminDashboardLabel() {
        return adminDashboardLabel;
    }

    public void setAdminDashboardLabel(JLabel adminDashboardLabel) {
        this.adminDashboardLabel = adminDashboardLabel;

    }

}
