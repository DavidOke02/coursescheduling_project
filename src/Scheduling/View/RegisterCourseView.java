package Scheduling.View;

import Scheduling.Controller.RegisterCourseController;
import Scheduling.Model.CustomCourse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterCourseView extends JFrame {
    private JPanel basePanel;
    private JLabel Heading;
    private JLabel Course_Name;
    private JLabel Course_ID;
    private JTextField CourseNameField;
    private JTextField CourseIDField;
    private JTextField InstructorField;
    private JLabel Instructor;
    private JLabel Credits;
    private JTextField CreditsField;
    private JLabel Department_Code;
    private JTextField DepartmentCodeField;
    private JLabel PrerequisitesLabel;
    private JTextField PrerequisitesField;
    private JLabel SemesterLabel;
    private JTextField SemesterField;
    private JLabel TimeslotLabel;
    private JTextField TimeslotField;
    private JButton registerCourseButton;

    private RegisterCourseController controller;

    // Setup
    public RegisterCourseView() {
        this.controller = new RegisterCourseController();
        controller.setRegisterView(this);

        this.add(basePanel);
        this.setTitle("Register Course");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setSize(800, 600);
        this.setMinimumSize(new Dimension(200, 150));
        initializeButtons();
    }

    public void initializeButtons() {
        registerCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseID = CourseIDField.getText().trim();
                String courseName = CourseNameField.getText().trim();
                String instructor = InstructorField.getText().trim();
                String departmentCode = DepartmentCodeField.getText().trim();
                String prerequisites = PrerequisitesField.getText().trim();
                String semester = SemesterField.getText().trim();
                String timeslot = TimeslotField.getText().trim();

                if (courseID.isEmpty() || courseName.isEmpty() || instructor.isEmpty() ||
                        CreditsField.getText().trim().isEmpty() || departmentCode.isEmpty()) {
                    JOptionPane.showMessageDialog(RegisterCourseView.this,
                            "All fields are required",
                            "Validation Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int credits;
                try {
                    credits = Integer.parseInt(CreditsField.getText().trim());
                    if (credits <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(RegisterCourseView.this,
                            "Credits must be a positive number",
                            "Validation Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (controller.courseExists(courseID)) {
                    JOptionPane.showMessageDialog(RegisterCourseView.this,
                            "A course with ID " + courseID + " already exists",
                            "Registration Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int availableSeats = 30;


                controller.registerCourse(courseID, courseName, instructor, credits, departmentCode, availableSeats, prerequisites, semester, timeslot);
            }
        });
    }

    public void displayRegistrationSuccess(CustomCourse newCourse) {
        JOptionPane.showMessageDialog(this,
                "Course " + newCourse.getCourseName() + " (" + newCourse.getCourseID() + ") has been successfully registered.",
                "Registration Success",
                JOptionPane.INFORMATION_MESSAGE);

        // Resetting Fields
        CourseIDField.setText("");
        CourseNameField.setText("");
        InstructorField.setText("");
        CreditsField.setText("");
        DepartmentCodeField.setText("");
        PrerequisitesField.setText("");
        SemesterField.setText("");
        TimeslotField.setText("");
    }

    public void displayRegistrationFailure() {
        JOptionPane.showMessageDialog(this,
                "Failed to register the course. Please check database connection and try again.",
                "Registration Error",
                JOptionPane.ERROR_MESSAGE);
    }

    // Getters
    public JPanel getBasePanel() {
        return basePanel;
    }

    public JLabel getHeading() {
        return Heading;
    }

    public JLabel getCourse_Name() {
        return Course_Name;
    }

    public JLabel getCourse_ID() {
        return Course_ID;
    }

    public JTextField getCourseNameField() {
        return CourseNameField;
    }

    public JTextField getCourseIDField() {
        return CourseIDField;
    }

    public JTextField getInstructorField() {
        return InstructorField;
    }

    public JLabel getInstructor() {
        return Instructor;
    }

    public JLabel getCredits() {
        return Credits;
    }

    public JTextField getCreditsField() {
        return CreditsField;
    }

    public JLabel getDepartment_Code() {
        return Department_Code;
    }

    public JTextField getDepartmentCodeField() {
        return DepartmentCodeField;
    }

    public JLabel getPrerequisitesLabel() {
        return PrerequisitesLabel;
    }

    public JTextField getPrerequisitesField() {
        return PrerequisitesField;
    }

    public JLabel getSemesterLabel() {
        return SemesterLabel;
    }

    public JTextField getSemesterField() {
        return SemesterField;
    }

    public JLabel getTimeslotLabel() {
        return TimeslotLabel;
    }

    public JTextField getTimeslotField() {
        return TimeslotField;
    }

    public JButton getRegisterCourseButton() {
        return registerCourseButton;
    }
}
