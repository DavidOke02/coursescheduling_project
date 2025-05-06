package CourseManagement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import CourseManagement.Model.*;
import CourseManagement.Controller.*;

public class CourseManagementTest {

    private Course testCourse;
    private Professor testProfessor;
    private Department testDepartment;
    private Prerequisite testPrerequisite;
    private Semester testSemester;
    private Waitlist testWaitlist;

    @BeforeEach
    void setUp() {
        //Initialize test objects
        testCourse = new Course("CS101", "Introduction to Programming", 3, "CS", 30, "PROF123", "None", "Fall 2025", "MWF 10:00-11:15");
        testProfessor = new Professor("PROF123", "Dr. John Smith", "Computer Science");
        testDepartment = new Department(); // Using default values
        testPrerequisite = new Prerequisite("CS201", "CS101");
        testSemester = new Semester(); // Using default values
        testWaitlist = new Waitlist(); // Using default values
    }

    //Course Model Tests
    @Test
    @DisplayName("Test Course constructor and getters")
    void testCourseConstructorAndGetters() {
        //Arrange
        String expectedId = "CS101";
        String expectedName = "Introduction to Programming";
        int expectedCredits = 3;
        String expectedDept = "CS";
        int expectedSeats = 30;
        String expectedProf = "PROF123";
        String expectedPrereqs = "None";
        String expectedSemester = "Fall 2025";
        String expectedTimeslot = "MWF 10:00-11:15";
        //Assert
        assertEquals(expectedId, testCourse.getCourseID());
        assertEquals(expectedName, testCourse.getCourseName());
        assertEquals(expectedCredits, testCourse.getCredits());
        assertEquals(expectedDept, testCourse.getDepartmentCode());
        assertEquals(expectedSeats, testCourse.getAvailableSeats());
        assertEquals(expectedProf, testCourse.getProfessor());
        assertEquals(expectedPrereqs, testCourse.getPrerequisites());
        assertEquals(expectedSemester, testCourse.getSemesterOffered());
        assertEquals(expectedTimeslot, testCourse.getTimeslot());
    }

    @Test
    @DisplayName("Test Course setters")
    void testCourseSetters() {
        //Arrange
        Course course = new Course();
        //Act
        course.setCourseID("CS202");
        course.setCourseName("Data Structures");
        course.setCredits(4);
        course.setDepartmentCode("CS");
        course.setAvailableSeats(25);
        course.setProfessor("PROF456");
        course.setPrerequisites("CS101");
        course.setSemesterOffered("Spring 2026");
        course.setTimeslot("TR 13:00-14:15");
        //Assert
        assertEquals("CS202", course.getCourseID());
        assertEquals("Data Structures", course.getCourseName());
        assertEquals(4, course.getCredits());
        assertEquals("CS", course.getDepartmentCode());
        assertEquals(25, course.getAvailableSeats());
        assertEquals("PROF456", course.getProfessor());
        assertEquals("CS101", course.getPrerequisites());
        assertEquals("Spring 2026", course.getSemesterOffered());
        assertEquals("TR 13:00-14:15", course.getTimeslot());
    }

