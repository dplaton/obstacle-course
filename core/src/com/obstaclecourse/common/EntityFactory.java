package com.obstaclecourse.common;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.obstaclecourse.assets.AssetDescriptors;
import com.obstaclecourse.assets.RegionNames;
import com.obstaclecourse.component.BoundsComponent;
import com.obstaclecourse.component.CleanupComponent;
import com.obstaclecourse.component.DimensionComponent;
import com.obstaclecourse.component.PickupComponent;
import com.obstaclecourse.component.MovementComponent;
import com.obstaclecourse.component.ObstacleComponent;
import com.obstaclecourse.component.CollectibleType;
import com.obstaclecourse.component.PlayerComponent;
import com.obstaclecourse.component.PositionComponent;
import com.obstaclecourse.component.TextureComponent;
import com.obstaclecourse.component.WorldWrapComponent;
import com.obstaclecourse.config.GameConfig;


/**
 * Creates entities and adds them to the Game Engine
 */

public class EntityFactory {

    private final PooledEngine engine;
    private final TextureAtlas gameAtlas;

    public EntityFactory(PooledEngine engine, AssetManager assetManager) {
        this.engine = engine;
        gameAtlas = assetManager.get(AssetDescriptors.GAMEPLAY_ASSET_DESCRIPTOR);
    }

    /**
     * Creates a new {@link Entity} object representing the player and adds it to the game engine.
     */
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

    /**
     * Creates a new {@link Entity} object representing an obstacle and adds it to the game engine.
     * @param x the X coordinate where to place the object.
     * @param y the Y coordinate where to place the object
     */
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

    /**
     * Creates a new {@link Entity} representing the background and adds it to the game engine
     */
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

    /**
     * Creates a new {@link Entity} representing a collectible and adds it to the game engine
     * @param x the X coordinate where to place the object
     * @param y the Y coordinate where to place the object
     * @param type the type of the collectible, as a {@link CollectibleType} value
     */
    public void addCollectible(float x, float y, CollectibleType type) {
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        switch (type) {
            case LIFE:texture.textureRegion = gameAtlas.findRegion(RegionNames.TR_PLAYER);
                break;
            case COIN:texture.textureRegion = gameAtlas.findRegion(RegionNames.TR_COIN);
                break;
        }

        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = x;
        position.y = y;

        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        bounds.bounds.x = x;
        bounds.bounds.y = y;
        bounds.bounds.setRadius(GameConfig.PLAYER_BOUNDS_RADIUS);

        MovementComponent movement = engine.createComponent(MovementComponent.class);
        movement.xSpeed = -GameManager.getInstance().getDifficultyLevel().getObstacleSpeed();

        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.height = GameConfig.PLAYER_SIZE;
        dimension.width = GameConfig.PLAYER_SIZE;

        CleanupComponent cleanupComponent = engine.createComponent(CleanupComponent.class);
        PickupComponent marker = engine.createComponent(PickupComponent.class);
        marker.collectibleType = type;

        Entity entity = engine.createEntity();

        entity.add(bounds);
        entity.add(movement);
        entity.add(position);
        entity.add(cleanupComponent);
        entity.add(dimension);
        entity.add(texture);
        entity.add(marker);

        engine.addEntity(entity);
    }
}
