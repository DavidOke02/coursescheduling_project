package CourseManagement.Model;

/**
 * A professor who teaches courses
 */
public class Professor {
    private String professorID;
    private String name;
    private String department;

    /**
     * Default constructor
     */
    public Professor() {

    }

    public Professor(String professorID, String name, String department) {
        this.professorID = professorID;
        this.name = name;
        this.department = department;
    }

    // Getters and setters
    public String getProfessorID() {
        return professorID;
    }

    public void setProfessorID(String professorID) {
        this.professorID = professorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}

