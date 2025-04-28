package Scheduling.Controller;

import java.util.*;

/**
 * Handles waitlist management logic.
 */
public class WaitlistManagement {
    private Map<String, Queue<String>> courseWaitlists = new HashMap<>();

    /**
     * Adds a student to the waitlist for a course.
     */
    public void addToWaitlist(String courseId, String studentId) {
        courseWaitlists.putIfAbsent(courseId, new LinkedList<>());
        courseWaitlists.get(courseId).add(studentId);
    }

    /**
     * Returns all courses where a student is waitlisted.
     */
    public List<String> getStudentWaitlistedCourses(String studentId) {
        List<String> waitlistedCourses = new ArrayList<>();
        for (Map.Entry<String, Queue<String>> entry : courseWaitlists.entrySet()) {
            if (entry.getValue().contains(studentId)) {
                waitlistedCourses.add(entry.getKey());
            }
        }
        return waitlistedCourses;
    }

    /**
     * (Optional) View the full waitlist for a specific course (Admin feature).
     */
    public List<String> viewCourseWaitlist(String courseId) {
        Queue<String> waitlist = courseWaitlists.get(courseId);
        if (waitlist == null) {
            return Collections.emptyList();
        }
        return new ArrayList<>(waitlist);
    }
}