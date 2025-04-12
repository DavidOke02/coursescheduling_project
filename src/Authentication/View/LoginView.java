package Authentication.View;

import Authentication.Controller.LoginController;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    private JPanel loginViewPanel;

    private LoginController controller;

    public LoginView(LoginController controller) {
        this.controller = controller;

        this.add(loginViewPanel);
        this.setTitle("Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(800, 600);
        this.setMinimumSize(new Dimension(200,150));
    }
}
