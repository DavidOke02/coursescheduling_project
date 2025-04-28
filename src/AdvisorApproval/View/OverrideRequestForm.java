package AdvisorApproval.View;

import AdvisorApproval.Database.DatabaseUtil;
import AdvisorApproval.Model.CourseOverride;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OverrideRequestForm extends JFrame {
    private JTextField courseIDField;
    private JTextField reasonField;
    private JButton submitButton;
    private String studentId;
    private JPanel mainPanel;

    public OverrideRequestForm(String studentId) {
        this.studentId = studentId;
        setTitle("Course Override Request");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel(new BorderLayout(10, 10));

        // Label showing Student ID (not editable)
        JLabel studentLabel = new JLabel("Student ID: " + studentId);
        studentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        studentLabel.setFont(new Font("Arial", Font.BOLD, 14));
        mainPanel.add(studentLabel, BorderLayout.NORTH);

        // Main Panel for input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 10, 10));

        // Course ID
        JLabel courseLabel = new JLabel("Course ID:");
        courseIDField = new JTextField();
        inputPanel.add(courseLabel);
        inputPanel.add(courseIDField);

        // Reason
        JLabel reasonLabel = new JLabel("Reason:");
        reasonField = new JTextField();
        inputPanel.add(reasonLabel);
        inputPanel.add(reasonField);

        mainPanel.add(inputPanel, BorderLayout.CENTER);

        // Submit button
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitOverrideRequest();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
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

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
