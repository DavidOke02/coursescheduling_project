package GraduationTracking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import GraduationTracking.Controller.DegreeProgressController;
import GraduationTracking.Model.DegreePlan;
import GraduationTracking.Model.GraduationRequirements;
import GraduationTracking.Model.StudentAcademicRecord;

import java.util.HashMap;
import java.util.Map;

public class GraduationTrackingTests {

    private DegreePlan testDegreePlan;
    private GraduationRequirements testGradRequirements;
    private StudentAcademicRecord testAcademicRecord;
    private DegreeProgressController testProgressController;
    private Map<String, Double> completedCourses;

    @BeforeEach
    void setUp() {
        testDegreePlan = new DegreePlan();
        testGradRequirements = new GraduationRequirements();

        completedCourses = new HashMap<>();
        completedCourses.put("CS101", 3.5);
        completedCourses.put("CS201", 4.0);
        completedCourses.put("MATH101", 3.0);

        testAcademicRecord = new StudentAcademicRecord("012345678", 60, "Computer Science", 3.5, completedCourses);
        testProgressController = new DegreeProgressController();
    }

    //DegreePlan Model Tests
    @Test
    @DisplayName("Test DegreePlan default constructor and getters")
    void testDegreePlanDefaultConstructorAndGetters() {
        //Assert
        assertEquals("012345678", testDegreePlan.getStudentID());

        assertNull(testDegreePlan.getDegree());
        assertEquals(120, testDegreePlan.getPlannedCredits());

        Map<String, String> plannedCourses = testDegreePlan.getPlannedCourses();
        assertNotNull(plannedCourses);
        assertEquals(4, plannedCourses.size());
        assertEquals("Fall 2025", plannedCourses.get("CS301"));
        assertEquals("Spring 2026", plannedCourses.get("CS401"));
        assertEquals("Fall 2025", plannedCourses.get("CS450"));
        assertEquals("Spring 2026", plannedCourses.get("CS490"));
    }

    @Test
    @DisplayName("Test DegreePlan setters")
    void testDegreePlanSetters() {
        //Arrange
        DegreePlan plan = new DegreePlan();
        //Act
        plan.setStudentID("987654321");
        plan.setDegree("Information Technology");
        plan.setPlannedCredits(130);
        //Assert
        assertEquals("987654321", plan.getStudentID());
        assertEquals("Information Technology", plan.getDegree());
        assertEquals(130, plan.getPlannedCredits());
    }

    @Test
    @DisplayName("Test DegreePlan course planning methods")
    void testDegreePlanCoursePlanningMethods() {
        //Arrange
        DegreePlan plan = new DegreePlan();
        //Act and Assert
        plan.addPlannedCourse("CS500", "Fall 2026");
        assertTrue(plan.isCoursePlanned("CS500"));
        assertEquals("Fall 2026", plan.getPlannedSemester("CS500"));
        //Act and Assert
        plan.removePlannedCourse("CS301");
        assertFalse(plan.isCoursePlanned("CS301"));
        assertNull(plan.getPlannedSemester("CS301"));
    }

    //GraduationRequirements Model Tests
    @Test
    @DisplayName("Test GraduationRequirements default constructor and getters")
    void testGraduationRequirementsDefaultConstructorAndGetters() {
        //Assert
        assertEquals("Computer Science", testGradRequirements.getDegreeProgramName());
        assertEquals(120, testGradRequirements.getTotalCreditsRequired());
        assertEquals(2.0, testGradRequirements.getMinimumGPA());

        String[] requiredCourses = testGradRequirements.getRequiredCourses();
        assertNotNull(requiredCourses);
        assertEquals(5, requiredCourses.length);
        assertEquals("CS101", requiredCourses[0]);
        assertEquals("CS201", requiredCourses[1]);
        assertEquals("CS301", requiredCourses[2]);
        assertEquals("MATH101", requiredCourses[3]);
        assertEquals("MATH201", requiredCourses[4]);
    }

