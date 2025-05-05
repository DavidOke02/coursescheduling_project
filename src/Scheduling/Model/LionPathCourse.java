package Scheduling.Model;

public class LionPathCourse {
    private String courseID;
    private String courseTitle;
    private String instructorName;
    private int credits;
    private String departmentName;
    private int availableSeats;
    private String dateEnrolled;
    private String prerequisites;  // New field
    private String semester;       // New field

    public LionPathCourse(String courseID, String courseTitle, String instructorName, int credits,
                          String departmentName, int availableSeats, String dateEnrolled,
                          String prerequisites, String semester) {
        this.courseID = courseID;
        this.courseTitle = courseTitle;
        this.instructorName = instructorName;
        this.credits = credits;
        this.departmentName = departmentName;
        this.availableSeats = availableSeats;
        this.dateEnrolled = dateEnrolled;
        this.prerequisites = prerequisites;
        this.semester = semester;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public int getCredits() {
        return credits;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public String getDateEnrolled() {
        return dateEnrolled;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public String getSemester() {
        return semester;
    }


    @Override
    public String toString() {
        return courseID + ": " + courseTitle;  // Simple format for JList display
    }
}
