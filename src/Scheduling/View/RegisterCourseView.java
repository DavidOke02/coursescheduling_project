package Scheduling.View;

import Scheduling.Model.CustomCourse;

import javax.swing.*;

public class RegisterCourseView extends JFrame {
    private JPanel basePanel;
    private JLabel Heading;
    private JLabel Course_Name;
    private JLabel Department_Code;
    private JTextField DepartmentCodeField;
    private JButton registerCourseButton;
    private JPanel buttonPanel;
    private JPanel mainContentPanel;

    /**
     * Allows a student to register for a course.
     */

    //Setup
    public RegisterCourseView() {
        initializeButtons();
    }

    //Button Logic
    public void initializeButtons(){

    }

    public void displayRegistrationSuccess(CustomCourse newCourse) {
    }

    public void displayRegistrationFailure() {
    }

    //Getters

}
