package com.obstaclecourse.common;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.obstaclecourse.component.BoundsComponent;
import com.obstaclecourse.component.MovementComponent;
import com.obstaclecourse.component.PlayerComponent;
import com.obstaclecourse.component.PositionComponent;
import com.obstaclecourse.component.WorldWrapComponent;
import com.obstaclecourse.config.GameConfig;
import com.obstaclecourse.entity.Player;

/**
 * Created by platon on 21/07/2017.
 */

public class EntityFactory {

    private final PooledEngine engine;

    public EntityFactory(PooledEngine engine) {
        this.engine = engine;
    }

    public void addPlayer() {
        float y = GameConfig.WORLD_HEIGHT / 2;
        float x = GameConfig.PLAYER_SIZE;

        BoundsComponent component = engine.createComponent(BoundsComponent.class);
        component.bounds.setX(x);
        component.bounds.setY(y);
        component.bounds.setRadius(GameConfig.PLAYER_BOUNDS_RADIUS);

        MovementComponent movement = engine.createComponent(MovementComponent.class);

        PlayerComponent player = engine.createComponent(PlayerComponent.class);

        WorldWrapComponent worldWrap = engine.createComponent(WorldWrapComponent.class);

        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = x;
        position.y = y;



        Entity entity = engine.createEntity();
        entity.add(component);
        entity.add(movement);
        entity.add(player);
        entity.add(position);
        entity.add(worldWrap);
        engine.addEntity(entity);
    }
}
