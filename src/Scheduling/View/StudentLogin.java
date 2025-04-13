package Scheduling.View;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StudentLogin {

    // Authenticates student login using email and password
    public boolean authenticate(String enteredEmail, String enteredPassword) {
        String filePath = "students.csv";  // Make sure it's in the root directory

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine(); // Skip header

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 3) continue;

                String studentID = parts[0].trim();
                String email = parts[1].trim();
                String password = parts[2].trim();

                // Debugging output
                System.out.println("Checking: " + email + " vs " + enteredEmail);
                System.out.println("Checking: " + password + " vs " + enteredPassword);

                if (email.equalsIgnoreCase(enteredEmail)
                        && enteredEmail.endsWith("@psu.edu")
                        && password.equals(enteredPassword)) {
                    System.out.println("Authenticated: " + studentID);
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV file.");
            e.printStackTrace();
        }

        return false;
    }
}
