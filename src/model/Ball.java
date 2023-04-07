package model;

import view.PongPanel;

import java.awt.*;

public class Ball {

    private boolean wallCollision = false;
    private int xLocation;
    private int yLocation;
    private int speed = 2;
    public int xSpeed = speed;
    public int ySpeed = speed;
    private Color color;
    public static final int DIAMETER = 50;

    public Ball(int xLocation, int yLocation, Color color) {
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.color = color;
    }

    public void serve() {
        double x = Math.random();
        double y = Math.random();
        if (x < 0.5) {
            xSpeed = -speed;
        }
        if (y < 0.5) {
            ySpeed = -speed;
        }
    }

    public void move() {
        xLocation += xSpeed;
        yLocation += ySpeed;
        checkWallCollision();
        if (wallCollision) {
            ySpeed = -ySpeed;
        }
    }

    public void checkWallCollision() {
        if (yLocation >= PongPanel.SCREEN_HEIGHT - DIAMETER || yLocation <= 0) {
            wallCollision = true;
        } else {
            wallCollision = false;
        }
    }

    public void checkPaddleCollision() {

    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillOval(xLocation, yLocation, DIAMETER, DIAMETER);
    }


    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getXLocation() {
        return xLocation;
    }

    public void setXLocation(int xLocation) {
        this.xLocation = xLocation;
    }

    public int getYLocation() {
        return yLocation;
    }

    public void setYLocation(int yLocation) {
        this.yLocation = yLocation;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isWallCollision() {
        return wallCollision;
    }

}
