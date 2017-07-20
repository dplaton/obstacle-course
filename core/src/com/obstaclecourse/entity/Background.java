package com.obstaclecourse.entity;

/**
 * Created by platon on 14/07/2017.
 */

public class Background {

    private float x;
    private float y;
    private float height;
    private float width;


    public Background() {
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setSize(float w, float h) {
        this.width = w;
        this.height = h;
    }
}
