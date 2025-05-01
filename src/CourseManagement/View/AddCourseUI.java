package CourseManagement.View;

import CourseManagement.Model.Course;
import CourseManagement.Controller.AddCourseController;
import CourseManagement.Model.Prerequisite;
import CourseManagement.Controller.PrerequisiteManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCourseUI extends JPanel {
    private JTextField courseIDField;
    private JTextField courseNameField;
    private JTextField creditsField;
    private JTextField departmentField;
    private JTextField seatsField;
    private JTextField professorIDField;
    private JTextField prereqField;
    private JTextField semesterField;
    private JTextField timeSlotField;  // New field for Time Slot
    private JLabel statusLabel;

    public AddCourseUI() {
        setLayout(new GridLayout(12, 2));  // Adjusted grid to accommodate the new field

        // Course ID
        add(new JLabel("Course ID:"));
        courseIDField = new JTextField();
        add(courseIDField);

        // Course Name
        add(new JLabel("Course Name:"));
        courseNameField = new JTextField();
        add(courseNameField);

        // Credits
        add(new JLabel("Credits:"));
        creditsField = new JTextField();
        add(creditsField);

        // Department Code
        add(new JLabel("Department Code:"));
        departmentField = new JTextField();
        add(departmentField);

        // Available Seats
        add(new JLabel("Available Seats:"));
        seatsField = new JTextField();
        add(seatsField);

        // Professor ID
        add(new JLabel("Professor ID:"));
        professorIDField = new JTextField();
        add(professorIDField);

        // Prerequisite Course ID
        add(new JLabel("Prerequisite Course ID:"));
        prereqField = new JTextField();
        add(prereqField);

        // Semester Offered
        add(new JLabel("Semester Offered:"));
        semesterField = new JTextField();
        add(semesterField);

        // Time Slot
        add(new JLabel("Time Slot:"));  // New label for Time Slot
        timeSlotField = new JTextField();  // New text field for Time Slot
        add(timeSlotField);

        // Submit Button
        JButton submitButton = new JButton("Add Course");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addCourse();
            }
        });
        add(submitButton);

        // Status Label
        statusLabel = new JLabel("");
        add(statusLabel);

        // Back to Dashboard Button
        JButton backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) getParent().getLayout();
                cardLayout.show(getParent(), "Dashboard");
            }
        });
        add(backButton);

        setVisible(true);
    }

    private void addCourse() {
        try {
            String courseID = courseIDField.getText();
            String courseName = courseNameField.getText();
            int credits = Integer.parseInt(creditsField.getText());
            String dept = departmentField.getText();
            int seats = Integer.parseInt(seatsField.getText());
            String professorID = professorIDField.getText();
            String prereq = prereqField.getText();
            String semester = semesterField.getText();
            String timeSlot = timeSlotField.getText();  // Get timeSlot from the new field

            Course courseToAdd = new Course(courseID, courseName, credits, dept, seats, professorID, prereq, semester, timeSlot);

            AddCourseController controller = new AddCourseController();
            controller.addCourse(courseToAdd);

            if (!prereq.isEmpty()) {
                Prerequisite prereqToAdd = new Prerequisite(courseID, prereq);
                PrerequisiteManager prereqManager = new PrerequisiteManager();
                prereqManager.addPrerequisites(prereqToAdd);
            }

            statusLabel.setText("Course added successfully.");
        } catch (Exception ex) {
            statusLabel.setText("Error: " + ex.getMessage());
        }
    }
}
