package CourseManagement.Controller;

import CourseManagement.Model.Course;

/**
 * Controller for adding courses to the list
 */
public class AddCourseToCourseList {
    /**
     * Add a course to the list
     */
    public boolean addCourse(Course course) {
        if (course != null && !course.getCourseID().isEmpty()) {
            System.out.println("Added course: " + course.getCourseID() + " - " + course.getCourseName());
            return true;
        } else {
            System.out.println("Failed to add course: Invalid course information");
            return false;
        }
    }

    /**
     * Remove a course from the list
     */
    public boolean courseExists(String courseID) {
        // For the method stub, we'll simulate that CS101 already exists
        if (courseID.equals("CS101")) {
            System.out.println("Course " + courseID + " already exists");
            return true;
        } else {
            System.out.println("Course " + courseID + " does not exist");
            return false;
        }

    }
}

