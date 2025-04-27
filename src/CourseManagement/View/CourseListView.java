package CourseManagement.View;

import CourseManagement.Controller.CourseListViewController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseListView {
    public  CourseListViewController controller;
    private JPanel courseListPanel;
    private JTable table1;
    private JComboBox<String> DepartmentCombo;
    private JButton addCourseButton;
    private JPanel Labels;
    private JButton viewCourseButton;

    private DefaultTableModel tableModel;

    public CourseListView(CourseListViewController controller) {
        this.controller = controller;
        DepartmentCombo.addItem("All");
        DepartmentCombo.addItem("IST");
        DepartmentCombo.addItem("MATH");
        DepartmentCombo.addItem("PHYS");
        DepartmentCombo.addItem("ENGL");
        DepartmentCombo.addItem("CHEM");
        controller.setView(this);
        controller.displayCourseList();

        addCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> new AddCourseUI());
            }
        });

        DepartmentCombo.addActionListener(e -> {
            Object selectedItem = DepartmentCombo.getSelectedItem();
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

    public JPanel getMainPanel() {
        return courseListPanel;
    }

    public JTable getTable() {
        return table1;
    }

    public JComboBox<String> getDepartmentCombo() {
        return DepartmentCombo;
    }

    public JButton getAddCourseButton() {
        return addCourseButton;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }



    public void setTableModel(DefaultTableModel model) {
        this.tableModel = model;
        table1.setModel(model);
    }

    public void setDepartmentComboOptions(String[] departments) {
        DepartmentCombo.removeAllItems();
        for (String dept : departments) {
            DepartmentCombo.addItem(dept);
        }
    }

    public static void main(String[] args) {
        CourseListViewController controller = new CourseListViewController();
        CourseListView view = new CourseListView(controller);
        controller.setView(view);

        JFrame frame = new JFrame("Course List");
        frame.setContentPane(view.getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
