package GraduationTracking.Model;

/*
 * Model: Represents the data structure for tracking graduation requirements.
 */
public class GraduationRequirements {
    private String degreeProgramName;
    private int totalCreditsRequired;
    private double minimumGPA;
    private String[] requiredCourses;


    /**
     * Default constructor initializing requirements.
     */
    public GraduationRequirements() {
        this.degreeProgramName = "Computer Science";
        this.totalCreditsRequired = 120;
        this.minimumGPA = 2.0;
        this.requiredCourses = new String[]{"CS101", "CS201", "CS301", "MATH101", "MATH201"};

    }

    /**
     * Checks if the student has met graduation requirements.
     * @return true if requirements are met, false otherwise.
     */

    public String getDegreeProgramName() {
        return degreeProgramName;
    }

    public void setDegreeProgramName(String degreeProgram) {
        this.degreeProgramName = degreeProgram;
    }

    public double getMinimumGPA() {
        return minimumGPA;
    }

    public void setMinimumGPA(double minimumGPA) {
        this.minimumGPA = minimumGPA;
    }

    public String[] getRequiredCourses() {
        return requiredCourses;
    }

    public void setRequiredCourses(String[] requiredCourses) {
        this.requiredCourses = requiredCourses;
    }

    public int getTotalCreditsRequired() {
        return totalCreditsRequired;
    }

    public void setTotalCreditsRequired(int totalCreditsRequired) {
        this.totalCreditsRequired = totalCreditsRequired;
    }
}

