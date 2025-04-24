package Scheduling.Model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Arrays;

public class CourseAdapter extends CustomCourse {
    private LionPathCourse lionPathCourse;

    public CourseAdapter(LionPathCourse lionPathCourse) {
        super(lionPathCourse.getCourseID(), lionPathCourse.getCourseTitle(), lionPathCourse.getInstructorName(), lionPathCourse.getCredits(), lionPathCourse.getDepartmentName(), lionPathCourse.getAvailableSeats());
        this.lionPathCourse = lionPathCourse;
    }

    public Date getDateAdded(){
        String convertedDateString = lionPathCourse.getDateEnrolled().split(",")[0];

        String month = convertedDateString.substring(0, 2);
        String day = convertedDateString.substring(3, 5);
        String year = convertedDateString.substring(6, 10);
        convertedDateString = year + "-" + month + "-" + day;

        return Date.valueOf(convertedDateString);
    }
}
