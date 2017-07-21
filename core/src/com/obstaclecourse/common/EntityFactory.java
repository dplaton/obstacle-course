package com.obstaclecourse.common;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.obstaclecourse.component.BoundsComponent;
import com.obstaclecourse.config.GameConfig;

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

        Entity entity = engine.createEntity();
        entity.add(component);
        engine.addEntity(entity);
    }
}
