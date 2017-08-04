package com.obstaclecourse.system;

import com.obstaclecourse.common.EntityFactory;
import com.obstaclecourse.component.CollectibleType;
import com.obstaclecourse.config.GameConfig;

/**
 * Handles the spawning of the "Coin" collectibles.
 */

public class CoinSpawnSystem extends CollectibleSpawnSystem {

    public CoinSpawnSystem(EntityFactory factory) {
        super(factory, CollectibleType.COIN, GameConfig.COIN_SPAWN_TIME);
    }
}
