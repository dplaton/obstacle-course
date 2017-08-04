package com.obstaclecourse.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.obstaclecourse.common.Mappers;
import com.obstaclecourse.component.BoundsComponent;
import com.obstaclecourse.component.DimensionComponent;
import com.obstaclecourse.component.PositionComponent;

/**
 * Updates the bounds of the entity when it moves around the game world.
 */

public class BoundsSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(PositionComponent.class, BoundsComponent.class, DimensionComponent.class)
                                               .get();

    public BoundsSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BoundsComponent bounds = Mappers.BOUNDS_MAPPER.get(entity);
        PositionComponent position = Mappers.POSITION_MAPPER.get(entity);
        DimensionComponent dimension = Mappers.DIMENSION_MAPPER.get(entity);

        bounds.bounds.x = position.x + dimension.width / 2;
        bounds.bounds.y = position.y + dimension.height / 2;
    }
}
