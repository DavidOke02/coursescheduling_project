package Scheduling.View;

import Scheduling.Controller.RegisterCourseController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Allows a student to register for a custom course.
 */
public class RegisterCourseView extends JFrame {
    private JPanel basePanel;
    private JLabel Title;
    private JTextField NamwtextField;
    private JTextField CodetextField;
    private JTextField CreditstextField;
    private JButton exitButton;
    private JButton addCourseButton;
    private JLabel Name;
    private JLabel code;
    private JLabel credits;
    private JTextArea textArea; // (renamed for clarity)

    private RegisterCourseController controller;

    // Setup
    public RegisterCourseView(RegisterCourseController controller) {
        this.controller = controller;
        setContentPane(basePanel);
        setTitle("Register Custom Course");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        initializeButtons();
    }

    // Button Logic
    public void initializeButtons() {
//        addCourseButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                addCustomCourse();
//            }
//        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // Close the window
            }
        });
    }

//    private void addCustomCourse() {
//        try {
//            String courseName = NamwtextField.getText().trim();
//            String courseCode = CodetextField.getText().trim();
//            int credits = Integer.parseInt(CreditstextField.getText().trim());
//
//            if (courseName.isEmpty() || courseCode.isEmpty() || credits <= 0) {
//                textArea.setText(" Please fill in Course Name, Course Code, and Credits correctly!");
//                return;
//            }
//
//            controller.addCustomCourse(courseName, courseCode, credits);
//            textArea.setText(" Course '" + courseName + "' added successfully!");
//            clearForm();
//        } catch (NumberFormatException ex) {
//            textArea.setText("Credits must be a valid number.");
//        }
//    }

    private void clearForm() {
        NamwtextField.setText("");
        CodetextField.setText("");
        CreditstextField.setText("");
    }

    // Getters
    public JPanel getBasePanel() {
        return basePanel;
    }
}