package AdvisorApproval.View;

import AdvisorApproval.Database.DatabaseUtil;
import AdvisorApproval.Model.CourseOverride;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OverrideRequestForm extends JFrame {
    private JTextField studentIDField;
    private JTextField courseIDField;
    private JTextField reasonField;
    private JButton submitButton;
    private String studentId;



    public OverrideRequestForm(String studentId) {
        this.studentId = studentId;
        setTitle("Course Override Request");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Label and input fields for Student ID
        JLabel studentLabel = new JLabel("Student ID:");
        studentLabel.setBounds(20, 20, 100, 25);
        add(studentLabel);

        studentIDField = new JTextField();
        studentIDField.setBounds(150, 20, 200, 25);
        add(studentIDField);

        // Label and input fields for Course ID
        JLabel courseLabel = new JLabel("Course ID:");
        courseLabel.setBounds(20, 60, 100, 25);
        add(courseLabel);

        courseIDField = new JTextField();
        courseIDField.setBounds(150, 60, 200, 25);
        add(courseIDField);

        // Label and input fields for Reason
        JLabel reasonLabel = new JLabel("Reason:");
        reasonLabel.setBounds(20, 100, 100, 25);
        add(reasonLabel);

        reasonField = new JTextField();
        reasonField.setBounds(150, 100, 200, 25);
        add(reasonField);

        // Submit button
        submitButton = new JButton("Submit");
        submitButton.setBounds(150, 150, 100, 30);
        add(submitButton);

        // Add action listener for submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    // Get the text from the fields and parse them
                    String studentID = studentIDField.getText();
                    String courseID = courseIDField.getText();
                    String reason = reasonField.getText();

                    CourseOverride override = new CourseOverride(
                            generateOverrideID(), // Generate unique override ID
                            studentID,
                            courseID,
                            reason,
                            "Pending" // Initial status
                    );


                    // Insert the override into the database
                    DatabaseUtil.insertCourseOverride(override);


                    JOptionPane.showMessageDialog(null, "Override request submitted.");

                    // Clear the fields after submission
                    studentIDField.setText("");
                    courseIDField.setText("");
                    reasonField.setText("");

                }

        });
    }

    // Generate unique override ID
    private String generateOverrideID() {
        return "O" + System.currentTimeMillis(); // Simple unique ID based on current time
    }


//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            OverrideRequestForm form = new OverrideRequestForm();
//            form.setVisible(true);
//        });
//    }
}
