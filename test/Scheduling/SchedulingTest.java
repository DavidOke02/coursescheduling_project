package Scheduling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import Scheduling.Controller.*;
import Scheduling.Model.*;

import java.sql.Date;

public class SchedulingTest {

    private CustomCourse testCustomCourse;
    private LionPathCourse testLionPathCourse;
    private CourseAdapter testCourseAdapter;
    private Schedule testSchedule;
    private Student testStudent;
    private ScheduleConflictResolver testResolver;
    private RegisterCourseController testRegisterController;
    private CourseDetailSearchController testSearchController;
    private WaitlistManagement testWaitlistManagement;

    @BeforeEach
    void setUp() {
        testCustomCourse = new CustomCourse(
                "CS101", "Introduction to Programming", "Dr. John Smith",
                3, "CS", 30, "None", "Fall 2025", "MWF 10:00-11:15"
        );

        testLionPathCourse = new LionPathCourse(
                "CS202", "Data Structures", "Dr. Jane Doe",
                4, "Computer Science", 25, "01/15/2025",
                "CS101", "Spring 2025", "TR 13:00-14:15"
        );

        testCourseAdapter = new CourseAdapter(testLionPathCourse);
        testSchedule = new Schedule();
        testStudent = new Student();
        testResolver = new ScheduleConflictResolver();
        testRegisterController = new RegisterCourseController();
        testSearchController = new CourseDetailSearchController();
        testWaitlistManagement = new WaitlistManagement();
    }

    //CustomCourse Model Tests
    @Test
    @DisplayName("Test CustomCourse constructor and getters")
    void testCustomCourseConstructorAndGetters() {
        //Assert
        assertEquals("CS101", testCustomCourse.getCourseID());
        assertEquals("Introduction to Programming", testCustomCourse.getCourseName());
        assertEquals("Dr. John Smith", testCustomCourse.getInstructor());
        assertEquals(3, testCustomCourse.getCredits());
        assertEquals("CS", testCustomCourse.getDepartmentCode());
        assertEquals(30, testCustomCourse.getAvailableSeats());
        assertEquals("None", testCustomCourse.getPrerequisites());
        assertEquals("Fall 2025", testCustomCourse.getSemester());
        assertEquals("MWF 10:00-11:15", testCustomCourse.getTimeslot());
    }

    @Test
    @DisplayName("Test CustomCourse setters")
    void testCustomCourseSetters() {
        //Arrange
        CustomCourse course = new CustomCourse(
                "CS101", "Original Title", "Original Instructor",
                3, "CS", 30, "None", "Fall 2025", "MWF 9:00-10:15"
        );
        //Act
        course.setCourseID("CS102");
        course.setCourseName("Updated Title");
        course.setInstructor("New Instructor");
        course.setCredits(4);
        course.setDepartmentCode("CMPSC");
        course.setAvailableSeats(25);
        course.setPrerequisites("CS101");
        course.setSemester("Spring 2026");
        course.setTimeslot("TR 13:00-14:15");
        //Assert
        assertEquals("CS102", course.getCourseID());
        assertEquals("Updated Title", course.getCourseName());
        assertEquals("New Instructor", course.getInstructor());
        assertEquals(4, course.getCredits());
        assertEquals("CMPSC", course.getDepartmentCode());
        assertEquals(25, course.getAvailableSeats());
        assertEquals("CS101", course.getPrerequisites());
        assertEquals("Spring 2026", course.getSemester());
        assertEquals("TR 13:00-14:15", course.getTimeslot());
    }

    //LionPathCourse Model Tests
    @Test
    @DisplayName("Test LionPathCourse constructor and getters")
    void testLionPathCourseConstructorAndGetters() {
        //Assert
        assertEquals("CS202", testLionPathCourse.getCourseID());
        assertEquals("Data Structures", testLionPathCourse.getCourseTitle());
        assertEquals("Dr. Jane Doe", testLionPathCourse.getInstructorName());
        assertEquals(4, testLionPathCourse.getCredits());
        assertEquals("Computer Science", testLionPathCourse.getDepartmentName());
        assertEquals(25, testLionPathCourse.getAvailableSeats());
        assertEquals("01/15/2025", testLionPathCourse.getDateEnrolled());
        assertEquals("CS101", testLionPathCourse.getPrerequisites());
        assertEquals("Spring 2025", testLionPathCourse.getSemester());
        assertEquals("TR 13:00-14:15", testLionPathCourse.getTimeSlot());
    }

