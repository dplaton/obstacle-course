package com.obstaclecourse.entity;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Pool;
import com.obstaclecourse.config.GameConfig;

/**
 * Created by platon on 07/07/2017.
 */
public class Obstacle extends GameObjectBase implements Pool.Poolable{

    private float xSpeed = 0.1f;

    private boolean hit = false;

    public Obstacle() {
        super(GameConfig.OBSTACLE_BOUNDS_RADIUS);
        setSize(GameConfig.OBSTACLE_SIZE, GameConfig.OBSTACLE_SIZE);
    }

    public void update() {
        setPosition(getX() - xSpeed,getY());
    }

    public boolean isCollidingWithPlayer(Player player) {
        Circle playerBounds = player.getBounds();
        boolean overlaps = hit = Intersector.overlaps(playerBounds, getBounds());

        return overlaps;
    }

    public boolean isNotHit() {
        return !hit;
    }

    public void setXSpeed(float speed) {
        this.xSpeed = speed;
    }

    @Override
    public void reset() {
        hit = false;
    }

}
