package CourseManagement.Controller;

import CourseManagement.Model.*;

/**
 * Main class demonstrating the Course Management System
 */
public class CourseManagementSystem {
    public static void main(String[] args) {
        // Create objects
        Course course = new Course();
        Professor professor = new Professor();
        Department department = new Department();
        Semester semester = new Semester();

        // Use controllers
        //AddCourseToCourseList addCourseController = new AddCourseToCourseList();
        //addCourseController.addCourse(course);

        //ProfessorManager assignController = new ProfessorManager();
        //assignController.assignProfessor(professor.getProfessorID(), course.getCourseID());

        //PrerequisiteManager prereqController = new PrerequisiteManager();
        //prereqController.addPrerequisite("IST201", "IST101");

        // Use views
        //CourseDetailView detailPage = new CourseDetailView();
        //detailPage.displayCourseDetails(course);

        //AdminDashboard2 dashboard = new AdminDashboard2();
        //dashboard.displayDashboard(professor);

        // Demonstrate waitlist functionality
        Waitlist waitlist = new Waitlist();
        waitlist.addStudent("S12345");
        waitlist.removeStudent("S12345");

        // Update course details
        ModifyCourseDetails modifyController = new ModifyCourseDetails();
        modifyController.updateCourse(course, "Advanced Programming", 4);

        // Show updated course details
        //detailPage.displayCourseDetails(course);
    }
}


