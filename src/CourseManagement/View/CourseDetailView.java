package CourseManagement.View;

import CourseManagement.Model.Course;

/**
 * View for displaying course details
 */
public class CourseDetailView {
    /**
     * Display course details
     */
    public void displayCourseDetails(Course course) {
        System.out.println("Displaying details for course: " + course.getCourseID());
        System.out.println("Course Details:");
        System.out.println("ID: " + course.getCourseID());
        System.out.println("Name: " + course.getCourseName());
        System.out.println("Credits: " + course.getCredits());
        System.out.println("Department: " + course.getDepartmentCode());
        System.out.println("Available Seats: " + course.getAvailableSeats());
    }

    public void showPrerequisites(String courseID) {
        System.out.println("Showing prerequisites for course: " + courseID);
    }
}

