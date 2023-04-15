package view;

import model.Ball;
import model.Paddle;
import model.ScoreLabel;
import util.KeyHandler;

import javax.swing.*;
import java.awt.*;

public class PongPanel extends JPanel implements Runnable{

    public static final int SCREEN_WIDTH = 1260;
    public static final int SCREEN_HEIGHT = (int)(SCREEN_WIDTH / 1.8);
    private boolean winner = false;

    Paddle paddle1;
    Paddle paddle2;

    Ball ball;

    ScoreLabel score1;
    ScoreLabel score2;

    JLabel winnerLabel = new JLabel();

    KeyHandler keyHandler;

    Thread gameThread;

    public PongPanel() {
        paddle1 = new Paddle(0, (SCREEN_HEIGHT - Paddle.HEIGHT)/2, Color.red);
        paddle2 = new Paddle(SCREEN_WIDTH - 50, (SCREEN_HEIGHT - Paddle.HEIGHT)/2, Color.blue);

        ball = new Ball((SCREEN_WIDTH - Ball.DIAMETER)/2, (SCREEN_HEIGHT - Ball.DIAMETER)/2, Color.ORANGE);
        ball.serve();

        score1 = new ScoreLabel(SCREEN_WIDTH / 2 - 60, 0);
        score2 = new ScoreLabel(SCREEN_WIDTH / 2, 0);

        winnerLabel.setFont(new Font("Impact", Font.PLAIN, 80));
        winnerLabel.setBounds((SCREEN_WIDTH - 530) / 2, (SCREEN_HEIGHT - 90) / 2, 530, 90);
        winnerLabel.setForeground(Color.GREEN);

        keyHandler = new KeyHandler();

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setLayout(null);
        this.setBackground(new Color(0, 0, 0));
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        this.add(score1);
        this.add(score2);
        this.add(winnerLabel);


        gameThread = new Thread(this);
        gameThread.start();
    }

    public void movePaddles() {
        if (keyHandler.wPressed && !paddle1.isTopCollision()) {
            paddle1.setYLocation(paddle1.getYLocation() - Paddle.getSpeed());
        } if (keyHandler.sPressed && !paddle1.isBottomCollision()) {
            paddle1.setYLocation(paddle1.getYLocation() + Paddle.getSpeed());
        } if (keyHandler.upPressed && !paddle2.isTopCollision()) {
            paddle2.setYLocation(paddle2.getYLocation() - Paddle.getSpeed());
        } if (keyHandler.downPressed && !paddle2.isBottomCollision()) {
            paddle2.setYLocation(paddle2.getYLocation() + Paddle.getSpeed());
        }
    }

    public void checkScored() {
        if (ball.getXLocation() < -(Ball.DIAMETER + ball.getSpeed()) ||
                ball.getXLocation() > SCREEN_WIDTH + ball.getSpeed()) {
            if (ball.getXLocation() > SCREEN_WIDTH) {
                score1.increaseScore();
            } else if (ball.getXLocation() < -Ball.DIAMETER) {
                score2.increaseScore();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ball = new Ball((SCREEN_WIDTH - Ball.DIAMETER)/2,
                    (SCREEN_HEIGHT - Ball.DIAMETER)/2, Color.ORANGE);
            Paddle.setSpeed(ball.getSpeed());
            ball.serve();
        }
    }

    public void checkWinner() {
        int winningScore = 15;
        if (score1.getScore() == winningScore) {
            winner = true;
            winnerLabel.setText("PLAYER 1 WINS!!!");
        } else if (score2.getScore() == winningScore) {
            winner = true;
            winnerLabel.setText("PLAYER 2 WINS!!!");
        }
    }

    public void update() {

        ball.move();
        movePaddles();
        ball.checkPaddleFaceCollision(paddle1);
        ball.checkPaddleFaceCollision(paddle2);
        ball.checkPaddleSideCollision(paddle1);
        ball.checkPaddleSideCollision(paddle2);
        checkScored();
        checkWinner();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.drawLine(SCREEN_WIDTH/2, 0, SCREEN_WIDTH/2, SCREEN_HEIGHT);
        paddle1.draw(g2d);
        paddle2.draw(g2d);
        if (!winner) {
            ball.draw(g2d);
        }

    }

    @Override
    public void run() {

        long lastTime = System.nanoTime();
        long currentTime;
        final int FPS = 60;
        double drawInterval = 1_000_000_000 / FPS;
        double delta = 0;

        while(!winner) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }

            lastTime = currentTime;

        }
        gameThread.interrupt();
    }

}
