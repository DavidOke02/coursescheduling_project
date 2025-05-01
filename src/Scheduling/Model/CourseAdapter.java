package Scheduling.Model;

import java.sql.Date;

public class CourseAdapter extends CustomCourse {
    private LionPathCourse lionPathCourse;

    public CourseAdapter(LionPathCourse lionPathCourse) {
        // Make sure CustomCourse has a constructor that accepts timeSlot
        super(lionPathCourse.getCourseID(), lionPathCourse.getCourseTitle(), lionPathCourse.getInstructorName(),
                lionPathCourse.getCredits(), lionPathCourse.getDepartmentName(), lionPathCourse.getAvailableSeats(),
                lionPathCourse.getPrerequisites(), lionPathCourse.getSemester(), lionPathCourse.getTimeSlot());

        this.lionPathCourse = lionPathCourse;
    }

    public Date getDateAdded() {
        String convertedDateString = lionPathCourse.getDateEnrolled().split(",")[0];

        String month = convertedDateString.substring(0, 2);
        String day = convertedDateString.substring(3, 5);
        String year = convertedDateString.substring(6, 10);
        convertedDateString = year + "-" + month + "-" + day;

        return Date.valueOf(convertedDateString);
    }

    public String getTimeslot() {
        return lionPathCourse.getTimeSlot();
    }
}
