package view;

import model.Ball;
import model.Paddle;
import util.KeyHandler;

import javax.swing.*;
import java.awt.*;

public class PongPanel extends JPanel implements Runnable{

    public static final int SCREEN_WIDTH = 1440;
    public static final int SCREEN_HEIGHT = 800;
    private final int PADDLE_HEIGHT = Paddle.HEIGHT;
    private int paddleSpeed = 5;

    Paddle paddle1;
    Paddle paddle2;

    Ball ball;

    KeyHandler keyHandler;

    Thread gameThread;

    public PongPanel() {
        paddle1 = new Paddle(0, (SCREEN_HEIGHT - PADDLE_HEIGHT)/2, Color.red);
        paddle2 = new Paddle(SCREEN_WIDTH - 50, (SCREEN_HEIGHT - PADDLE_HEIGHT)/2, Color.blue);

        ball = new Ball((SCREEN_WIDTH - Ball.DIAMETER)/2, (SCREEN_HEIGHT - Ball.DIAMETER)/2, Color.ORANGE);
        ball.serve();

        keyHandler = new KeyHandler();

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(new Color(0, 0, 0));
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void checkScored() {
        if (ball.getXLocation() < -(Ball.DIAMETER + ball.getSpeed()) || ball.getXLocation() > SCREEN_WIDTH + ball.getSpeed()) {
            try {
                gameThread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ball = new Ball((SCREEN_WIDTH - Ball.DIAMETER)/2, (SCREEN_HEIGHT - Ball.DIAMETER)/2, Color.ORANGE);
            ball.serve();
        }
    }


    public void update() {
        movePaddles();
        ball.checkWallCollision();
        ball.move();
        checkScored();

    }

    public void movePaddles() {
        if (keyHandler.wPressed && !paddle1.isTopCollision()) {
            paddle1.setYLocation(paddle1.getYLocation() - paddleSpeed);
        } if (keyHandler.sPressed && !paddle1.isBottomCollision()) {
            paddle1.setYLocation(paddle1.getYLocation() + paddleSpeed);
        } if (keyHandler.upPressed && !paddle2.isTopCollision()) {
            paddle2.setYLocation(paddle2.getYLocation() - paddleSpeed);
        } if (keyHandler.downPressed && !paddle2.isBottomCollision()) {
            paddle2.setYLocation(paddle2.getYLocation() + paddleSpeed);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.drawLine(720, 0, 720, 800);
        paddle1.draw(g2d);
        paddle2.draw(g2d);
        ball.draw(g2d);

    }

    @Override
    public void run() {

        long lastTime = System.nanoTime();
        long currentTime;
        final int FPS = 60;
        double drawInterval = 1_000_000_000 / FPS;
        double delta = 0;

        while(true) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }

            lastTime = currentTime;

        }
    }

}
