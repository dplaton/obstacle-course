package com.obstaclecourse.system;

import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.math.MathUtils;
import com.obstaclecourse.common.EntityFactory;
import com.obstaclecourse.config.GameConfig;

/**
 * Handles the spawning of the obstacles in the game
 */

public class ObstacleSpawnSystem extends IntervalSystem {


    private final EntityFactory factory;

    public ObstacleSpawnSystem(EntityFactory factory) {
        super(GameConfig.OBSTACLE_SPAWN_TIME);
        this.factory = factory;
    }

    @Override
    protected void updateInterval() {
        float min = 0;
        float max = GameConfig.WORLD_HEIGHT - GameConfig.OBSTACLE_SIZE;

        float obstacleY = MathUtils.random(min, max);
        float obstacleX = GameConfig. WORLD_WIDTH;

        factory.addObstacle(obstacleX, obstacleY);
    }
}
