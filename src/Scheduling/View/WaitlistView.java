package Scheduling.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import Scheduling.Controller.WaitlistManagement; // Assuming you have the logic there

public class WaitlistView extends JFrame {
    private JPanel basePanel;
    private JButton exitButton;
    private JTextArea textArealList;  // You should replace this with JList later for better list view
    private JLabel stusentID;
    private JLabel Title;
    private JLabel list;
    private JTextField inputID;

    private WaitlistManagement waitlistManagement;

    public WaitlistView(WaitlistManagement waitlistManagement) {
        this.waitlistManagement = waitlistManagement;
        initializeButtons();
        setContentPane(basePanel);
        setTitle("Waitlist View");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initializeButtons() {
        inputID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStudentWaitlists();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the window
            }
        });
    }

    private void updateStudentWaitlists() {
        String studentId = inputID.getText().trim();
        List<String> waitlistedCourses = waitlistManagement.getStudentWaitlistedCourses(studentId);

        if (waitlistedCourses.isEmpty()) {
            textArealList.setText("You are not on any waitlists.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (String course : waitlistedCourses) {
                sb.append(course).append("\n");
            }
            textArealList.setText(sb.toString());
        }
    }

    public JPanel getBasePanel() {
        return basePanel;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JTextArea getTextArealList() {
        return textArealList;
    }

    public JLabel getStusentID() {
        return stusentID;
    }


    public JLabel getList() {
        return list;
    }

    public JTextField getInputID() {
        return inputID;
    }

    public WaitlistManagement getWaitlistManagement() {
        return waitlistManagement;
    }

    public void setBasePanel(JPanel basePanel) {
        this.basePanel = basePanel;
    }

    public void setExitButton(JButton exitButton) {
        this.exitButton = exitButton;
    }

    public void setTextArealList(JTextArea textArealList) {
        this.textArealList = textArealList;
    }

    public void setStusentID(JLabel stusentID) {
        this.stusentID = stusentID;
    }

    public void setTitle(JLabel title) {
        Title = title;
    }

    public void setList(JLabel list) {
        this.list = list;
    }

    public void setInputID(JTextField inputID) {
        this.inputID = inputID;
    }

    public void setWaitlistManagement(WaitlistManagement waitlistManagement) {
        this.waitlistManagement = waitlistManagement;
    }
}
