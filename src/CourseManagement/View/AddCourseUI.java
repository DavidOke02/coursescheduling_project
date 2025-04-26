package CourseManagement.View;

import CourseManagement.Model.Course;
import CourseManagement.Controller.*;
import CourseManagement.Model.Prerequisite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddCourseUI extends JFrame {

    private JTextField courseIDField;
    private JTextField courseNameField;
    private JTextField creditsField;
    private JTextField departmentField;
    private JTextField seatsField;
    private JTextField professorIDField;
    private JTextField prereqField;
    private JTextField semesterField;

    public AddCourseUI() {
        setTitle("Add New Course");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new GridLayout(9, 2));

        // Input fields
        add(new JLabel("Course ID:"));
        courseIDField = new JTextField();
        add(courseIDField);

        add(new JLabel("Course Name:"));
        courseNameField = new JTextField();
        add(courseNameField);

        add(new JLabel("Credits:"));
        creditsField = new JTextField();
        add(creditsField);

        add(new JLabel("Department Code:"));
        departmentField = new JTextField();
        add(departmentField);

        add(new JLabel("Available Seats:"));
        seatsField = new JTextField();
        add(seatsField);

        add(new JLabel("Professor ID:"));
        professorIDField = new JTextField();
        add(professorIDField);

        add(new JLabel("Prerequisite Course ID:"));
        prereqField = new JTextField();
        add(prereqField);

        add(new JLabel("Semester Offered:"));
        semesterField = new JTextField();
        add(semesterField);

        JButton submitButton = new JButton("Add Course");
        add(submitButton);

        JLabel statusLabel = new JLabel("");
        add(statusLabel);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String courseID = courseIDField.getText();
                    String courseName = courseNameField.getText();
                    int credits = Integer.parseInt(creditsField.getText());
                    String dept = departmentField.getText();
                    int seats = Integer.parseInt(seatsField.getText());
                    String professorID = professorIDField.getText();
                    String prereq = prereqField.getText();
                    String semester = semesterField.getText();

                    Course courseToAdd = new Course(courseID, courseName, credits, dept, seats, professorID, prereq, semester);

                    try {
                        AddCourseController controller = new AddCourseController();
                        controller.addCourse(courseToAdd);
                        if (!prereq.isEmpty()) { //Checking prereqs if any is input
                            Prerequisite prereqToAdd = new Prerequisite(courseID, prereq);
                            PrerequisiteManager prereqManager = new PrerequisiteManager();
                            prereqManager.addPrerequisites(prereqToAdd);
                        }
                        statusLabel.setText("Course added successfully.");
                    } catch (Exception error) {
                        statusLabel.setText("Failed to add course.");
                    }

                } catch (Exception ex) {
                    statusLabel.setText("Error: " + ex.getMessage());
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new AddCourseUI();
    }
}
