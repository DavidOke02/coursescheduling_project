package Scheduling.View;

import Scheduling.Controller.StudentDashboardController;

import javax.swing.*;
import java.awt.*;

public class StudentDashboardView extends JFrame {
    private JPanel basePanel;
    private JButton schedulingButton;
    private JButton graduationTrackingButton;
    private JLabel titleLabel;
    private JPanel breadcrumbPanel;
    private JLabel homeNavLabel;

    private StudentDashboardController controller;


    //Setup
    public StudentDashboardView(StudentDashboardController controller) {
        this.controller = controller;

        this.add(basePanel);
        this.setTitle("Home");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(800, 600);
        this.setMinimumSize(new Dimension(200,150));
        initializeButtons();

    }

    //Button Logic
    public void initializeButtons(){

    }

    //Getters
    public JButton getSchedulingButton() {
        return schedulingButton;
    }

    public JButton getGraduationTrackingButton() {
        return graduationTrackingButton;
    }
}