    @Test
    @DisplayName("Test LionPathCourse toString method")
    void testLionPathCourseToString() {
        //Arrange
        String expected = "CS202: Data Structures (TR 13:00-14:15)";
        //Act
        String result = testLionPathCourse.toString();
        //Assert
        assertEquals(expected, result);
    }

    //CourseAdapter Model Tests
    @Test
    @DisplayName("Test CourseAdapter constructor and inheritance")
    void testCourseAdapterConstructorAndInheritance() {
        //Assert
        assertEquals(testLionPathCourse.getCourseID(), testCourseAdapter.getCourseID());
        assertEquals(testLionPathCourse.getCourseTitle(), testCourseAdapter.getCourseName());
        assertEquals(testLionPathCourse.getInstructorName(), testCourseAdapter.getInstructor());
        assertEquals(testLionPathCourse.getCredits(), testCourseAdapter.getCredits());
        assertEquals(testLionPathCourse.getDepartmentName(), testCourseAdapter.getDepartmentCode());
        assertEquals(testLionPathCourse.getAvailableSeats(), testCourseAdapter.getAvailableSeats());
        assertEquals(testLionPathCourse.getPrerequisites(), testCourseAdapter.getPrerequisites());
        assertEquals(testLionPathCourse.getSemester(), testCourseAdapter.getSemester());
        assertEquals(testLionPathCourse.getTimeSlot(), testCourseAdapter.getTimeslot());
    }

    @Test
    @DisplayName("Test CourseAdapter getDateAdded method")
    void testCourseAdapterGetDateAdded() {
        //Act
        Date date = testCourseAdapter.getDateAdded();
        //Assert
        assertNotNull(date);
        assertEquals("2025-01-15", date.toString());
    }

    @Test
    @DisplayName("Test CourseAdapter getTimeslot method")
    void testCourseAdapterGetTimeslot() {
        //Act
        String timeslot = testCourseAdapter.getTimeslot();
        //Assert
        assertEquals(testLionPathCourse.getTimeSlot(), timeslot);
    }

    //Schedule Model Tests
    @Test
    @DisplayName("Test Schedule default constructor and getters")
    void testScheduleDefaultConstructorAndGetters() {
        // Assert
        assertEquals("012345678", testSchedule.getStudentID());
        assertEquals("Fall 2025", testSchedule.getSemester());
    }

    @Test
    @DisplayName("Test Schedule setter")
    void testScheduleSetter() {
        //Act
        testSchedule.setSemester("Spring 2026");
        //Assert
        assertEquals("Spring 2026", testSchedule.getSemester());
    }

    //Student Model Tests
    @Test
    @DisplayName("Test Student default constructor and getters")
    void testStudentDefaultConstructorAndGetters() {
        //Assert
        assertEquals("012345678", testStudent.getStudentID());
        assertEquals("Default Student", testStudent.getName());
    }

    @Test
    @DisplayName("Test Student setter")
    void testStudentSetter() {
        //Act
        testStudent.setName("Jane Doe");
        //Assert
        assertEquals("Jane Doe", testStudent.getName());
    }

    //ScheduleConflictResolver Controller Tests
    @Test
    @DisplayName("Test ScheduleConflictResolver resolveConflict method")
    void testScheduleConflictResolverResolveConflict() {
        // Act and Assert
        assertDoesNotThrow(() -> testResolver.resolveConflict());
    }

    //WaitlistManagement Controller Tests
    @Test
    @DisplayName("Test WaitlistManagement manageWaitlist method")
    void testWaitlistManagementManageWaitlist() {
        // Act and Assert
        assertDoesNotThrow(() -> testWaitlistManagement.manageWaitlist());
    }
}
