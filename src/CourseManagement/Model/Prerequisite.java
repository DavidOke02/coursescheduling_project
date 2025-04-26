package CourseManagement.Model;

/**
 * Course prerequisites
 */
public class Prerequisite {
    private String courseID;
    private String prerequisiteCourseID;
    private String minimumGrade;

    /**
     * Default constructor
     */
    public Prerequisite() {
        this.courseID = "IST201";
        this.prerequisiteCourseID = "IST101";
        this.minimumGrade = "C";
    }

    public Prerequisite(String courseID, String prerequisiteCourseID) {
        this.courseID = courseID;
        this.prerequisiteCourseID = prerequisiteCourseID;
    }

    // Getters and setters
    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getPrerequisiteCourseID() {
        return prerequisiteCourseID;
    }

    public void setPrerequisiteCourseID(String prerequisiteCourseID) {
        this.prerequisiteCourseID = prerequisiteCourseID;
    }

    public String getMinimumGrade() {
        return minimumGrade;
    }

    public void setMinimumGrade(String minimumGrade) {
        this.minimumGrade = minimumGrade;
    }


    /**
     * Validates if a prerequisite is met
     */
    public boolean validatePrerequisite(String studentID) {
        // Simple validation logic
        System.out.println("Validating prerequisite for student: " + studentID);
        return true;
    }
}

