package CourseManagement.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseListView {
    private JPanel courseListPanel;
    private JTable table1;
    private JComboBox<String> DepartmentCombo;
    private JButton addCourseButton;
    private JPanel Labels;

    private DefaultTableModel tableModel;

    public CourseListView() {


        DepartmentCombo.addItem("All");
        DepartmentCombo.addItem("IST");
        DepartmentCombo.addItem("MATH");
        DepartmentCombo.addItem("PHYS");
        DepartmentCombo.addItem("ENGL");
        DepartmentCombo.addItem("CHEM");

        addCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> new AddCourseUI());
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
        JFrame frame = new JFrame("Course List");
        frame.setContentPane(new CourseListView().getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
