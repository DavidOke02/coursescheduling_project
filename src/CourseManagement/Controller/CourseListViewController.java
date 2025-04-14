package CourseManagement.Controller;

import CourseManagement.View.AddCourseUI;
import CourseManagement.View.CourseListView;

import javax.swing.*;

public class CourseListViewController {
    private CourseListView view;

    public void setView(CourseListView view) {
        this.view = view;
    }

    // Example methods
    public void handleAddCourseButton() {
        SwingUtilities.invokeLater(() -> new AddCourseUI());
    }

    public void filterCoursesByDepartment(String deptCode) {
        System.out.println("Filtering for department: " + deptCode);
    }
}
