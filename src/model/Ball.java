package model;

import view.PongPanel;

import java.awt.*;

public class Ball {

    private int xLocation;
    private int yLocation;
    private int speed = 5;
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
    }

    public void checkWallCollision() {
        int yAcceleration = 1;
        if (yLocation >= PongPanel.SCREEN_HEIGHT - DIAMETER || yLocation <= 0) {
            if (ySpeed > 0) {
                ySpeed = -(ySpeed + yAcceleration);
            } else if (ySpeed < 0) {
                ySpeed = -(ySpeed - yAcceleration);
            }
            Paddle.setSpeed(Paddle.getSpeed() + yAcceleration);
        }
    }

    public void checkPaddleFaceCollision(Paddle paddle) {
        int xAcceleration = 1;
        // Left paddle
        if (paddle.getXLocation() < PongPanel.SCREEN_WIDTH / 2) {
            if (getYLocation() > +paddle.getYLocation() - DIAMETER / 2 &&
                    getYLocation() <= paddle.getYLocation() + Paddle.HEIGHT - DIAMETER / 2 &&
                    getXLocation() <= Paddle.WIDTH &&
                    getXLocation() >= Paddle.WIDTH / 2) {
                setXLocation(Paddle.WIDTH);
                xSpeed = -xSpeed + xAcceleration;
            }
        }
        // Right paddle
        else if (paddle.getXLocation() >= PongPanel.SCREEN_WIDTH / 2) {
            if (getYLocation() > +paddle.getYLocation() - DIAMETER / 2 &&
                    getYLocation() <= paddle.getYLocation() + Paddle.HEIGHT - DIAMETER / 2 &&
                    getXLocation() >= PongPanel.SCREEN_WIDTH - Paddle.WIDTH - DIAMETER &&
                    getXLocation() <= PongPanel.SCREEN_WIDTH - Paddle.WIDTH / 2 - DIAMETER) {
                setXLocation(PongPanel.SCREEN_WIDTH - Paddle.WIDTH - DIAMETER);
                xSpeed = - (xSpeed + xAcceleration);
            }
        }
    }

    public void checkPaddleSideCollision(Paddle paddle) {
        // Left paddle
        if (paddle.getXLocation() < PongPanel.SCREEN_WIDTH / 2 &&
                xLocation <= Paddle.WIDTH / 2 &&
                xLocation >= - Paddle.WIDTH / 2) {
            // Top of left paddle
            if (yLocation >= paddle.getYLocation() - DIAMETER &&
                    yLocation <= paddle.getYLocation() + Paddle.HEIGHT / 2 - DIAMETER / 2) {
                yLocation = paddle.getYLocation() - DIAMETER;
                ySpeed = -(ySpeed + 1);
            }
            // Bottom of left paddle
            else if (yLocation > paddle.getYLocation() + Paddle.HEIGHT / 2 - DIAMETER / 2 &&
                    yLocation <= paddle.getYLocation() + Paddle.HEIGHT) {
                yLocation = paddle.getYLocation() + Paddle.HEIGHT;
                ySpeed = -(ySpeed - 1);
            }
        // Right paddle
        } else if (paddle.getXLocation() >= PongPanel.SCREEN_WIDTH / 2 &&
                xLocation <= PongPanel.SCREEN_WIDTH - DIAMETER / 2 &&
                xLocation >= PongPanel.SCREEN_WIDTH - DIAMETER / 2 - Paddle.WIDTH) {
            // Top of right paddle
            if (yLocation >= paddle.getYLocation() - DIAMETER &&
                    yLocation <= paddle.getYLocation() + Paddle.HEIGHT / 2 - DIAMETER / 2) {
                yLocation = paddle.getYLocation() - DIAMETER;
                ySpeed = -(ySpeed + 1);
            }
            // Bottom of right paddle
            else if (yLocation > paddle.getYLocation() + Paddle.HEIGHT / 2 - DIAMETER / 2 &&
                    yLocation <= paddle.getYLocation() + Paddle.HEIGHT) {
                yLocation = paddle.getYLocation() + Paddle.HEIGHT;
                ySpeed = -(ySpeed - 1);
            }
        }

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



}
