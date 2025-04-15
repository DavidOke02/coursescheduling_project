package CourseManagement.Controller;

import CourseManagement.View.AdminDashboard;

public class AdminDashboardController {
    private AdminDashboard view;

    public AdminDashboardController() {
        this.view = new AdminDashboard(this);
    }
}
