package com.obstaclecourse.system;

import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.math.MathUtils;
import com.obstaclecourse.common.EntityFactory;
import com.obstaclecourse.component.PickupType;
import com.obstaclecourse.config.GameConfig;

/**
 * Created by platon on 03/08/2017.
 */

public class CoinSpawnSystem extends IntervalSystem {

    private final EntityFactory factory;

    public CoinSpawnSystem(EntityFactory factory) {
        super(GameConfig.COIN_SPAWN_TIME);
        this.factory = factory;
    }

    @Override
    protected void updateInterval() {
        float min = 0;
        float max = GameConfig.WORLD_HEIGHT - GameConfig.PLAYER_SIZE;

        float lifeY = MathUtils.random(min, max);
        float lifeX = GameConfig.WORLD_WIDTH;
        factory.addCollectible(lifeX, lifeY, PickupType.COIN);
    }
}
