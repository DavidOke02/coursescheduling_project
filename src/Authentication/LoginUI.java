package Authentication;

import Authentication.Controller.LoginController;
import Scheduling.View.StudentLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;

    private LoginController controller;

    public LoginUI() {
        setTitle("PSU Student Login");
        setSize(400, 200);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        LoginController controller = new LoginController();

        initializeComponents();
    }

    private void initializeComponents() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel emailLabel = new JLabel("PSU Email:");
        emailField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginAction());

        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // spacer
        panel.add(loginButton);

        add(panel);
    }

    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String enteredEmail = emailField.getText().trim();
            String enteredPassword = new String(passwordField.getPassword()).trim();

            if (!enteredEmail.endsWith("@psu.edu")) {
                JOptionPane.showMessageDialog(LoginUI.this, "Email must end with @psu.edu", "Invalid Email", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean isAuthenticated = controller.authenticate(enteredEmail, enteredPassword);
            if (isAuthenticated) {
                JOptionPane.showMessageDialog(LoginUI.this, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                // Proceed to next screen (for example, a student dashboard)
                // new StudentDashboardUI(); // Uncomment this to open a new UI
            } else {
                JOptionPane.showMessageDialog(LoginUI.this, "Invalid email or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginUI().setVisible(true));
    }
}
