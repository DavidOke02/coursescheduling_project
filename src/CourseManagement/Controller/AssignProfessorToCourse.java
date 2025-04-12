package CourseManagement.Controller;

import CourseManagement.Model.Course;
import CourseManagement.Model.Professor;

/**
 * Controller for assigning professors to courses
 */
public class AssignProfessorToCourse {
    /**
     * Assign a professor to a course.
     */
    public boolean assignProfessor(String professorID, String courseID) {
        Professor professor = new Professor(); // Get professor data
        Course course = new Course(); // Get course data

        if (professor.getProfessorID().equals(professorID) && course.getCourseID().equals(courseID)) {
            System.out.println("Professor " + professor.getName() + " assigned to " + course.getCourseName());
            return true;
        } else {
            System.out.println("Failed to assign professor to course");
            return false;
        }

    }

    public boolean removeProfessor(String courseID) {
        Course course = new Course(); // Get course data

        if (course.getCourseID().equals(courseID)) {
            System.out.println("Professor removed from " + course.getCourseName());
            return true;
        } else {
            System.out.println("Failed to remove professor from course");
            return false;
        }
    }

}

