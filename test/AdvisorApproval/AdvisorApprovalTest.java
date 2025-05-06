package AdvisorApproval;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import AdvisorApproval.Controller.AdvisorApproval;
import AdvisorApproval.Model.ApprovalRequest;
import AdvisorApproval.Model.CourseOverride;
import AdvisorApproval.Model.Advisor;


public class AdvisorApprovalTest {

    private AdvisorApproval advisorApprovalController;
    private ApprovalRequest pendingRequest;
    private ApprovalRequest approvedRequest;
    private CourseOverride pendingOverride;
    private CourseOverride approvedOverride;

    @BeforeEach
    void setUp() {
        // Initialize controller
        advisorApprovalController = new AdvisorApproval();
        // Create test approval requests
        pendingRequest = new ApprovalRequest(1, "STU123", "CYBER 300",
                "Need override due to course conflict.", "", "Pending", "Fall 2025");
        approvedRequest = new ApprovalRequest(2, "STU456", "HCDD 350",
                "Prerequisite not completed.", "Approved by advisor.", "Approved", "Spring 2025");
        // Create test course overrides
        pendingOverride = new CourseOverride(1, "STU123", "ENG 200",
                "Course conflict", "Pending", "");
        approvedOverride = new CourseOverride(2, "STU456", "ENGL 400",
                "Schedule mismatch", "Approved", "Approved by advisor");
    }

    @Test
    @DisplayName("Test approving a pending request")
    void testApproveRequest_WithPendingRequest_ShouldReturnTrue() {
        // Act
        boolean result = advisorApprovalController.approveRequest(pendingRequest);
        // Assert
        assertTrue(result);
        assertEquals("Approved", pendingRequest.getStatus());
    }

    @Test
    @DisplayName("Test approving an already approved request")
    void testApproveRequest_WithApprovedRequest_ShouldReturnFalse() {
        // Act
        boolean result = advisorApprovalController.approveRequest(approvedRequest);
        // Assert
        assertFalse(result);
        assertEquals("Approved", approvedRequest.getStatus()); // Status should not change
    }

    @Test
    @DisplayName("Test approving a pending override")
    void testApproveOverride_WithPendingOverride_ShouldReturnTrue() {
        // Act
        boolean result = advisorApprovalController.approveOverride(pendingOverride);
        // Assert
        assertTrue(result);
        assertEquals("Approved", pendingOverride.getStatus());
    }

    @Test
    @DisplayName("Test approving an already approved override")
    void testApproveOverride_WithApprovedOverride_ShouldReturnFalse() {
        // Act
        boolean result = advisorApprovalController.approveOverride(approvedOverride);
        // Assert
        assertFalse(result);
        assertEquals("Approved", approvedOverride.getStatus()); // Status should not change
    }

    @Test
    @DisplayName("Test ApprovalRequest constructor and getters")
    void testApprovalRequestConstructorAndGetters() {
        // Arrange
        ApprovalRequest request = new ApprovalRequest(101, "STU999", "CS 101",
                "Test comment", "Test advisor comment", "Pending", "Fall 2025");
        // Assert
        assertEquals(101, request.getRequestId());
        assertEquals("STU999", request.getStudentId());
        assertEquals("CS 101", request.getCourseId());
        assertEquals("Test comment", request.getComment());
        assertEquals("Test advisor comment", request.getAdvisorComment());
        assertEquals("Pending", request.getStatus());
        assertEquals("Fall 2025", request.getTerm());
    }

    @Test
    @DisplayName("Test ApprovalRequest setters")
    void testApprovalRequestSetters() {
        // Arrange
        ApprovalRequest request = new ApprovalRequest(null, "OLD_ID", "OLD_COURSE",
                "Old comment", "Old advisor comment", "Old status", "Old term");
        // Act
        request.setRequestId(202);
        request.setStudentId("NEW_ID");
        request.setCourseId("NEW_COURSE");
        request.setComment("New comment");
        request.setAdvisorComment("New advisor comment");
        request.setStatus("New status");
        request.setTerm("New term");
        // Assert
        assertEquals(202, request.getRequestId());
        assertEquals("NEW_ID", request.getStudentId());
        assertEquals("NEW_COURSE", request.getCourseId());
        assertEquals("New comment", request.getComment());
        assertEquals("New advisor comment", request.getAdvisorComment());
        assertEquals("New status", request.getStatus());
        assertEquals("New term", request.getTerm());
    }

    @Test
    @DisplayName("Test CourseOverride constructor and getters")
    void testCourseOverrideConstructorAndGetters() {
        // Arrange
        CourseOverride override = new CourseOverride(303, "STU777", "MATH 101",
                "Test reason", "Test status", "Test comment");
        // Assert
        assertEquals(303, override.getOverrideID());
        assertEquals("STU777", override.getStudentID());
        assertEquals("MATH 101", override.getCourseID());
        assertEquals("Test reason", override.getReason());
        assertEquals("Test status", override.getStatus());
        assertEquals("Test comment", override.getComment());
    }

    @Test
    @DisplayName("Test CourseOverride setters")
    void testCourseOverrideSetters() {
        // Arrange
        CourseOverride override = new CourseOverride(null, "OLD_ID", "OLD_COURSE",
                "Old reason", "Old status", "Old comment");
        // Act
        override.setOverrideID(404);
        override.setStudentID("NEW_ID");
        override.setCourseID("NEW_COURSE");
        override.setReason("New reason");
        override.setStatus("New status");
        override.setComment("New comment");
        // Assert
        assertEquals(404, override.getOverrideID());
        assertEquals("NEW_ID", override.getStudentID());
        assertEquals("NEW_COURSE", override.getCourseID());
        assertEquals("New reason", override.getReason());
        assertEquals("New status", override.getStatus());
        assertEquals("New comment", override.getComment());
    }

    @Test
    @DisplayName("Test Advisor default constructor and getters")
    void testAdvisorDefaultConstructor() {
        // Arrange
        Advisor advisor = new Advisor();
        // Assert
        assertEquals("A0001", advisor.getAdvisorID());
        assertEquals("Default Advisor", advisor.getName());
    }
}
