package com.obstaclecourse.common;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.obstaclecourse.assets.AssetDescriptors;
import com.obstaclecourse.assets.RegionNames;
import com.obstaclecourse.component.BoundsComponent;
import com.obstaclecourse.component.CleanupComponent;
import com.obstaclecourse.component.DimensionComponent;
import com.obstaclecourse.component.MovementComponent;
import com.obstaclecourse.component.ObstacleComponent;
import com.obstaclecourse.component.PlayerComponent;
import com.obstaclecourse.component.PositionComponent;
import com.obstaclecourse.component.TextureComponent;
import com.obstaclecourse.component.WorldWrapComponent;
import com.obstaclecourse.config.GameConfig;
import com.obstaclecourse.entity.Obstacle;
import com.obstaclecourse.entity.Player;
import com.sun.corba.se.impl.orbutil.threadpool.TimeoutException;

/**
 * Created by platon on 21/07/2017.
 */

public class EntityFactory {

    private final PooledEngine engine;
    private final AssetManager assetManager;
    private final TextureAtlas gameAtlas;

    public EntityFactory(PooledEngine engine, AssetManager assetManager) {
        this.engine = engine;
        this.assetManager = assetManager;
        gameAtlas = assetManager.get(AssetDescriptors.GAMEPLAY_ASSET_DESCRIPTOR);
    }

    public void addPlayer() {
        float y = (GameConfig.WORLD_HEIGHT - GameConfig.PLAYER_SIZE) / 2;
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

        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.textureRegion = gameAtlas.findRegion(RegionNames.TR_PLAYER);


        DimensionComponent dimensionComponent = engine.createComponent(DimensionComponent.class);
        dimensionComponent.height = GameConfig.PLAYER_SIZE;
        dimensionComponent.width = GameConfig.PLAYER_SIZE;

        Entity entity = engine.createEntity();
        entity.add(component);
        entity.add(movement);
        entity.add(player);
        entity.add(position);
        entity.add(worldWrap);
        entity.add(texture);
        entity.add(dimensionComponent);

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

        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.textureRegion = gameAtlas.findRegion(RegionNames.TR_OBSTACLE);


        DimensionComponent dimensionComponent = engine.createComponent(DimensionComponent.class);
        dimensionComponent.height = GameConfig.OBSTACLE_SIZE;
        dimensionComponent.width = GameConfig.OBSTACLE_SIZE;
        Entity obstacle = engine.createEntity();
        obstacle.add(bounds);
        obstacle.add(movement);
        obstacle.add(position);
        obstacle.add(cleanup);
        obstacle.add(obstacleComponent);
        obstacle.add(texture);
        obstacle.add(dimensionComponent);

        engine.addEntity(obstacle);
    }

    public void addBackground() {
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.textureRegion = gameAtlas.findRegion(RegionNames.TR_BACKGROUND);

        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = 0;
        position.y = 0;

        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = GameConfig.WORLD_WIDTH;
        dimension.height = GameConfig.WORLD_HEIGHT;

        Entity entity = engine.createEntity();
        entity.add(texture);
        entity.add(position);
        entity.add(dimension);

        engine.addEntity(entity);
    }
}
