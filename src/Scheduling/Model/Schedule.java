package Scheduling.Model;

/*
 * Represents a student's schedule for a given semester.
 */
public class Schedule {
    private String studentID;
    private String semester;

    /**
     * Default constructor initializing schedule with default values.
     */
    public Schedule() {
        this.studentID = "012345678";
        this.semester = "Fall 2025";
    }

    /**
     * Returns the student ID associated with the schedule.
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * Sets the semester for the schedule.
     */
    public void setSemester(String newSemester) {
        this.semester = newSemester;
    }

    /**
     * Returns the semester of the schedule.
     */
    public String getSemester() {
        return semester;
    }
}

