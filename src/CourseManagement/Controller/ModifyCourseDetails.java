package CourseManagement.Controller;

import CourseManagement.Model.Course;

/**
 * Controller for modifying course details
 */
public class ModifyCourseDetails {
    /**
     * Update course details
     */
    public boolean updateCourse(Course course, String newName, int newCredits) {
        if (course.getCourseID() != null) {
            course.setCourseName(newName);
            course.setCredits(newCredits);
            System.out.println("Updated course: " + course.getCourseName() + " (" + course.getCredits() + " credits)");
            return true;
        } else {
            System.out.println("Failed to update course");
            return false;
        }
    }

    public boolean archiveCourse(String courseID) {
        Course course = new Course(); // Get course data

        if (course.getCourseID().equals(courseID)) {
            System.out.println("Archived course: " + course.getCourseName());
            return true;
        } else {
            System.out.println("Failed to archive course");
            return false;
        }
    }

}

