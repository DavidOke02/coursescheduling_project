package CourseManagement.Model;

/**
 * Manages waitlist for courses
 */
public class Waitlist {
    private String courseID;
    private String[] studentIDs;
    private int capacity;
    private int currentSize;


    /**
     * Default constructor
     */
    public Waitlist() {
        this.courseID = "IST101";
        this.studentIDs = new String[0];
        this.capacity = 15;
        this.currentSize = 0;

    }

    /**
     * Add a student to the waitlist
     */
    public void addStudent(String studentID) {
        System.out.println("Adding student " + studentID + " to waitlist for " + courseID);
    }

    /**
     * Remove a student from the waitlist
     */
    public void removeStudent(String studentID) {
        System.out.println("Removing student " + studentID + " from waitlist for " + courseID);
    }

    /**
     * Get the next student on the waitlist
     */
    public String getNextStudent() {
        if (studentIDs.length > 0) {
            return studentIDs[0];
        }
        return null;
    }

    public String getCourseID() {
        return courseID;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public boolean isFull() {
        return currentSize >= capacity;
    }

}

