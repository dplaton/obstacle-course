package com.obstaclecourse.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by platon on 30/07/2017.
 */

public class PickupComponent implements Component, Pool.Poolable {
    public boolean collected = false;

    public PickupType pickupType;

    public void collect() {
        collected = true;
    }

    public boolean isCollected() {
        return collected;
    }

    public boolean isNotCollected() {
        return !collected;
    }

    @Override
    public void reset() {
        collected = false;
    }
}
