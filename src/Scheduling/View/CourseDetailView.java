package Scheduling.View;

import CourseManagement.Model.Course;
import Scheduling.Controller.CourseDetailSearchController;

import javax.swing.*;
import java.awt.*;

public class CourseDetailView extends JFrame {


    private JPanel courseDetailView;
    private JLabel courseIDLabel;
    private JTextField courseNameField;
    private JLabel courseNameLabel;
    private JTextField creditsField;
    private JTextField departmentField;
    private JTextField seatsField;
    private JTextField professorField;
    private JLabel creditsLabel;
    private JLabel departmentCodeLabel;
    private JLabel seatsLabel;
    private JLabel professorLabel;
    private JLabel prerequisiteLabel;
    private JTextField prerequisiteField;
    private JTextField semesterField;
    private JLabel semesterLabel;
    private JPanel mainContentPanel;
    private JButton goBackButton;

    private CourseDetailSearchController controller;
    private String studentID;

    public CourseDetailView(String courseID, String studentID) {
        this.add(courseDetailView);
        this.setTitle("Course Details");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setSize(800, 600);
        this.setMinimumSize(new Dimension(200,150));
        this.controller = new CourseDetailSearchController();
        this.studentID = studentID;
        Course courseToDisplay = controller.displayCourseDetails(courseID);
        setFields(courseToDisplay);
        initializeButtons();
    }

    public void initializeButtons() {
        goBackButton.addActionListener(e -> {
            dispose();
            new StudentScheduleView(studentID);
        });

    }

    public void setFields(Course courseToDisplay) {
        courseIDLabel.setText(courseToDisplay.getCourseID());
        courseNameField.setText(courseToDisplay.getCourseName());
        courseNameField.setEnabled(false);
        creditsField.setText(String.valueOf(courseToDisplay.getCredits()));
        creditsField.setEnabled(false);
        departmentField.setText(courseToDisplay.getDepartmentCode());
        departmentField.setEnabled(false);
        seatsField.setText(String.valueOf(courseToDisplay.getAvailableSeats()));
        seatsField.setEnabled(false);
        professorField.setText(courseToDisplay.getProfessor());
        professorField.setEnabled(false);
        prerequisiteField.setText(courseToDisplay.getPrerequisites());
        prerequisiteField.setEnabled(false);
        semesterField.setText(courseToDisplay.getSemesterOffered());
        semesterField.setEnabled(false);
    }
}
