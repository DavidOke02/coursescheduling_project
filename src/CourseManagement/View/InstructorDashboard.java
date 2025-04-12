package CourseManagement.View;

import CourseManagement.Model.Professor;

/**
 * View for instructor dashboard
 */
public class InstructorDashboard {
    /**
     * Display instructor dashboard
     */
    private Professor professor;

    public void displayDashboard(Professor professor) {
        System.out.println("Displaying dashboard for professor: " + professor.getProfessorID());
    }

    public void showCoursesTaught(Professor professor) {
        System.out.println("Showing courses taught by professor: " + professor.getProfessorID());
    }
}

