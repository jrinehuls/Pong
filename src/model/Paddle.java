package model;

import view.PongPanel;

import java.awt.*;

public class Paddle extends Rectangle{

    private int xLocation;
    private int yLocation;
    private final Color COLOR;
    public static final int WIDTH = 50;
    public static final int HEIGHT = 200;

    public Paddle(int xLocation, int yLocation, Color color) {
        super(xLocation, yLocation, WIDTH, HEIGHT);
        this.COLOR = color;
        this.xLocation = xLocation;
        this.yLocation = yLocation;

    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(this.COLOR);
        g2d.fillRect(xLocation, yLocation, WIDTH, HEIGHT);
    }

    public boolean isTopCollision() {
        if(this.yLocation <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isBottomCollision() {
        if(this.yLocation >= PongPanel.SCREEN_HEIGHT - HEIGHT) {
            return true;
        } else {
            return false;
        }
    }

    public int getYLocation() {
        return yLocation;
    }

    public void setYLocation(int yLocation) {
        this.yLocation = yLocation;
    }
}
