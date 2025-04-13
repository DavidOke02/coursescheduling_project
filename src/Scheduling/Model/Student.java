package Scheduling.Model;

/**
 * Represents a student with a unique ID and name.
 */
public class Student {
    private String studentID;
    private String name;

    public Student() {
        this.studentID = "012345678";
        this.name = "Default Student";
    }

    public String getStudentID() {
        return studentID;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getName() {
        return name;
    }
}
