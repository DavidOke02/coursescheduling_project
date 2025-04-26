package AdvisorApproval.Database;

import AdvisorApproval.Model.ApprovalRequest;
import AdvisorApproval.Model.CourseOverride;
import db.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseUtil {
    public static ArrayList<CourseOverride> getCourseOverridesForStudent(String studentId) {
        ArrayList<CourseOverride> overrides = new ArrayList<>();
        String sql = "SELECT override_id, student_id, course_id, reason, status FROM course_overrides WHERE student_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    CourseOverride override = new CourseOverride(
                            rs.getString("override_id"),
                            rs.getString("student_id"),
                            rs.getString("course_id"),
                            rs.getString("reason"),
                            rs.getString("status")
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
        String sql = "INSERT INTO approval_requests (request_id, student_id, course_id, comment, advisor_comment, status, term) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, request.getRequestId());  // Make sure this is not null
            stmt.setString(2, request.getStudentId());
            stmt.setString(3, request.getCourseId());
            stmt.setString(4, request.getComment());
            stmt.setString(5, request.getAdvisorComment());
            stmt.setString(6, request.getStatus());
            stmt.setString(7, request.getTerm());
            stmt.executeUpdate();
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
                        rs.getString("request_id"),
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
                            rs.getString("request_id"),
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

    public static void insertCourseOverride(CourseOverride override) {
        String sql = "INSERT INTO course_overrides (override_id, student_id, course_id, reason, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, override.getOverrideID());
            stmt.setString(2, override.getStudentID());
            stmt.setString(3, override.getCourseID());
            stmt.setString(4, override.getReason());
            stmt.setString(5, override.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting course override:");
            e.printStackTrace();
        }
    }

    // Fixed method to match the call signature
    public static void updateCourseOverrideStatus(String overrideId, String status, String reason, String comment) {
        String sql = "UPDATE course_overrides SET status = ?, reason = ?, comment = ? WHERE override_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setString(2, reason);
            stmt.setString(3, comment);  // Added comment field here
            stmt.setString(4, overrideId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating course override status:");
            e.printStackTrace();
        }
    }
}
