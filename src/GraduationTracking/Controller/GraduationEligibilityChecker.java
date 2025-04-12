package GraduationTracking.Controller;

import GraduationTracking.Model.GraduationRequirements;
import GraduationTracking.Model.StudentAcademicRecord;

public class GraduationEligibilityChecker {
    public boolean checkEligibility(String studentID) {
        StudentAcademicRecord record = new StudentAcademicRecord(studentID);
        GraduationRequirements requirements = new GraduationRequirements();

        if (record.getStudentID().equals(studentID)) {
            // Check if student meets all graduation requirements
            boolean hasEnoughCredits = record.getCompletedCredits() >= requirements.getTotalCreditsRequired();
            boolean hasMinimumGPA = record.getCurrentGPA() >= requirements.getMinimumGPA();
            boolean hasRequiredCourses = true; // We'll check if all required courses are completed

            for (String course : requirements.getRequiredCourses()) {
                if (!record.hasCourseCompleted(course)) {
                    hasRequiredCourses = false;
                    break;
                }
            }

            boolean isEligible = hasEnoughCredits && hasMinimumGPA && hasRequiredCourses;
            System.out.println("Checked graduation eligibility for student " + studentID + ": " + isEligible);
            return isEligible;
        } else {
            System.out.println("Student record not found for ID: " + studentID);
            return false;
        }
    }

    public String[] getMissingRequirements(String studentID) {
        StudentAcademicRecord record = new StudentAcademicRecord(studentID);
        GraduationRequirements requirements = new GraduationRequirements();

        if (record.getStudentID().equals(studentID)) {
            // Create a list of all missing requirements
            String[] missingRequirements = new String[10]; // Maximum 10 possible missing requirements
            int count = 0;

            // Check credits
            if (record.getCompletedCredits() < requirements.getTotalCreditsRequired()) {
                missingRequirements[count++] = "Need " + (requirements.getTotalCreditsRequired() - record.getCompletedCredits()) + " more credits";
            }

            // Check GPA
            if (record.getCurrentGPA() < requirements.getMinimumGPA()) {
                missingRequirements[count++] = "GPA below minimum requirement of " + requirements.getMinimumGPA();
            }

            // Check required courses
            for (String course : requirements.getRequiredCourses()) {
                if (!record.hasCourseCompleted(course)) {
                    missingRequirements[count++] = "Missing required course: " + course;
                }
            }

            // Create a properly sized array with only the missing requirements
            String[] result = new String[count];
            System.arraycopy(missingRequirements, 0, result, 0, count);

            System.out.println("Retrieved missing graduation requirements for student " + studentID);
            return result;
        } else {
            System.out.println("Student record not found for ID: " + studentID);
            return new String[0];
        }
    }

    public boolean applyForGraduation(String studentID) {
        boolean isEligible = checkEligibility(studentID);

        if (isEligible) {
            System.out.println("Application for graduation submitted for student " + studentID);
            return true;
        } else {
            System.out.println("Application for graduation denied for student " + studentID + " - eligibility requirements not met");
            return false;
        }
    }

}
