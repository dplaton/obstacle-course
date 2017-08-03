package com.obstaclecourse.system.collision;

import com.badlogic.ashley.core.Entity;

/**
 * Created by platon on 27/07/2017.
 */

public interface CollisionListener {
    void hitObstacle();

    void collect(Entity lifeEntity);
}
