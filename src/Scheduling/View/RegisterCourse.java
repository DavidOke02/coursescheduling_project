package Scheduling.View;

import Scheduling.Model.CustomCourse;

/*
 * Handles course registration for students.
 */
public class RegisterCourse {
    /**
     * Allows a student to register for a course.
     */
    public boolean register(String courseID) {
        CustomCourse customCourse = new CustomCourse();
        if (customCourse.getCourseID().equals(courseID)) {
            System.out.println("Successfully registered for " + customCourse.getCourseName());
            return true;
        } else {
            System.out.println("Course not found.");
            return false;
        }
    }
}


