package com.obstaclecourse.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.obstaclecourse.common.Mappers;
import com.obstaclecourse.component.DimensionComponent;
import com.obstaclecourse.component.PositionComponent;
import com.obstaclecourse.component.WorldWrapComponent;
import com.obstaclecourse.config.GameConfig;

/**
 * Handles the collision with the world bounds, preventing entities which have a {@link WorldWrapComponent} attached to them from leaving the world.
 */

public class WorldWrapSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(PositionComponent.class, WorldWrapComponent.class, DimensionComponent
            .class).get();

    private final Viewport viewport;

    public WorldWrapSystem(Viewport viewport) {
        super(FAMILY);
        this.viewport = viewport;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = Mappers.POSITION_MAPPER.get(entity);

        DimensionComponent dimension = Mappers.DIMENSION_MAPPER.get(entity);

        position.x = MathUtils.clamp(position.x, 0, viewport.getWorldWidth() - dimension.width);
        position.y = MathUtils.clamp(position.y, 0, viewport.getWorldHeight() - dimension.height);
    }
}
