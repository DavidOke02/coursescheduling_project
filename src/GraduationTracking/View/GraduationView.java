package GraduationTracking.View;

/*
 * View: Displays overall graduation tracking details.
 */
public class GraduationView {
    /**
     * Displays graduation progress.
     */
    public void displayGraduationStatus(String studentID, boolean isEligible) {
        System.out.println("Displaying graduation status for student " + studentID);
        if (isEligible) {
            System.out.println("Student is eligible for graduation");
        } else {
            System.out.println("Student is not yet eligible for graduation");
        }

    }

    public void displayGraduationRequirements(String degreeProgram) {
        System.out.println("Displaying graduation requirements for " + degreeProgram);
    }

    public boolean confirmGraduationApplication(String studentID) {
        System.out.println("Confirming graduation application for student " + studentID);
        // In a real implementation, this would prompt for confirmation
        return true;
    }

}

