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
    public void  calculateOverallProgress(String studentID) {

    }

    public void getRemainingRequirements(String studentID) {

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