    @Test
    @DisplayName("Test GraduationRequirements setters")
    void testGraduationRequirementsSetters() {
        //Arrange
        GraduationRequirements requirements = new GraduationRequirements();
        //Act
        requirements.setDegreeProgramName("Data Science");
        requirements.setTotalCreditsRequired(128);
        requirements.setMinimumGPA(2.5);
        requirements.setRequiredCourses(new String[]{"DS101", "DS201", "STAT101"});
        //Assert
        assertEquals("Data Science", requirements.getDegreeProgramName());
        assertEquals(128, requirements.getTotalCreditsRequired());
        assertEquals(2.5, requirements.getMinimumGPA());

        String[] requiredCourses = requirements.getRequiredCourses();
        assertEquals(3, requiredCourses.length);
        assertEquals("DS101", requiredCourses[0]);
        assertEquals("DS201", requiredCourses[1]);
        assertEquals("STAT101", requiredCourses[2]);
    }

    //StudentAcademicRecord Model Tests
    @Test
    @DisplayName("Test StudentAcademicRecord constructor and getters")
    void testStudentAcademicRecordConstructorAndGetters() {
        //Assert
        assertEquals("012345678", testAcademicRecord.getStudentID());
        assertEquals(60, testAcademicRecord.getCompletedCredits());
        assertEquals("Computer Science", testAcademicRecord.getDegree());
        assertEquals(3.5, testAcademicRecord.getCurrentGPA());

        Map<String, Double> courses = testAcademicRecord.getCompletedCourses();
        assertNotNull(courses);
        assertEquals(0, courses.size());
    }

    @Test
    @DisplayName("Test StudentAcademicRecord setters")
    void testStudentAcademicRecordSetters() {
        //Arrange
        StudentAcademicRecord record = new StudentAcademicRecord("111222333", 0, "", 0.0, null);
        //Act
        record.setStudentID("111222333");
        record.setCompletedCredits(90);
        record.setDegree("Information Systems");
        record.setCurrentGPA(3.8);
        //Assert
        assertEquals("111222333", record.getStudentID());
        assertEquals(90, record.getCompletedCredits());
        assertEquals("Information Systems", record.getDegree());
        assertEquals(3.8, record.getCurrentGPA());
    }

    @Test
    @DisplayName("Test StudentAcademicRecord course completion methods")
    void testStudentAcademicRecordCourseCompletionMethods() {
        //Arrange
        StudentAcademicRecord record = new StudentAcademicRecord("012345678", 60, "Computer Science", 3.5, null);
        //Act
        record.addCompletedCourse("CS101", 3.5);
        record.addCompletedCourse("CS201", 4.0);
        //Assert
        assertTrue(record.hasCourseCompleted("CS101"));
        assertTrue(record.hasCourseCompleted("CS201"));
        assertFalse(record.hasCourseCompleted("CS301"));

        assertEquals(3.5, record.getCourseGPA("CS101"));
        assertEquals(4.0, record.getCourseGPA("CS201"));
    }

    //DegreeProgressController Tests
    @Test
    @DisplayName("Test DegreeProgressController updateDegreePlan method with valid student ID")
    void testUpdateDegreePlanWithValidStudentID() {
        //Arrange
        String studentID = "012345678";
        String courseID = "CS600";
        String semester = "Fall 2026";
        //Act
        boolean result = testProgressController.updateDegreePlan(studentID, courseID, semester);
        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test DegreeProgressController updateDegreePlan method with invalid student ID")
    void testUpdateDegreePlanWithInvalidStudentID() {
        //Arrange
        String invalidStudentID = "999999999";
        String courseID = "CS600";
        String semester = "Fall 2026";
        //Act
        boolean result = testProgressController.updateDegreePlan(invalidStudentID, courseID, semester);
        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test StudentAcademicRecord constructor bug with completed courses")
    void testStudentAcademicRecordConstructorBug() {
        //Arrange
        Map<String, Double> inputCourses = new HashMap<>();
        inputCourses.put("TEST101", 4.0);
        inputCourses.put("TEST102", 3.5);
        //Act
        StudentAcademicRecord record = new StudentAcademicRecord("012345678", 60, "Computer Science", 3.5, inputCourses);
        //Assert
        Map<String, Double> resultCourses = record.getCompletedCourses();
        assertTrue(resultCourses.isEmpty());

        record.addCompletedCourse("TEST103", 3.0);
        assertTrue(record.hasCourseCompleted("TEST103"));
    }

    @Test
    @DisplayName("Test integration between DegreePlan and DegreeProgressController")
    void testIntegrationBetweenDegreePlanAndController() {
        //Arrange
        String studentID = "012345678";
        String courseID = "CS700";
        String semester = "Spring 2027";
        //Act
        boolean result = testProgressController.updateDegreePlan(studentID, courseID, semester);
        //Assert
        assertTrue(result);
    }
}