    @Test
    @DisplayName("Test Course toString method")
    void testCourseToString() {
        //Arrange
        String expected = "CS101: Introduction to Programming (MWF 10:00-11:15)";
        //Act
        String result = testCourse.toString();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test Course partial constructor with name and credits")
    void testCoursePartialConstructor() {
        //Arrange
        Course course = new Course("Advanced Java", 4);
        //Assert
        assertNull(course.getCourseID());
        assertEquals("Advanced Java", course.getCourseName());
        assertEquals(4, course.getCredits());
        assertNull(course.getDepartmentCode());
        assertEquals(0, course.getAvailableSeats());
    }

    @Test
    @DisplayName("Test Course constructor with 6 parameters")
    void testCourse6ParamConstructor() {
        //Arrange
        Course course = new Course("CS303", "Database Systems", "PROF999", 3, "CS", 40);
        //Assert
        assertEquals("CS303", course.getCourseID());
        assertEquals("Database Systems", course.getCourseName());
        assertEquals("PROF999", course.getProfessor());
        assertEquals(3, course.getCredits());
        assertEquals("CS", course.getDepartmentCode());
        assertEquals(40, course.getAvailableSeats());
    }

    //Professor Model Tests
    @Test
    @DisplayName("Test Professor constructor and getters")
    void testProfessorConstructorAndGetters() {
        //Arrange
        String expectedId = "PROF123";
        String expectedName = "Dr. John Smith";
        String expectedDept = "Computer Science";
        //Assert
        assertEquals(expectedId, testProfessor.getProfessorID());
        assertEquals(expectedName, testProfessor.getName());
        assertEquals(expectedDept, testProfessor.getDepartment());
    }

    @Test
    @DisplayName("Test Professor setters")
    void testProfessorSetters() {
        //Arrange
        Professor professor = new Professor();
        //Act
        professor.setProfessorID("PROF789");
        professor.setName("Dr. Jane Doe");
        professor.setDepartment("Mathematics");
        //Assert
        assertEquals("PROF789", professor.getProfessorID());
        assertEquals("Dr. Jane Doe", professor.getName());
        assertEquals("Mathematics", professor.getDepartment());
    }

    @Test
    @DisplayName("Test Professor default constructor")
    void testProfessorDefaultConstructor() {
        //Arrange
        Professor professor = new Professor();
        //Assert
        assertNull(professor.getProfessorID());
        assertNull(professor.getName());
        assertNull(professor.getDepartment());
    }

    //Department Model Tests
    @Test
    @DisplayName("Test Department default constructor and getters")
    void testDepartmentDefaultConstructorAndGetters() {
        //Arrange
        String expectedId = "023";
        String expectedCode = "CS";
        String expectedName = "Computer Science";
        String expectedChair = "Dr. Jane Doe";
        //Assert
        assertEquals(expectedId, testDepartment.getDepartmentID());
        assertEquals(expectedCode, testDepartment.getDepartmentCode());
        assertEquals(expectedName, testDepartment.getName());
        assertEquals(expectedChair, testDepartment.getChairperson());
    }

    @Test
    @DisplayName("Test Department setters")
    void testDepartmentSetters() {
        //Arrange and Act
        testDepartment.setDepartmentID("024");
        testDepartment.setName("Information Technology");
        //Assert
        assertEquals("024", testDepartment.getDepartmentID());
        assertEquals("Information Technology", testDepartment.getName());
    }

    //Prerequisite Model Tests
    @Test
    @DisplayName("Test Prerequisite constructor and getters")
    void testPrerequisiteConstructorAndGetters() {
        //Arrange
        String expectedCourseId = "CS201";
        String expectedPrereqId = "CS101";
        //Assert
        assertEquals(expectedCourseId, testPrerequisite.getCourseID());
        assertEquals(expectedPrereqId, testPrerequisite.getPrerequisiteCourseID());
    }

    @Test
    @DisplayName("Test Prerequisite default constructor")
    void testPrerequisiteDefaultConstructor() {
        //Arrange
        Prerequisite prereq = new Prerequisite();
        //Assert
        assertEquals("IST201", prereq.getCourseID());
        assertEquals("IST101", prereq.getPrerequisiteCourseID());
        assertEquals("C", prereq.getMinimumGrade());
    }

    @Test
    @DisplayName("Test Prerequisite validation")
    void testPrerequisiteValidation() {
        //Arrange
        String studentId = "STU123";
        //Act
        boolean result = testPrerequisite.validatePrerequisite(studentId);
        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test Prerequisite minimum grade setter and getter")
    void testPrerequisiteMinimumGrade() {
        //Arrange
        Prerequisite prereq = new Prerequisite();
        //Act
        prereq.setMinimumGrade("B");
        //Assert
        assertEquals("B", prereq.getMinimumGrade());
    }

    //Semester Model Tests
    @Test
    @DisplayName("Test Semester default constructor and getters")
    void testSemesterDefaultConstructorAndGetters() {
        //Arrange
        String expectedId = "S2025FA";
        String expectedName = "Fall 2025";
        String expectedStart = "2025-08-25";
        String expectedEnd = "2025-12-15";
        //Assert
        assertEquals(expectedId, testSemester.getSemesterID());
        assertEquals(expectedName, testSemester.getName());
        assertEquals(expectedStart, testSemester.getStartDate());
        assertEquals(expectedEnd, testSemester.getEndDate());
    }

    @Test
    @DisplayName("Test Semester setters")
    void testSemesterSetters() {
        //Arrange and Act
        testSemester.setSemesterID("S2026SP");
        testSemester.setName("Spring 2026");
        //Assert
        assertEquals("S2026SP", testSemester.getSemesterID());
        assertEquals("Spring 2026", testSemester.getName());
    }

    //Waitlist Model Tests
    @Test
    @DisplayName("Test Waitlist default constructor and getters")
    void testWaitlistDefaultConstructorAndGetters() {
        //Arrange
        String expectedCourseId = "IST101";
        int expectedCapacity = 15;
        int expectedSize = 0;
        //Assert
        assertEquals(expectedCourseId, testWaitlist.getCourseID());
        assertEquals(expectedCapacity, testWaitlist.getCapacity());
        assertEquals(expectedSize, testWaitlist.getCurrentSize());
        assertFalse(testWaitlist.isFull());
    }

    @Test
    @DisplayName("Test Waitlist setters and isFull")
    void testWaitlistSettersAndIsFull() {
        //Arrange
        Waitlist waitlist = new Waitlist();
        //Act
        waitlist.setCapacity(5);
        //Assert
        assertEquals(5, waitlist.getCapacity());
        assertFalse(waitlist.isFull());
    }

    @Test
    @DisplayName("Test Waitlist add and remove student")
    void testWaitlistAddRemoveStudent() {
        testWaitlist.addStudent("STU123");
        testWaitlist.removeStudent("STU123");

    }

    @Test
    @DisplayName("Test Waitlist getNextStudent returns null for empty waitlist")
    void testWaitlistGetNextStudentEmpty() {
        assertNull(testWaitlist.getNextStudent());
    }

    //CourseManagementSystem Tests
    @Test
    @DisplayName("Test CourseManagementSystem main method execution")
    void testCourseManagementSystemMainMethod() {
        String[] args = new String[0];
        CourseManagementSystem.main(args);

    }

    //ModifyCourseDetails Tests
    @Test
    @DisplayName("Test ModifyCourseDetails constructor")
    void testModifyCourseDetailsConstructor() {
        //Arrange and Act
        ModifyCourseDetails controller = new ModifyCourseDetails();
        //Assert
        assertNotNull(controller);
    }

    //AddCourseController Test
    @Test
    @DisplayName("Test AddCourseController default constructor")
    void testAddCourseControllerDefaultConstructor() {
        //Arrange and Act
        AddCourseController controller = new AddCourseController();
        //Assert
        assertNotNull(controller);
    }
}
