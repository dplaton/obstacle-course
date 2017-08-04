package com.obstaclecourse.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Component that adds movement to an entity. The movement is defined by two parameters - the speed on the X axis and the speed on the Y axis.
 */

public class MovementComponent implements Component, Pool.Poolable {
    public float xSpeed;
    public float ySpeed;

    /**
     * Resets this component by settings both speeds to 0
     */
    @Override
    public void reset() {
        xSpeed = 0;
        ySpeed = 0;
    }
}
