package GraduationTracking.View;

import GraduationTracking.Controller.AcademicRecordController;

import javax.swing.*;
import java.awt.*;

public class AcademicRecordView extends JFrame {
    private JPanel academicControllerView;
    private JLabel titleLabel;
    private JTable academicRecordTable;
    private JPanel mainContentPanel;
    private JPanel buttonPanel;
    private JButton goBackButton;

    private AcademicRecordController controller;

    public AcademicRecordView(String studentID) {
        this.controller = new AcademicRecordController(this);
        this.add(academicControllerView);
        this.setTitle("Academic Record");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setSize(800, 600);
        this.setMinimumSize(new Dimension(200,150));
        displayContent();
        controller.displayRecordTable(studentID);
    }

    public void displayContent() {
        goBackButton.addActionListener(e -> {
            dispose();
            new GraduationHomeView("STU123");
        });
    }

    //Getters

    public JTable getAcademicRecordTable() {
        return academicRecordTable;
    }

    public JButton getGoBackButton() {
        return goBackButton;
    }
}
