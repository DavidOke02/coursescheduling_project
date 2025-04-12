package GraduationTracking.View;

/*
 * View: Displays a student’s degree progress.
 */
public class DegreeProgress {
    /**
     * Shows the student's current degree progress.
     */
    public void displayOverallProgress(String studentID, double percentComplete) {
        System.out.println("Displaying degree progress for student " + studentID);
        System.out.println("Overall completion: " + percentComplete + "%");
    }

    public void displayCategoryProgress(String studentID, String category, double percentComplete) {
        System.out.println("Progress in " + category + " category: " + percentComplete + "%");
    }

    public void displayRemainingRequirements(String studentID) {
        System.out.println("Displaying remaining requirements for student " + studentID);
    }

}

