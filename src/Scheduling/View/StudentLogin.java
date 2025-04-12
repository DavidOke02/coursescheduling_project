package Scheduling.View;

import Scheduling.Model.Student;

public class StudentLogin {
    /**
     * Authenticates the student login.
     */
    private String studentID;
    private String name;
    private String password;

    public StudentLogin(String studentID, String name, String password){
        this.studentID = studentID;
        this.name = name;
        this.password = password;
    }

    public boolean authenticate(String studentID, String name, String password) {
        System.out.println("Authenticating Student...");
        Student student = new Student();
        return (student.getStudentID().equals(studentID) && student.getName().equals(name) && this.password.equals(password));
    }


}