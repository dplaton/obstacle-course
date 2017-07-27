package com.obstaclecourse.system.collision;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Logger;
import com.obstaclecourse.common.Mappers;
import com.obstaclecourse.component.BoundsComponent;
import com.obstaclecourse.component.ObstacleComponent;
import com.obstaclecourse.component.PlayerComponent;
import com.obstaclecourse.entity.Obstacle;
import com.obstaclecourse.entity.Player;

/**
 * Created by platon on 26/07/2017.
 */

public class CollisionSystem extends EntitySystem {
    private static final Logger LOG = new Logger(CollisionSystem.class.getName(), Logger.DEBUG);


    /*
     * We need to families here which will contain the entities that can collide
     */
    private static final Family PLAYER_FAMILY = Family.all(
            PlayerComponent.class,
            BoundsComponent.class).get();

    private static final Family OBSTACLE_FAMILY = Family.all(
            ObstacleComponent.class,
            BoundsComponent.class).get();

    private final CollisionListener listener;

    public CollisionSystem(CollisionListener listener) {
        this.listener = listener;
    }

    @Override
    public void update(float deltaTime) {

        // we only have one player, tho
        ImmutableArray<Entity> players = getEngine().getEntitiesFor(PLAYER_FAMILY);
        ImmutableArray<Entity> obstacles = getEngine().getEntitiesFor(OBSTACLE_FAMILY);

        for(Entity playerEntity: players) {
            for(Entity obstacleEntity: obstacles) {
                ObstacleComponent obstacleComponent = Mappers.OBSTACLE_MAPPER.get(obstacleEntity);
                if (obstacleComponent.hit) {
                    continue;
                }

                if (checkCollision(playerEntity, obstacleEntity)) {
                    obstacleComponent.hit = true;
                    LOG.debug("Collision detected!");
                    listener.hitObstacle();
                }
            }
        }
    }

    private boolean checkCollision(Entity playerEntity, Entity obstacleEntity) {
        BoundsComponent playerBounds = Mappers.BOUNDS_MAPPER.get(playerEntity);
        BoundsComponent obstactleBounds = Mappers.BOUNDS_MAPPER.get(obstacleEntity);

        return Intersector.overlaps(playerBounds.bounds, obstactleBounds.bounds);
    }
}
