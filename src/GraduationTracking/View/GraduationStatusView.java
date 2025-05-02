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

    private GraduationStatusController controller;

    public GraduationStatusView() {
        controller = new GraduationStatusController();
        this.add(graduationStatusView);
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
}
