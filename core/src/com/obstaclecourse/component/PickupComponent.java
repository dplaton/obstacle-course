package com.obstaclecourse.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * A component attached to collectible entities
 */

public class PickupComponent implements Component, Pool.Poolable {
    /**
     * A flag indicating whether the collectible was picked up or not
     */
    public boolean collected = false;

    /**
     * The type of the collectible
     */
    public CollectibleType collectibleType;

    /**
     * Marks the collectible as "collected"
     */
    public void collect() {
        collected = true;
    }

    /**
     * Returns the "collected" status of the entity
     *
     * @return {@code true} if the entity was collected, {@link false} otherwise
     */
    public boolean isCollected() {
        return collected;
    }


    /**
     * Resets the component by setting the "collected" flag to false
     */
    @Override
    public void reset() {
        collected = false;
    }
}
