package com.obstaclecourse.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by platon on 21/07/2017.
 */

public class MovementComponent implements Component, Pool.Poolable {
    public float xSpeed;
    public float ySpeed;

    @Override
    public void reset() {
        xSpeed = 0;
        ySpeed = 0;
    }
}
