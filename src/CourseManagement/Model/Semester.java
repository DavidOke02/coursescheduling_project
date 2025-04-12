package CourseManagement.Model;

/**
 * A semester
 */
public class Semester {
    private String semesterID;
    private String name;
    private String startDate;
    private String endDate;


    /**
     * Default constructor
     */
    public Semester() {
        this.semesterID = "S2025FA";
        this.name = "Fall 2025";
        this.startDate = "2025-08-25";
        this.endDate = "2025-12-15";

    }

    // Getters and setters
    public String getSemesterID() {
        return semesterID;
    }

    public void setSemesterID(String semesterID) {
        this.semesterID = semesterID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

}

