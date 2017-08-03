package com.obstaclecourse.system;

import com.obstaclecourse.common.EntityFactory;
import com.obstaclecourse.component.PickupType;
import com.obstaclecourse.config.GameConfig;

/**
 * Created by platon on 03/08/2017.
 */

public class CoinSpawnSystem extends CollectibleSpawnSystem {

    public CoinSpawnSystem(EntityFactory factory) {
        super(factory, PickupType.COIN, GameConfig.COIN_SPAWN_TIME);
    }
}
