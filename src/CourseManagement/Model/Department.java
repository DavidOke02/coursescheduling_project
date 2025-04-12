package CourseManagement.Model;

/**
 * An academic department
 */
public class Department {
    private String departmentID;
    private String departmentCode;
    private String name;
    private String chairperson;


    /**
     * Default constructor
     */
    public Department() {
        this.departmentID = "023";
        this.departmentCode = "CS";
        this.name = "Computer Science";
        this.chairperson = "Dr. Jane Doe";

    }

    // Getters and setters
    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChairperson() {
        return chairperson;
    }

}

