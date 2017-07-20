package com.obstaclecourse.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.obstaclecourse.config.GameConfig;

/**
 * Created by platon on 07/07/2017.
 */
public class Player extends GameObjectBase{

    public Player() {
        super(GameConfig.PLAYER_BOUNDS_RADIUS);
        setSize(GameConfig.PLAYER_SIZE, GameConfig.PLAYER_SIZE);
    }

    public void update() {
        float ySpeed = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            ySpeed = GameConfig.MAX_PLAYER_Y_SPEED;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            ySpeed = -GameConfig.MAX_PLAYER_Y_SPEED;
        }

        setY(getY() + ySpeed);
        setPosition(x, y);

    }
}
