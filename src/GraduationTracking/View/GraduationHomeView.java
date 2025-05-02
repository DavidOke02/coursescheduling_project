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

    public GraduationHomeView() {
        controller = new GraduationHomeViewController();
        this.add(graduationHomeView);
        this.setTitle("Graduation Home");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setSize(800, 600);
        this.setMinimumSize(new Dimension(200,150));
        displayContent();
    }

    public void displayContent() {

    }

    //Getters

}
