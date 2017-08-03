package com.obstaclecourse.system;

import com.obstaclecourse.common.EntityFactory;
import com.obstaclecourse.component.PickupType;
import com.obstaclecourse.config.GameConfig;

/**
 * Created by platon on 03/08/2017.
 */

public class LifeSpawnSystem extends CollectibleSpawnSystem {

    public LifeSpawnSystem(EntityFactory factory) {
        super(factory, PickupType.LIFE, GameConfig.LIFE_SPAWN_TIME);
    }
}
