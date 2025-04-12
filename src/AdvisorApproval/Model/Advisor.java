package AdvisorApproval.Model;

// Model: Represents an academic advisor responsible for approvals
public class Advisor {
    private String advisorID;
    private String name;

    /**
     * Default constructor for Advisor.
     */
    public Advisor() {
        this.advisorID = "A0001";
        this.name = "Default Advisor";
    }

    /**
     * Returns the advisor ID.
     */
    public String getAdvisorID() {
        return advisorID;
    }

    /**
     * Returns the advisor's name.
     */
    public String getName() {
        return name;
    }
}

