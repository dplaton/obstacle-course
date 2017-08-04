package com.obstaclecourse.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Logger;
import com.obstaclecourse.common.Mappers;
import com.obstaclecourse.component.MovementComponent;
import com.obstaclecourse.component.PositionComponent;

/**
 * Handles the movement of the entities in the game.
 */

public class MovementSystem extends IteratingSystem {

    private static final Logger LOG = new Logger(MovementSystem.class.getName(), Logger.DEBUG);

    private static final Family FAMILY = Family.all(PositionComponent.class, MovementComponent.class).get();


    public MovementSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = Mappers.POSITION_MAPPER.get(entity);
        MovementComponent movement = Mappers.MOVEMENT_MAPPER.get(entity);

        position.y += movement.ySpeed;
        position.x += movement.xSpeed;
    }
}
