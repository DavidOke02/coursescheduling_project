package GraduationTracking.Controller;

import GraduationTracking.Model.DegreePlan;
import GraduationTracking.Model.GraduationRequirements;
import GraduationTracking.Model.StudentAcademicRecord;

/*
 * Controller: Manages degree progress tracking.
 */
public class DegreeProgressController {
    /**
     * Updates and validates student degree progress.
     */
    public double calculateOverallProgress(String studentID) {
        StudentAcademicRecord record = new StudentAcademicRecord(studentID);
        GraduationRequirements requirements = new GraduationRequirements();

        if (record.getStudentID().equals(studentID)) {
            // Calculate progress based on credits completed vs. required
            double progress = ((double) record.getCompletedCredits() / record.getCompletedCredits()) * 100;
            // For demo purposes, round to 2 decimal places
            progress = Math.round(progress * 100.0) / 100.0;
            System.out.println("Calculated overall progress for student " + studentID + ": " + progress + "%");
            return progress;
        } else {
            System.out.println("Student record not found for ID: " + studentID);
            return 0.0;
        }
    }

    public String[] getRemainingRequirements(String studentID) {
        StudentAcademicRecord record = new StudentAcademicRecord(studentID);
        GraduationRequirements requirements = new GraduationRequirements();

        if (record.getStudentID().equals(studentID)) {
            // Compare required courses with completed courses
            String[] requiredCourses = requirements.getRequiredCourses();
            String[] remainingCourses = new String[requiredCourses.length];
            int count = 0;

            for (String course : requiredCourses) {
                if (!record.hasCourseCompleted(course)) {
                    remainingCourses[count++] = course;
                }
            }

            // Create a properly sized array with only the remaining courses
            String[] result = new String[count];
            System.arraycopy(remainingCourses, 0, result, 0, count);

            System.out.println("Retrieved remaining requirements for student " + studentID);
            return result;
        } else {
            System.out.println("Student record not found for ID: " + studentID);
            return new String[0];
        }
    }

    public boolean updateDegreePlan(String studentID, String courseID, String semester) {
        DegreePlan plan = new DegreePlan();

        if (plan.getStudentID().equals(studentID)) {
            plan.addPlannedCourse(courseID, semester);
            System.out.println("Updated degree plan for student " + studentID + ": added " + courseID + " for " + semester);
            return true;
        } else {
            System.out.println("Degree plan not found for student ID: " + studentID);
            return false;
        }
    }

}

