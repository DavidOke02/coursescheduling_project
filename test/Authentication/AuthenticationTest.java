package Authentication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import Authentication.Model.Login;

public class AuthenticationTest {

    private Login studentModel;
    private Login advisorModel;
    private Login adminModel;

    @BeforeEach
    void setUp() {
        // Create test models without initializing the controller
        studentModel = new Login("STU123", "Jane", "Smith", "Student", "password");
        advisorModel = new Login("ADV123", "John", "Doe", "Advisor", "password");
        adminModel = new Login("ADM123", "Sarah", "Peters", "Admin", "password");
    }

    @Test
    @DisplayName("Test student login model constructor and getters")
    void testStudentLoginModelConstructorAndGetters() {
        assertEquals("STU123", studentModel.getId());
        assertEquals("Jane", studentModel.getFirstname());
        assertEquals("Smith", studentModel.getLastname());
        assertEquals("Student", studentModel.getRole());
        assertEquals("password", studentModel.getPassword());
    }

    @Test
    @DisplayName("Test advisor login model constructor and getters")
    void testAdvisorLoginModelConstructorAndGetters() {
        assertEquals("ADV123", advisorModel.getId());
        assertEquals("John", advisorModel.getFirstname());
        assertEquals("Doe", advisorModel.getLastname());
        assertEquals("Advisor", advisorModel.getRole());
        assertEquals("password", advisorModel.getPassword());
    }

    @Test
    @DisplayName("Test admin login model constructor and getters")
    void testAdminLoginModelConstructorAndGetters() {
        assertEquals("ADM123", adminModel.getId());
        assertEquals("Sarah", adminModel.getFirstname());
        assertEquals("Peters", adminModel.getLastname());
        assertEquals("Admin", adminModel.getRole());
        assertEquals("password", adminModel.getPassword());
    }

    @Test
    @DisplayName("Test ID setter")
    void testIdSetter() {
        // Arrange
        Login user = new Login("OLD_ID", "Jane", "Smith", "Student", "password");
        // Act
        user.setId("NEW_ID");
        // Assert
        assertEquals("NEW_ID", user.getId());
    }

    @Test
    @DisplayName("Test firstname setter")
    void testFirstnameSetter() {
        // Arrange
        Login user = new Login("STU123", "Jane", "Smith", "Student", "password");
        // Act
        user.setFirstname("NewFirstName");
        // Assert
        assertEquals("NewFirstName", user.getFirstname());
    }

    @Test
    @DisplayName("Test lastname setter")
    void testLastnameSetter() {
        // Arrange
        Login user = new Login("STU123", "Jane", "Smith", "Student", "password");
        // Act
        user.setLastname("NewLastName");
        // Assert
        assertEquals("NewLastName", user.getLastname());
    }

    @Test
    @DisplayName("Test role setter")
    void testRoleSetter() {
        // Arrange
        Login user = new Login("STU123", "Jane", "Smith", "Student", "password");
        // Act
        user.setRole("NewRole");
        // Assert
        assertEquals("NewRole", user.getRole());
    }

    @Test
    @DisplayName("Test password setter")
    void testPasswordSetter() {
        // Arrange
        Login user = new Login("STU123", "Jane", "Smith", "Student", "password");
        // Act
        user.setPassword("NewPassword");
        // Assert
        assertEquals("NewPassword", user.getPassword());
    }

    @Test
    @DisplayName("Test Login model with null values")
    void testLoginModelWithNullValues() {
        // Arrange & Act
        Login user = new Login(null, null, null, null, null);
        // Assert
        assertNull(user.getId());
        assertNull(user.getFirstname());
        assertNull(user.getLastname());
        assertNull(user.getRole());
        assertNull(user.getPassword());
    }

    @Test
    @DisplayName("Test Login model with empty strings")
    void testLoginModelWithEmptyStrings() {
        // Arrange & Act
        Login user = new Login("", "", "", "", "");
        // Assert
        assertEquals("", user.getId());
        assertEquals("", user.getFirstname());
        assertEquals("", user.getLastname());
        assertEquals("", user.getRole());
        assertEquals("", user.getPassword());
    }
}