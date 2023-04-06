package model;

import view.PongPanel;

import java.awt.*;

public class Paddle extends Rectangle{

    private int xLocation;
    private int yLocation;
    private final Color COLOR;
    public static final int WIDTH = 50;
    public static final int HEIGHT = 200;
    public PongPanel panel;

    public Paddle(int xLocation, int yLocation, Color color, PongPanel panel) {
        super(xLocation, yLocation, WIDTH, HEIGHT);
        this.COLOR = color;
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.panel = panel;

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
        if(this.yLocation >= panel.SCREEN_HEIGHT - HEIGHT) {
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
