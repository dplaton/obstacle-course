package com.obstaclecourse.common;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.obstaclecourse.component.BoundsComponent;
import com.obstaclecourse.component.CleanupComponent;
import com.obstaclecourse.component.MovementComponent;
import com.obstaclecourse.component.ObstacleComponent;
import com.obstaclecourse.component.PlayerComponent;
import com.obstaclecourse.component.PositionComponent;
import com.obstaclecourse.component.WorldWrapComponent;
import com.obstaclecourse.config.GameConfig;
import com.obstaclecourse.entity.Obstacle;
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

    public void addObstacle(float x, float y) {
        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        bounds.bounds.x = x;
        bounds.bounds.y = y;
        bounds.bounds.radius = GameConfig.OBSTACLE_BOUNDS_RADIUS;

        MovementComponent movement = engine.createComponent(MovementComponent.class);
        movement.xSpeed = -GameManager.getInstance().getDifficultyLevel().getObstacleSpeed();

        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = x;
        position.y = y;

        CleanupComponent cleanup = engine.createComponent(CleanupComponent.class);

        ObstacleComponent obstacleComponent = engine.createComponent(ObstacleComponent.class);

        Entity obstacle = engine.createEntity();
        obstacle.add(bounds);
        obstacle.add(movement);
        obstacle.add(position);
        obstacle.add(cleanup);
        obstacle.add(obstacleComponent);

        engine.addEntity(obstacle);
    }
}
