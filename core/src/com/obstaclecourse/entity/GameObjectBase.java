package com.obstaclecourse.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;

/**
 * Created by platon on 13/07/2017.
 */

public abstract class  GameObjectBase {
    protected float x;
    protected float y;
    private float width = 1f;
    private float height = 1f;

    protected Circle bounds;

    public GameObjectBase(float boundsRadius) {
        bounds = new Circle(x,y, boundsRadius);
    }

    public void drawDebug(ShapeRenderer renderer) {
        renderer.x(bounds.x, bounds.y, 0.1f);
        renderer.circle(bounds.x, bounds.y, bounds.radius,40);
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        updateBounds(x, y);
    }

    protected void updateBounds(float x, float y) {
        float halfWidth = width / 2f;
        float halfHeight = height / 2f;
        bounds.x = x + halfWidth;
        bounds.y = y + halfHeight;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
        updateBounds(x,y);
    }

    public void setY(float y) {
        this.y = y;
        updateBounds(x,y);
    }

    protected Circle getBounds() {
        return bounds;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setSize(float w, float h) {
        this.width = w;
        this.height = h;
        updateBounds(x, y);
    }
}
