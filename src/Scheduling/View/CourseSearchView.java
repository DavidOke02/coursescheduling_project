package Scheduling.View;

import CourseManagement.Model.Course;
import Scheduling.Controller.CourseDetailSearchController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CourseSearchView extends JFrame {
    private CourseDetailSearchController controller;
    private JTextField courseIDnameField;
    private JButton searchButton;
    private JTextArea resultArea;
    private JButton addButton;
    private JLabel courseIDLabel;
    private JPanel basePanel;

    private JTextArea detailArea;
    private DefaultListModel<Course> listModel;
    private JList<Course> courseList;

    private String studentID;
    private StudentScheduleView parentView; // reference to refresh

    // Updated constructor
    public CourseSearchView(String studentID, StudentScheduleView parentView) {
        this.studentID = studentID;
        this.parentView = parentView;
        this.controller = new CourseDetailSearchController();
        setTitle("Course Search");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("Enter Course ID:");
        courseIDnameField = new JTextField(20);
        searchButton = new JButton("Search");
        searchPanel.add(label);
        searchPanel.add(courseIDnameField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        courseList = new JList<>(listModel);
        courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScrollPane = new JScrollPane(courseList);

        detailArea = new JTextArea(6, 30);
        detailArea.setEditable(false);
        JScrollPane detailScrollPane = new JScrollPane(detailArea);

        JSplitPane centerPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, listScrollPane, detailScrollPane);
        centerPane.setDividerLocation(200);
        add(centerPane, BorderLayout.CENTER);

        addButton = new JButton("Add Selected Course to Schedule");
        add(addButton, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> searchCourses());
        courseList.addListSelectionListener(e -> showCourseDetails());
        addButton.addActionListener(e -> addSelectedCourse());
    }

    private void searchCourses() {
        String searchTerm = courseIDnameField.getText().trim();
        listModel.clear();
        detailArea.setText("");

        if (searchTerm.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a course ID.");
            return;
        }

        List<Course> results = controller.searchCoursesByID(searchTerm);
        if (results.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No matching courses found.");
        } else {
            for (Course c : results) {
                listModel.addElement(c);
            }
        }
    }

    private void showCourseDetails() {
        Course selected = courseList.getSelectedValue();
        if (selected != null) {
            detailArea.setText(
                    "Course ID: " + selected.getCourseID() + "\n" +
                            "Name: " + selected.getCourseName() + "\n" +
                            "Instructor: " + selected.getProfessor() + "\n" +
                            "Credits: " + selected.getCredits() + "\n" +
                            "Department: " + selected.getDepartmentCode() + "\n" +
                            "Seats Available: " + selected.getAvailableSeats() + "\n" +
                            "Prerequisite: " + selected.getPrerequisites() + "\n"
            );
        }
    }

    private void addSelectedCourse() {
        Course selected = courseList.getSelectedValue();
        if (selected != null) {
            int response = JOptionPane.showConfirmDialog(this, "Do you want to add this course to your schedule?");
            if (response == JOptionPane.YES_OPTION) {
                controller.addCourseToSchedule(studentID, selected);
                JOptionPane.showMessageDialog(this, "Course added to schedule.");

                // Refresh parent view (StudentScheduleView)
                if (parentView != null) {
                    parentView.getController().displayCourseList(studentID); // Refreshing the schedule view
                }

                dispose(); // Close the course search window

                // Reopen the StudentScheduleView
                SwingUtilities.invokeLater(() -> new StudentScheduleView(studentID)); // Reopen the schedule view
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a course to add.");
        }
    }
}
