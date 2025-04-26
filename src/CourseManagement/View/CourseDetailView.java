package CourseManagement.View;

import CourseManagement.Controller.ModifyCourseDetails;
import CourseManagement.Model.Course;

import javax.swing.*;
import java.awt.*;

public class CourseDetailView extends JFrame {
    private JPanel courseDetailView;
    private JLabel titleLabel;
    private JLabel idLabel;
    private JTextField courseNameField;
    private JLabel courseNameLabel;
    private JLabel creditsLabel;
    private JTextField creditsField;
    private JLabel departmentCodeLabel;
    private JTextField departmentCodeField;
    private JLabel seatsLabel;
    private JLabel professorIDLabel;
    private JTextField seatsField;
    private JLabel prerequisiteLabel;
    private JTextField prerequisiteField;
    private JTextField semesterField;
    private JLabel semesterLabel;
    private JTextField professorField;
    private JButton updateCourseButton;
    private JButton deleteCourseButton;

    private ModifyCourseDetails controller;

    //Setup
    public CourseDetailView(int courseID) {
        this.add(courseDetailView);
        this.setTitle("Course Details");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(800, 600);
        this.setMinimumSize(new Dimension(200,150));
        controller = new ModifyCourseDetails();
        //Course courseToDisplay = controller.viewCourse(courseID)
        initializeButtons();
    }

    //Button Logic
    public void initializeButtons() {
        getUpdateCourseButton().addActionListener(e -> {
            try{
                String id = idLabel.getText();
                String name = courseNameField.getText();
                int credits = Integer.parseInt(creditsField.getText());
                String department = departmentCodeField.getText();
                int seats = Integer.parseInt(seatsField.getText());
                String professor = professorIDLabel.getText();
                String prerequisite = prerequisiteField.getText();
                String semester = semesterField.getText();
                Course updatedCourse = new Course(id, name, credits, department, seats, professor, prerequisite, semester);
                //controller.updateCourse(updatedCourse)
                System.out.println("Updated Course Details for " + id);
            }catch (Exception ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });

        getDeleteCourseButton().addActionListener(e -> {
            //controller.deleteCourse(courseID)
            System.out.println("Deleted Course " + idLabel.getText());
        });
    }

    //Getters
    public JTextField getCourseDescriptionField() {
        return courseNameField;
    }

    public JTextField getCreditsField() {
        return creditsField;
    }

    public JTextField getDepartmentCodeField() {
        return departmentCodeField;
    }

    public JTextField getPrerequisiteField() {
        return prerequisiteField;
    }

    public JTextField getSemesterField() {
        return semesterField;
    }

    public JTextField getProfessorField() {
        return professorField;
    }

    public JButton getUpdateCourseButton() {
        return updateCourseButton;
    }

    public JButton getDeleteCourseButton() {
        return deleteCourseButton;
    }

    public JTextField getSeatsField() {
        return seatsField;
    }

    public JLabel getIdLabel() {
        return idLabel;
    }
}
