package CourseManagement.View;

import CourseManagement.Controller.CourseListViewController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseListView extends JPanel {
    private CourseListViewController controller;
    private JTable table1;
    private JComboBox<String> departmentCombo;
    private JButton addCourseButton;
    private JPanel courseListPanel;
    private JPanel Labels;
    private JButton viewCourseButton;
    private JButton backButton;
    private DefaultTableModel tableModel;

    public CourseListView(CourseListViewController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        // Set up Department Combo Box
        departmentCombo = new JComboBox<>();
        departmentCombo.addItem("All");
        departmentCombo.addItem("IST");
        departmentCombo.addItem("MATH");
        departmentCombo.addItem("PHYS");
        departmentCombo.addItem("ENGL");
        departmentCombo.addItem("CHEM");

        // Add Department Combo to the top panel
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Filter by Department:"));
        topPanel.add(departmentCombo);
        add(topPanel, BorderLayout.NORTH);

        // Table to show courses
        table1 = new JTable();
        tableModel = new DefaultTableModel(new Object[]{"Course ID", "Course Name", "Credits"}, 0);
        table1.setModel(tableModel);
        JScrollPane scrollPane = new JScrollPane(table1);
        add(scrollPane, BorderLayout.CENTER);

        // Action listener for Department filter combo box
        departmentCombo.addActionListener(e -> {
            String selectedDepartment = (String) departmentCombo.getSelectedItem();
            if (selectedDepartment != null) {
                if ("All".equals(selectedDepartment)) {
                    controller.displayCourseList(); // Display all courses
                } else {
                    controller.displayCourseList(selectedDepartment); // Filter by department
                }
            }
        });

        // Action listener for Add Course button
        addCourseButton = new JButton("Add Course");
        addCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Navigate to Add Course UI
                CardLayout cardLayout = (CardLayout) getParent().getLayout();
                cardLayout.show(getParent(), "Add Course");
            }
        });

        // Add Add Course button to the bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(addCourseButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Back to Dashboard Button
        backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) getParent().getLayout();
                cardLayout.show(getParent(), "Dashboard");
            }
        });

        // Add Back Button to a separate bottom panel to avoid overlap
        JPanel backButtonPanel = new JPanel();
        backButtonPanel.add(backButton);
        add(backButtonPanel, BorderLayout.SOUTH);

        controller.setView(this);
        controller.displayCourseList();  // Initial load of course data
    }

    public JTable getTable() {
        return table1;
    }

    public JComboBox<String> getDepartmentCombo() {
        return departmentCombo;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(DefaultTableModel model) {
        this.tableModel = model;
        table1.setModel(model);
    }
}
