package Scheduling.Controller;

import Scheduling.Model.Schedule;

/*
 * Handles updates to a student's schedule.
 */
public class ScheduleUpdate {
    /**
     * Updates the schedule for a student.
     */
    public boolean updateSchedule(String studentID, String newSemester) {
        Schedule schedule = new Schedule();
        if (schedule.getStudentID().equals(studentID)) {
            schedule.setSemester(newSemester);
            System.out.println("Schedule updated to " + newSemester);
            return true;
        } else {
            System.out.println("Schedule update failed.");
            return false;
        }
    }

}