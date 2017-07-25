package com.obstaclecourse.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.obstaclecourse.common.Mappers;
import com.obstaclecourse.component.CleanupComponent;
import com.obstaclecourse.component.PositionComponent;
import com.obstaclecourse.config.GameConfig;

/**
 * Created by platon on 25/07/2017.
 */

public class CleanupSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(PositionComponent.class, CleanupComponent.class).get();

    public CleanupSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = Mappers.POSITION_MAPPER.get(entity);

        if (position.x < -GameConfig.OBSTACLE_SIZE) {
            getEngine().removeEntity(entity);
        }
    }
}
