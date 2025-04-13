//import AdvisorApproval.Controller.AdvisorApproval;
//import AdvisorApproval.Model.Advisor;
//import AdvisorApproval.Model.ApprovalRequest;
//import AdvisorApproval.View.OverrideRequestForm;
//import AdvisorApproval.View.RequestDetailView;
//import AdvisorApproval.View.RequestListView;
//import CourseManagement.Controller.AddCourseToCourseList;
//import CourseManagement.Controller.AssignProfessorToCourse;
//import CourseManagement.Controller.ManagePrerequisites;
//import CourseManagement.Controller.ModifyCourseDetails;
//import CourseManagement.Model.Course;
//import CourseManagement.Model.Professor;
//import CourseManagement.View.CourseDetailView;
//import CourseManagement.View.InstructorDashboard;
//import CourseManagement.View.StudentCourseFeedback;
//import GraduationTracking.Controller.DegreeProgressController;
//import GraduationTracking.Controller.GraduationEligibilityChecker;
//import GraduationTracking.Model.DegreePlan;
//import GraduationTracking.Model.GraduationRequirements;
//import GraduationTracking.Model.StudentAcademicRecord;
//import GraduationTracking.View.CourseCompletionChecklist;
//import GraduationTracking.View.DegreeProgress;
//import GraduationTracking.View.GraduationView;
//import Scheduling.Controller.ScheduleUpdate;
//import Scheduling.Model.Student;
//import Scheduling.View.RegisterCourse;
//import Scheduling.View.StudentLogin;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class TestHarness {
//
//    private static void testSchedulingComponents() {
//        System.out.println("=== Testing Login Scheduling Components ===\n");
//
//        Student student = new Student();
//
//        // Test Student Login
//        StudentLogin login = new StudentLogin(student.getStudentID(), student.getName(), "password");
//        boolean loginSuccess = login.authenticate("012345678", "Default Student", "password");
//        System.out.println("Login Test Passed: " + loginSuccess + "\n");
//
//        // Test Course Registration
//        RegisterCourse register = new RegisterCourse();
//        boolean registerSuccess = register.register("IST101");
//        System.out.println("Course Registration Test Passed: " + registerSuccess + "\n");
//
//        // Test Schedule Update
//        ScheduleUpdate schedule = new ScheduleUpdate();
//        boolean updateSuccess = schedule.updateSchedule("012345678", "Spring 2026");
//        System.out.println("Schedule Update Test Passed: " + updateSuccess + "\n");
//    }
//
//    private static void testCourseManagementComponents() {
//        System.out.println("=== Testing Course Management Components ===\n");
//
//        Course course = new Course();
//        course.setCourseID("IST101");
//        course.setCourseName("Introduction to Technology");
//        course.setCredits(3);
//        course.setDepartmentCode("IST");
//        course.setAvailableSeats(30);
//
//        Professor professor = new Professor();
//        professor.setProfessorID("P001");
//        professor.setName("John Smith");
//        professor.setDepartment("Computer Science");
//
//        // Test Add Course To Course List
//        AddCourseToCourseList addCourse = new AddCourseToCourseList();
//        boolean courseExistsResult = addCourse.courseExists("CS101");
//        System.out.println("Course Exists Test Passed: " + courseExistsResult);
//
//        boolean addCourseResult = addCourse.addCourse(course);
//        System.out.println("Add Course Test Passed: " + addCourseResult + "\n");
//
//        // Test Assign Professor To Course
//        AssignProfessorToCourse assignProfessor = new AssignProfessorToCourse();
//        boolean assignProfessorResult = assignProfessor.assignProfessor("P12345", "CS101");
//        System.out.println("Assign Professor Test Passed: " + assignProfessorResult);
//
//        boolean removeProfessorResult = assignProfessor.removeProfessor("CS101");
//        System.out.println("Remove Professor Test Passed: " + removeProfessorResult + "\n");
//
//        // Test Manage Prerequisites
//        ManagePrerequisites managePrereqs = new ManagePrerequisites();
//        boolean addPrereqResult = managePrereqs.addPrerequisite("CS201", "CS101");
//        System.out.println("Add Prerequisite Test Passed: " + addPrereqResult);
//
//        String[] prerequisites = managePrereqs.getPrerequisites("CS201");
//        System.out.println("Get Prerequisites Test Passed: " + (prerequisites.length > 0));
//
//        boolean removePrereqResult = managePrereqs.removePrerequisite("CS201", "CS101");
//        System.out.println("Remove Prerequisite Test Passed: " + removePrereqResult + "\n");
//
//        // Test Modify Course Details
//        ModifyCourseDetails modifyCourse = new ModifyCourseDetails();
//        boolean updateCourseResult = modifyCourse.updateCourse(course, "Programming Fundamentals", 4);
//        System.out.println("Update Course Test Passed: " + updateCourseResult);
//
//        boolean archiveCourseResult = modifyCourse.archiveCourse("CS101");
//        System.out.println("Archive Course Test Passed: " + archiveCourseResult + "\n");
//
//        // Test Course Views
//        CourseDetailView courseDetail = new CourseDetailView();
//        courseDetail.displayCourseDetails(course);
//        courseDetail.showPrerequisites("CS201");
//
//        InstructorDashboard instructorDash = new InstructorDashboard();
//        instructorDash.displayDashboard(professor);
//        instructorDash.showCoursesTaught(professor);
//
//        StudentCourseFeedback feedback = new StudentCourseFeedback();
//        feedback.displayFeedbackForm("CS101");
//        boolean submitFeedbackResult = feedback.submitFeedback("CS101", "012345678", "Great course!");
//        System.out.println("Submit Feedback Test Passed: " + submitFeedbackResult + "\n");
//    }
//
//
//    private static void testGraduationTrackingComponents() {
//        System.out.println("=== Testing Graduation Tracking Components ===\n");
//
//        StudentAcademicRecord record = new StudentAcademicRecord("012345678");
//        GraduationRequirements requirements = new GraduationRequirements();
//        DegreePlan plan = new DegreePlan();
//
//        // Test Degree Progress Controller
//        DegreeProgressController progressController = new DegreeProgressController();
//
//        double overallProgress = progressController.calculateOverallProgress("012345678");
//        System.out.println("Calculate Overall Progress Test Passed: " + (overallProgress > 0.0));
//
//        double categoryProgress = progressController.calculateCategoryProgress("012345678", "Core Courses");
//        System.out.println("Calculate Category Progress Test Passed: " + (categoryProgress > 0.0));
//
//        String[] remainingRequirements = progressController.getRemainingRequirements("012345678");
//        System.out.println("Get Remaining Requirements Test Passed: " + (remainingRequirements != null));
//
//        boolean updatePlanResult = progressController.updateDegreePlan("012345678", "CS499", "Fall 2026");
//        System.out.println("Update Degree Plan Test Passed: " + updatePlanResult + "\n");
//
//        // Test Graduation Eligibility Checker
//        GraduationEligibilityChecker eligibilityChecker = new GraduationEligibilityChecker();
//
//        boolean eligibilityResult = eligibilityChecker.checkEligibility("012345678");
//        System.out.println("Check Eligibility Test Passed: " + (eligibilityResult == false));
//
//        String[] missingRequirements = eligibilityChecker.getMissingRequirements("012345678");
//        System.out.println("Get Missing Requirements Test Passed: " + (missingRequirements != null && missingRequirements.length > 0));
//
//        boolean applyResult = eligibilityChecker.applyForGraduation("012345678");
//        System.out.println("Apply for Graduation Test Passed: " + (applyResult == eligibilityResult) + "\n");
//
//        // Test Graduation Views
//        GraduationView gradView = new GraduationView();
//        gradView.displayGraduationStatus("012345678", eligibilityResult);
//        gradView.displayGraduationRequirements("Computer Science");
//        boolean confirmResult = gradView.confirmGraduationApplication("012345678");
//        System.out.println("Confirm Graduation Application Test Passed: " + confirmResult);
//
//        DegreeProgress progressView = new DegreeProgress();
//        progressView.displayOverallProgress("012345678", overallProgress);
//        progressView.displayCategoryProgress("012345678", "Core Courses", categoryProgress);
//        progressView.displayRemainingRequirements("012345678");
//
//        CourseCompletionChecklist checklist = new CourseCompletionChecklist();
//        checklist.displayCompletedCourses("012345678", record.getCompletedCourses());
//        checklist.displayRequiredCourses("Computer Science", requirements.getRequiredCourses());
//        checklist.displayInProgressCourses("012345678", plan.getPlannedCourses());
//        System.out.println("View Display Tests Completed Successfully");
//    }
//
//    private static void testApprovalSystem() {
//        System.out.println("=== Testing Advisor Approvals ===\n");
//        // Create advisor
//        Advisor validAdvisor = new Advisor();
//        Advisor invalidAdvisor = new Advisor();
//        invalidAdvisor = new Advisor() {
//            public String getAdvisorID() {
//                return "INVALID";
//            }
//        };
//        // Create approval requests
//        List<ApprovalRequest> requests = new ArrayList<>();
//        ApprovalRequest req1 = new ApprovalRequest();
//        req1.setStatus("Pending");
//        ApprovalRequest req2 = new ApprovalRequest();
//        req2.setStatus("Approved"); // This request should fail approval
//        // Create Approval Controller
//        AdvisorApproval advisorApproval = new AdvisorApproval();
//        // Test approving request with valid advisor
//        boolean approvalSuccess = advisorApproval.approveRequest(req1);
//        System.out.println(" Approval Test Passed: " + approvalSuccess + "\n");
//        // Test approving already approved request
//        boolean failedApproval = advisorApproval.approveRequest(req2);
//        System.out.println(" Expected Failure: Already Approved: " + !failedApproval + "\n");
//        // Test list of requests
//        requests.add(req1);
//        requests.add(req2);
//        RequestListView requestListView = new RequestListView();
//        requestListView.showRequests(requests);
//        System.out.println(" Request List View Test Passed\n");
//        // Test showing request details
//        RequestDetailView requestDetailView = new RequestDetailView();
//        requestDetailView.showRequestDetails(req1);
//        System.out.println(" Request Detail View Test Passed\n");
//        // Test Override Form
//        OverrideRequestForm overrideForm = new OverrideRequestForm();
//        overrideForm.displayForm();
//        System.out.println(" Override Request Form Test Passed\n");
//    }
//
//
//    public static void main(String[] args){
//        // Test Scheduling Components
//        testSchedulingComponents();
//
//        // Test Course Management Components
//        testCourseManagementComponents();
//
//        System.out.println("\nUniversity System Test Completed.");
//
//        System.out.println("Starting University System Test...\n");
//
//        // Test Graduation Tracking Components
//        testGraduationTrackingComponents();
//
//        System.out.println("\nUniversity System Test Completed.");
//
//        System.out.println("=== Starting Approval System Tests ===\n");
//        testApprovalSystem();
//        System.out.println("\n=== Approval System Tests Completed ===");
//
//    }
//}
