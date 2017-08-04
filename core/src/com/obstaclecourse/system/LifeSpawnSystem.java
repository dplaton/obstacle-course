package com.obstaclecourse.system;

import com.obstaclecourse.common.EntityFactory;
import com.obstaclecourse.component.CollectibleType;
import com.obstaclecourse.config.GameConfig;

/**
 * Handles the spawning of "life" collectibles in the game.
 */

public class LifeSpawnSystem extends CollectibleSpawnSystem {

    public LifeSpawnSystem(EntityFactory factory) {
        super(factory, CollectibleType.LIFE, GameConfig.LIFE_SPAWN_TIME);
    }
}
