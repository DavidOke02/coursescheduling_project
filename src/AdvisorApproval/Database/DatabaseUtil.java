package AdvisorApproval.Database;

import AdvisorApproval.Model.ApprovalRequest;
import AdvisorApproval.Model.CourseOverride;
import db.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseUtil {

    static {
        DatabaseUtil util = new DatabaseUtil();
        util.createApprovalTables();
        util.addTestData();
    }

    public static ArrayList<CourseOverride> getCourseOverridesForStudent(String studentId) {
        ArrayList<CourseOverride> overrides = new ArrayList<>();
        String sql = "SELECT override_id, student_id, course_id, reason, status, comment FROM course_overrides WHERE student_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    CourseOverride override = new CourseOverride(
                            rs.getInt("override_id"),
                            rs.getString("student_id"),
                            rs.getString("course_id"),
                            rs.getString("reason"),
                            rs.getString("status"),
                            rs.getString("comment")
                    );
                    overrides.add(override);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving course overrides for student:");
            e.printStackTrace();
        }

        return overrides;
    }

    public static void insertApprovalRequest(ApprovalRequest request) {
        String sql = "INSERT INTO approval_requests (student_id, course_id, comment, advisor_comment, status, term) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, request.getStudentId());
            stmt.setString(2, request.getCourseId());
            stmt.setString(3, request.getComment());
            stmt.setString(4, request.getAdvisorComment());
            stmt.setString(5, request.getStatus());
            stmt.setString(6, request.getTerm());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    request.setRequestId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error inserting approval request:");
            e.printStackTrace();
        }
    }

    public static void updateApprovalRequestStatus(String studentId, String courseId, String status, String comment) {
        String sql = "UPDATE approval_requests SET status = ?, advisor_comment = ? WHERE student_id = ? AND course_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setString(2, comment);
            stmt.setString(3, studentId);
            stmt.setString(4, courseId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating approval request status:");
            e.printStackTrace();
        }
    }

    public static ArrayList<ApprovalRequest> getAllApprovalRequests() {
        ArrayList<ApprovalRequest> requests = new ArrayList<>();
        String sql = "SELECT request_id, student_id, course_id, comment, advisor_comment, status, term FROM approval_requests";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ApprovalRequest request = new ApprovalRequest(
                        rs.getInt("request_id"),
                        rs.getString("student_id"),
                        rs.getString("course_id"),
                        rs.getString("comment"),
                        rs.getString("advisor_comment"),
                        rs.getString("status"),
                        rs.getString("term")
                );
                requests.add(request);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving approval requests:");
            e.printStackTrace();
        }
        return requests;
    }

    public static ArrayList<ApprovalRequest> getApprovalRequestsForStudent(String studentId) {
        ArrayList<ApprovalRequest> requests = new ArrayList<>();
        String sql = "SELECT request_id, student_id, course_id, comment, advisor_comment, status, term FROM approval_requests WHERE student_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ApprovalRequest request = new ApprovalRequest(
                            rs.getInt("request_id"),
                            rs.getString("student_id"),
                            rs.getString("course_id"),
                            rs.getString("comment"),
                            rs.getString("advisor_comment"),
                            rs.getString("status"),
                            rs.getString("term")
                    );
                    requests.add(request);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving approval requests for student:");
            e.printStackTrace();
        }
        return requests;
    }

    public static void updateCourseOverrideStatus(int overrideId, String status, String reason, String comment) {
        String sql = "UPDATE course_overrides SET status = ?, reason = ?, comment = ? WHERE override_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setString(2, reason);
            stmt.setString(3, comment);
            stmt.setInt(4, overrideId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating course override status:");
            e.printStackTrace();
        }
    }

    public void createApprovalTables() {
        try (Connection connection = DBConnection.getConnection();
             Statement stmt = connection.createStatement()) {

            // CREATE TABLES IF NOT EXISTS
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS approval_requests (
                    request_id INT PRIMARY KEY AUTO_INCREMENT,
                    student_id VARCHAR(50) NOT NULL,
                    course_id VARCHAR(50) NOT NULL,
                    comment TEXT,
                    advisor_comment TEXT,
                    status VARCHAR(20),
                    term VARCHAR(20)
                )
                """);

            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS course_overrides (
                    override_id INT PRIMARY KEY AUTO_INCREMENT,
                    student_id VARCHAR(50) NOT NULL,
                    course_id VARCHAR(50) NOT NULL,
                    reason TEXT,
                    status VARCHAR(20),
                    comment TEXT
                )
                """);

            System.out.println("Approval tables created or already exist!");

        } catch (SQLException e) {
            System.err.println("Could not create approval tables:");
            e.printStackTrace();
        }
    }

    public void addTestData() {
        try (Connection conn = DBConnection.getConnection()) {
            String checkApproval = "SELECT COUNT(*) FROM approval_requests";
            String checkOverride = "SELECT COUNT(*) FROM course_overrides";

            try (Statement stmt = conn.createStatement();
                 ResultSet rs1 = stmt.executeQuery(checkApproval)) {
                rs1.next();
                int approvalCount = rs1.getInt(1);

                if (approvalCount == 0) {
                    insertApprovalTestData(conn);
                }
            }

            try (Statement stmt = conn.createStatement();
                 ResultSet rs2 = stmt.executeQuery(checkOverride)) {
                rs2.next();
                int overrideCount = rs2.getInt(1);

                if (overrideCount == 0) {
                    insertOverrideTestData(conn);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error inserting test data:");
            e.printStackTrace();
        }
    }

    private void insertApprovalTestData(Connection conn) throws SQLException {
        String insertSQL = "INSERT INTO approval_requests (student_id, course_id, comment, advisor_comment, status, term) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
            stmt.setString(1, "STU123");
            stmt.setString(2, "CYBER 300");
            stmt.setString(3, "Need override due to course conflict.");
            stmt.setString(4, " ");
            stmt.setString(5, "Pending");
            stmt.setString(6, "Fall 2025");
            stmt.executeUpdate();

            stmt.setString(1, "STU456");
            stmt.setString(2, "HCDD 350");
            stmt.setString(3, "Prerequisite not completed.");
            stmt.setString(4, " ");
            stmt.setString(5, "Approved");
            stmt.setString(6, "Spring 2025");
            stmt.executeUpdate();
        }
    }

    private void insertOverrideTestData(Connection conn) throws SQLException {
        String insertSQL = "INSERT INTO course_overrides (student_id, course_id, reason, status, comment) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
            stmt.setString(1, "STU123");
            stmt.setString(2, "ENG 200");
            stmt.setString(3, "Course conflict");
            stmt.setString(4, "Pending");
            stmt.setString(5, " ");
            stmt.executeUpdate();

            stmt.setString(1, "STU123");
            stmt.setString(2, "IST 411");
            stmt.setString(3, "Prerequisite not completed");
            stmt.setString(4, "Pending");
            stmt.setString(5, " ");
            stmt.executeUpdate();

            stmt.setString(1, "STU456");
            stmt.setString(2, "ENGL 400");
            stmt.setString(3, "Schedule mismatch");
            stmt.setString(4, "Pending");
            stmt.setString(5, " ");
            stmt.executeUpdate();
        }
    }

    public static void insertCourseOverride(CourseOverride override) {
        String sql = "INSERT INTO course_overrides (student_id, course_id, reason, status, comment) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Set the values for the placeholders
            stmt.setString(1, override.getStudentID());
            stmt.setString(2, override.getCourseID());
            stmt.setString(3, override.getReason());
            stmt.setString(4, override.getStatus());
            stmt.setString(5, override.getComment());

            // Execute the insert
            int rowsAffected = stmt.executeUpdate();

            // If the insert was successful, retrieve the generated keys (if necessary)
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        override.setOverrideID(generatedKeys.getInt(1)); // Set the generated override ID
                        System.out.println("Course override inserted with ID: " + override.getOverrideID());
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error inserting course override:");
            e.printStackTrace();
        }
    }

}
