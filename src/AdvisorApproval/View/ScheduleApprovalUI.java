package AdvisorApproval.View;

import AdvisorApproval.Database.DatabaseUtil;
import AdvisorApproval.Model.ApprovalRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ScheduleApprovalUI extends JFrame {
    private String studentId;
    private List<ApprovalRequest> sharedRequests;
    private DefaultListModel<String> submittedCoursesListModel;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JComboBox<String> termComboBox;
    private JTextField deptInputField;
    private JTextField numberInputField;
    private JTextArea commentArea;
    private JButton addButton;
    private JList<String> submittedCoursesList;
    private JLabel stepLabel;
    private JButton previousButton;
    private JButton nextButton;
    private JButton submitButton;
    private JPanel wizardPanel;

    private int currentStep = 0;

    public ScheduleApprovalUI(String studentId) {
        this.studentId = studentId;

        setTitle("Student Course Submission");
        setSize(700, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(createStep1Panel(), "Step 1");
        cardPanel.add(createStep2Panel(), "Step 2");
        cardPanel.add(createStep3Panel(), "Step 3");

        wizardPanel = createWizardBar();
        add(wizardPanel, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        stepLabel = new JLabel("Step 1 of 3");
        bottomPanel.add(stepLabel, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel();
        previousButton = new JButton("Previous");
        nextButton = new JButton("Next");
        submitButton = new JButton("Submit");

        previousButton.addActionListener(this::handlePrevious);
        nextButton.addActionListener(this::handleNext);
        submitButton.addActionListener(this::handleSubmit);

        buttonPanel.add(previousButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(submitButton);

        bottomPanel.add(buttonPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        updateUIState();
        setVisible(true);
    }

    private JPanel createWizardBar() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3));
        panel.add(new JLabel("1. Select Term", SwingConstants.CENTER));
        panel.add(new JLabel("2. Add Courses", SwingConstants.CENTER));
        panel.add(new JLabel("3. Submitted", SwingConstants.CENTER));
        return panel;
    }

    private JPanel createStep1Panel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Step 1: Select Term"));

        String[] terms = {"Spring 2025", "Fall 2025", "Summer 2025"};
        termComboBox = new JComboBox<>(terms);
        panel.add(termComboBox, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createStep2Panel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Step 2: Add Courses"));

        JPanel coursePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        deptInputField = new JTextField(5);
        numberInputField = new JTextField(5);

        coursePanel.add(new JLabel("Department:"));
        coursePanel.add(deptInputField);
        coursePanel.add(Box.createHorizontalStrut(10));
        coursePanel.add(new JLabel("Course Number:"));
        coursePanel.add(numberInputField);

        commentArea = new JTextArea(3, 30);
        commentArea.setLineWrap(true);
        commentArea.setWrapStyleWord(true);

        panel.add(coursePanel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("Comments (optional):"));
        panel.add(new JScrollPane(commentArea));

        addButton = new JButton("Add Course");
        addButton.addActionListener(this::handleAddCourse);
        panel.add(addButton);

        submittedCoursesListModel = new DefaultListModel<>();
        submittedCoursesList = new JList<>(submittedCoursesListModel);
        JScrollPane listScrollPane = new JScrollPane(submittedCoursesList);
        listScrollPane.setBorder(BorderFactory.createTitledBorder("Courses to Submit"));
        panel.add(listScrollPane);

        return panel;
    }

    private JPanel createStep3Panel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Step 3"));

        JLabel confirmationLabel = new JLabel("Your requests have been sent.", SwingConstants.CENTER);
        confirmationLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(confirmationLabel, BorderLayout.CENTER);

        return panel;
    }

    private void handleAddCourse(ActionEvent e) {
        String dept = deptInputField.getText().trim().toUpperCase();
        String number = numberInputField.getText().trim();
        String comment = commentArea.getText().trim();
        String term = (String) termComboBox.getSelectedItem();

        if (dept.isEmpty() || number.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both department and course number.");
            return;
        }

        String course = dept + " " + number;


        ApprovalRequest request = new ApprovalRequest(
                null,
                studentId,
                course,
                comment,
                "",
                "Pending",
                term
        );


        DatabaseUtil.insertApprovalRequest(request);

        String displayText = course + " | Status: Pending | Comment: " + comment;
        submittedCoursesListModel.addElement(displayText);

        deptInputField.setText("");
        numberInputField.setText("");
        commentArea.setText("");
    }

    private void handleNext(ActionEvent e) {
        if (currentStep < 2) {
            currentStep++;
            cardLayout.show(cardPanel, "Step " + (currentStep + 1));
            updateUIState();
        }
    }

    private void handlePrevious(ActionEvent e) {
        if (currentStep > 0) {
            currentStep--;
            cardLayout.show(cardPanel, "Step " + (currentStep + 1));
            updateUIState();
        }
    }

    private void handleSubmit(ActionEvent e) {
        cardLayout.show(cardPanel, "Step 3");
        currentStep = 2;
        updateUIState();
    }

    private void updateUIState() {
        stepLabel.setText("Step " + (currentStep + 1) + " of 3");
        previousButton.setEnabled(currentStep > 0);
        nextButton.setVisible(currentStep == 0);
        submitButton.setVisible(currentStep == 1);
        nextButton.setEnabled(currentStep == 0);
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            ScheduleApprovalUI form = new ScheduleApprovalUI("RCW5372"); // just for test
//            form.setVisible(true);
//        });
//    }
}
