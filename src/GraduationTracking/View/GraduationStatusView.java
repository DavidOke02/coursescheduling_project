package GraduationTracking.View;

import GraduationTracking.Controller.GraduationStatusController;

import javax.swing.*;
import java.awt.*;

public class GraduationStatusView extends JFrame {
    private JPanel graduationStatusView;
    private JPanel mainContentPanel;
    private JLabel graduationStatusLabel;
    private JPanel mainCintentPanel;
    private JLabel gradElegibilityLabel;
    private JTextPane textPane1;
    private JButton goBackButton;
    private JPanel buttonPanel;

    private GraduationStatusController controller;

    public GraduationStatusView() {
        controller = new GraduationStatusController(this);
        this.add(graduationStatusView);
        this.setTitle("Graduation Eligibility Status");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setSize(800, 600);
        this.setMinimumSize(new Dimension(200,150));
        displayContent();
        if (controller.calculateGradStatus("STU123")){
            gradElegibilityLabel.setText("Eligible for graduation!");
        }
        else {
            gradElegibilityLabel.setText("Not Eligible for graduation!");
            textPane1.setText("Reasons: \n -Not all courses required for degree are complete");
            textPane1.setEditable(false);
            textPane1.setFocusable(false);
        }
    }

    public void displayContent() {
        goBackButton.addActionListener(e -> {
            dispose();
            new GraduationHomeView("STU123");
        });
    }

    public JLabel getGraduationStatusLabel() {
        return graduationStatusLabel;
    }

    public JTextPane getTextPane1() {
        return textPane1;
    }
}
