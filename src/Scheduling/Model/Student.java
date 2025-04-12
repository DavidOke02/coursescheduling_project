package Scheduling.Model;

/*
 * Represents a student with a unique ID and name.
 */
public class Student {
    private String studentID;
    private String name;

    /**
     * Default constructor initializing student with default values.
     */
    public Student() {
        this.studentID = "012345678";
        this.name = "Default Student";
    }

    /**
     * Returns the student ID.
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * Sets the student's name.
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Returns the student's name.
     */
    public String getName() {
        return name;
    }
}

/*
 * Represents a course with a unique ID, name, and credit count.
 */

