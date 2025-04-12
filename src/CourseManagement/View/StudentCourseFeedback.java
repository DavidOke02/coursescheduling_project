package CourseManagement.View;

/**
 * View for student course feedback
 */
public class StudentCourseFeedback {
    /**
     * Display feedback form
     */
    public void displayFeedbackForm(String courseID) {
        System.out.println("Displaying feedback form for course: " + courseID);
    }


    /**
     * Submit feedback
     */
    public boolean submitFeedback(String courseID, String studentID, String feedback) {
        System.out.println("Submitting feedback for course " + courseID + " from student " + studentID);
        return true;
    }
}

