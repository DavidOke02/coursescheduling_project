package Scheduling.View;

import Scheduling.Controller.CourseDetailSearchController;
import Scheduling.Model.LionPathCourse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseSearchView extends JFrame {

    private CourseDetailSearchController controller;
    private JTextField courseIDField;
    private JTextArea resultArea;
    private JPanel basePanel;
    private JButton searchButton;
    private JLabel courseIDLabel;

    public CourseSearchView(CourseDetailSearchController controller) {
        this.controller = controller;

        setTitle("Course Detail Search");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top panel for input
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        JLabel courseIDLabel = new JLabel("Enter Course ID:");
        courseIDField = new JTextField(15);
        JButton searchButton = new JButton("Search");

        topPanel.add(courseIDLabel);
        topPanel.add(courseIDField);
        topPanel.add(searchButton);

        add(topPanel, BorderLayout.NORTH);

        // Center panel for results
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // Action listener for search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchCourse();
            }
        });
    }

    private void searchCourse() {
        String courseID = courseIDField.getText();
        LionPathCourse course = controller.searchByCourseID(courseID);

        if (course != null) {
            resultArea.setText(
                    "Course Title: " + course.getCourseTitle() + "\n" +
                            "Instructor: " + course.getInstructorName() + "\n" +
                            "Credits: " + course.getCredits() + "\n" +
                            "Department: " + course.getDepartmentName() + "\n" +
                            "Available Seats: " + course.getAvailableSeats() + "\n" +
                            "Date Enrolled: " + course.getDateEnrolled()
            );
        } else {
            resultArea.setText("Course not found.");
        }
    }
    public static void main(String[] args) {
        // Create controller instance
        CourseDetailSearchController controller = new CourseDetailSearchController();

        // Insert sample courses into the database
        controller.addCourseToDB(new LionPathCourse("CSE100", "Intro to Programming", "Dr. Smith", 3, "Computer Science", 20, "2025-01-10"));
        controller.addCourseToDB(new LionPathCourse("MATH200", "Calculus II", "Dr. Johnson", 4, "Mathematics", 15, "2025-01-11"));

        // Launch the UI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CourseSearchView(controller).setVisible(true);
            }
        });
}}


