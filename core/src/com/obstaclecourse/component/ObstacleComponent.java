package com.obstaclecourse.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * A component attached to obstacle entities. It holds the state of the obstacle, i.e. hit or not hit.
 */
public class ObstacleComponent implements Component, Pool.Poolable {

    /**
     * A flag indicating whether the obstacle was hit or not
     */
    public boolean hit;

    /**
     * Resets the component by setting {@link ObstacleComponent#hit} to false.
     */
    @Override
    public void reset() {
        hit = false;
    }
}
