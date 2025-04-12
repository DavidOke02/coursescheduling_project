package CourseManagement.Controller;

import CourseManagement.Model.Course;
import CourseManagement.Model.Prerequisite;

/**
 * Controller for managing prerequisites
 */
public class ManagePrerequisites {
    /**
     * Add a prerequisite to a course
     */
    public boolean addPrerequisite(String courseID, String prereqCourseID) {
        Course course = new Course(); // Get course data

        if (course.getCourseID().equals(courseID)) {
            System.out.println("Added prerequisite " + prereqCourseID + " to " + course.getCourseName());
            return true;
        } else {
            System.out.println("Failed to add prerequisite to course");
            return false;
        }
    }

    /**
     * Remove a prerequisite from a course
     */
    public boolean removePrerequisite(String courseID, String prereqCourseID) {
        Course course = new Course(); // Get course data

        if (course.getCourseID().equals(courseID)) {
            System.out.println("Removed prerequisite " + prereqCourseID + " from " + course.getCourseName());
            return true;
        } else {
            System.out.println("Failed to remove prerequisite from course");
            return false;
        }
    }

    public String[] getPrerequisites(String courseID) {
        Prerequisite prereq = new Prerequisite(); // Get prerequisite data

        if (prereq.getCourseID().equals(courseID)) {
            return new String[]{prereq.getPrerequisiteCourseID()};
        } else {
            return new String[]{};
        }
    }

}

