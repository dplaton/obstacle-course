package com.obstaclecourse.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.obstaclecourse.common.Mappers;
import com.obstaclecourse.component.PositionComponent;
import com.obstaclecourse.component.WorldWrapComponent;
import com.obstaclecourse.config.GameConfig;

/**
 * Created by platon on 24/07/2017.
 */

public class WorldWrapSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(PositionComponent.class, WorldWrapComponent.class).get();

    private final Viewport viewport;

    public WorldWrapSystem(Viewport viewport) {
        super(FAMILY);
        this.viewport = viewport;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = Mappers.POSITION_MAPPER.get(entity);

        position.x = MathUtils.clamp(position.x, GameConfig.PLAYER_SIZE, viewport.getWorldWidth() - GameConfig.PLAYER_SIZE);
        position.y = MathUtils.clamp(position.y, GameConfig.PLAYER_SIZE, viewport.getWorldHeight() - GameConfig.PLAYER_SIZE);
    }
}
