package fr.zolkers;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SwingMainMenu {
    public static JPanel getjPanel(AtomicInteger robotChoice) {
        JPanel robotList = new JPanel();
        JButton randomRobot = new JButton("RandomRobot");
        JButton eDGNRobot = new JButton("EDGNRobot");
        randomRobot.addActionListener(e -> {
            robotChoice.set(1);
            robotList.setVisible(false);
        });
        eDGNRobot.addActionListener( e -> {
            robotChoice.set(2);
            robotList.setVisible(false);
        });
        robotList.add(randomRobot);
        robotList.add(eDGNRobot);
        robotList.setSize(300, 300);
        return robotList;
    }
}
