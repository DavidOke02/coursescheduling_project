package CourseManagement.View;

import CourseManagement.Controller.CourseListViewController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CourseListView extends JPanel {
    public  CourseListViewController controller;
    private JPanel courseListPanel;
    private JTable table1;
    private JComboBox<String> departmentCombo;
    private JButton addCourseButton;
    private JPanel Labels;
    private JButton viewCourseButton;
    private JPanel tablePanel;
    private JPanel buttonPanel;
    private JButton returnToDashboardButton;

    private DefaultTableModel tableModel;

    public CourseListView() {
        this.add(courseListPanel);
        this.setSize(800, 600);
        this.controller = new CourseListViewController();
        departmentCombo.addItem("All");
        departmentCombo.addItem("IST");
        departmentCombo.addItem("MATH");
        departmentCombo.addItem("PHYS");
        departmentCombo.addItem("ENGL");
        departmentCombo.addItem("CHEM");
        controller.setView(this);
        controller.displayCourseList();

        initializeButtons();
        departmentCombo.addActionListener(e -> {
            Object selectedItem = departmentCombo.getSelectedItem();
            if (selectedItem == null) {
                JOptionPane.showMessageDialog(null, "Please add a department");

            }
            else if (selectedItem.equals("All")) {
                controller.displayCourseList();
            }
            else {
                controller.displayCourseList(((String) selectedItem));
            }

        });
    }

    public void initializeButtons(){
        addCourseButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> new AddCourseUI());
            CardLayout cardLayout = (CardLayout) getParent().getLayout();
            cardLayout.show(getParent(), "Add Course");
        });

        viewCourseButton.addActionListener(e -> {
            tableModel = (DefaultTableModel) table1.getModel();
            String courseID = tableModel.getValueAt(table1.getSelectedRow(), 0).toString();
           new CourseDetailView(courseID);
        });

        returnToDashboardButton.addActionListener(e -> {
            remove(this);
            CardLayout cardLayout = (CardLayout) getParent().getLayout();
            cardLayout.show(getParent(), "Dashboard");
            //new AdminDashboard();
        });
    }


    public JPanel getMainPanel() {
        return courseListPanel;
    }

    public JTable getTable() {
        return table1;
    }

    public JComboBox<String> getDepartmentCombo() {
        return departmentCombo;
    }

    public JButton getAddCourseButton() {
        return addCourseButton;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JButton getViewCourseButton() {
        return viewCourseButton;
    }

    public void setTableModel(DefaultTableModel model) {
        this.tableModel = model;
        table1.setModel(model);
    }

    public void setDepartmentComboOptions(String[] departments) {
        departmentCombo.removeAllItems();
        for (String dept : departments) {
            departmentCombo.addItem(dept);
        }
    }

    public static void main(String[] args) {
        CourseListViewController controller = new CourseListViewController();
        CourseListView view = new CourseListView();
        controller.setView(view);

        JFrame frame = new JFrame("Course List");
        frame.setContentPane(view.getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}