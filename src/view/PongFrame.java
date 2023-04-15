package view;

import javax.swing.*;

public class PongFrame extends JFrame {

    public PongFrame() {
        super("PONG GAME");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new PongPanel());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
}
