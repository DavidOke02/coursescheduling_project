package Scheduling.View;

import Scheduling.Controller.StudentDashboardController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentScheduleHomeView extends JFrame {
    private JPanel scheduleHomeView;
    private JLabel schedulingHomeLabel;
    private JButton viewScheduleButton;
    private JButton viewAcademicProgressButton;
    private JPanel breadcrumbPanel;
    private JLabel homeNavLabel;
    private JLabel schedulingNavLabel;

    //Setup
    public StudentScheduleHomeView() {
        this.add(scheduleHomeView);
        this.setTitle("Scheduling");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(800, 600);
        this.setMinimumSize(new Dimension(200,150));
        initializeButtons();
    }

    //Button Logic
    public void initializeButtons(){
        makeClickable(getHomeNavLabel());
        makeClickable(getSchedulingNavLabel());
        StudentScheduleHomeView homeView = this;

        //For breadcrumb bar
        getHomeNavLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                homeView.dispose();
                StudentDashboardController controller= new StudentDashboardController();
            }
        });

        //For buttons
        getViewScheduleButton().addActionListener(e -> {

        });

        getViewScheduleButton().addActionListener(e -> {

        });
    }

    public void makeClickable(JLabel label) {
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }


    //Getters
    public JLabel getHomeNavLabel() {
        return homeNavLabel;
    }

    public JLabel getSchedulingNavLabel() {
        return schedulingNavLabel;
    }

    public JButton getViewScheduleButton() {
        return viewScheduleButton;
    }

    public JButton getViewAcademicProgressButton() {
        return viewAcademicProgressButton;
    }
}
