package Authentication;

import Authentication.Controller.LoginController;
import Scheduling.View.StudentLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI extends JFrame {

    private JTextField idField;
    private JPasswordField passwordField;
    private JButton loginButton;

    private LoginController controller;

    public LoginUI() {
        setTitle("PSU Login");
        setSize(400, 200);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        controller = new LoginController();

        initializeComponents();
    }

    private void initializeComponents() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel idLabel = new JLabel("User ID:");  // Updated label
        idField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginAction());

        panel.add(idLabel);
        panel.add(idField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // spacer
        panel.add(loginButton);

        add(panel);
    }

    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String enteredId = idField.getText().trim();
            String enteredPassword = new String(passwordField.getPassword()).trim();

            boolean isAuthenticated = controller.authenticate(enteredId, enteredPassword);
            if (isAuthenticated) {
                String userRole = controller.getLoggedInUserRole();
                if ("Advisor".equals(userRole)) {

                    new AdvisorDashboard(enteredId);
                } else if ("Student".equals(userRole)) {

                    new StudentDashboard(enteredId);
                } else {
                    JOptionPane.showMessageDialog(LoginUI.this, "Unknown role!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(LoginUI.this, "Invalid ID or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginUI().setVisible(true));
    }
}
