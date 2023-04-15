package model;

import javax.swing.*;
import java.awt.*;

public class ScoreLabel extends JLabel {

    private int score = 0;

    public ScoreLabel(int x, int y) {
        super("00");
        this.setLayout(null);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setFont(new Font("Impact", Font.PLAIN, 40));
        this.setForeground(Color.GREEN);
        this.setOpaque(false);
        this.setBounds(x, y, 60, 50);
    }

    public void increaseScore() {
        score++;
        int tens = score / 10;
        int ones = score % 10;
        super.setText(String.valueOf(tens) + String.valueOf(ones));
    }

    public int getScore() {
        return score;
    }

}
