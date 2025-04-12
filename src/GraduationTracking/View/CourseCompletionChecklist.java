package GraduationTracking.View;

import java.util.Map;

/*
 * View: Displays a checklist of completed and remaining courses.
 */
public class CourseCompletionChecklist {
    /**
     * Displays the list of completed and pending courses.
     */
    public void displayCompletedCourses(String studentID, Map<String, String> completedCourses) {
        System.out.println("Displaying completed courses for student " + studentID);
        for (Map.Entry<String, String> entry : completedCourses.entrySet()) {
            System.out.println("Course: " + entry.getKey() + ", Grade: " + entry.getValue());
        }
    }

    public void displayRequiredCourses(String degreeProgram, String[] requiredCourses) {
        System.out.println("Displaying required courses for " + degreeProgram);
        for (String course : requiredCourses) {
            System.out.println("Required course: " + course);
        }
    }

    public void displayInProgressCourses(String studentID, Map<String, String> plannedCourses) {
        System.out.println("Displaying in-progress courses for student " + studentID);
        for (Map.Entry<String, String> entry : plannedCourses.entrySet()) {
            System.out.println("Course: " + entry.getKey() + ", Planned for: " + entry.getValue());
        }
    }

}

