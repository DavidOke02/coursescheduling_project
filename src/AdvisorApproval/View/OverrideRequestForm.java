package AdvisorApproval.View;

import AdvisorApproval.Database.DatabaseUtil;
import AdvisorApproval.Model.CourseOverride;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OverrideRequestForm extends JFrame {
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

        // Label showing Student ID (not editable)
        JLabel studentLabel = new JLabel("Student ID: " + studentId);
        studentLabel.setBounds(20, 20, 300, 25);
        add(studentLabel);

        // Label and input field for Course ID
        JLabel courseLabel = new JLabel("Course ID:");
        courseLabel.setBounds(20, 60, 100, 25);
        add(courseLabel);

        courseIDField = new JTextField();
        courseIDField.setBounds(150, 60, 200, 25);
        add(courseIDField);

        // Label and input field for Reason
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

        // Action listener for the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitOverrideRequest();
            }
        });
    }

    private void submitOverrideRequest() {
        String courseID = courseIDField.getText().trim();
        String reason = reasonField.getText().trim();

        if (courseID.isEmpty() || reason.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        CourseOverride override = new CourseOverride(
                null,
                studentId,
                courseID,
                reason,
                "Pending",
                ""
        );

        // Insert the override into the database
        DatabaseUtil.insertCourseOverride(override);

        JOptionPane.showMessageDialog(this, "Override request submitted successfully.");

        // Clear the fields after submission
        courseIDField.setText("");
        reasonField.setText("");
    }

    // Generate unique override ID
    private String generateOverrideID() {
        return "O" + System.currentTimeMillis(); // Simple unique ID based on current time
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            OverrideRequestForm form = new OverrideRequestForm("123456");
//            form.setVisible(true);
//        });
//    }
}
