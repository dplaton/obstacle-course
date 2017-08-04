package com.obstaclecourse.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Logger;
import com.obstaclecourse.common.Mappers;
import com.obstaclecourse.component.BoundsComponent;
import com.obstaclecourse.component.MovementComponent;
import com.obstaclecourse.component.PlayerComponent;
import com.obstaclecourse.config.GameConfig;

/**
 * Handles the player reactions to key presses
 */

public class PlayerSystem extends IteratingSystem {
    private static final Logger LOG = new Logger(PlayerSystem.class.getName(), Logger.DEBUG);

    private static final Family FAMILY = Family.all(
            PlayerComponent.class,
            MovementComponent.class,
            BoundsComponent.class
    ).get();

    public PlayerSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        MovementComponent movement = Mappers.MOVEMENT_MAPPER.get(entity);

        movement.ySpeed = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            movement.ySpeed = GameConfig.MAX_PLAYER_Y_SPEED;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            movement.ySpeed = -GameConfig.MAX_PLAYER_Y_SPEED;
        }

    }
}
