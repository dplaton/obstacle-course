package com.obstaclecourse.system;

import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Logger;
import com.obstaclecourse.common.EntityFactory;
import com.obstaclecourse.component.PickupType;
import com.obstaclecourse.config.GameConfig;

/**
 * Created by platon on 30/07/2017.
 */

public class CollectibleSpawnSystem extends IntervalSystem {

    private static final Logger LOG = new Logger(CollectibleSpawnSystem.class.getName(), Logger.DEBUG);

    private final EntityFactory factory;
    private final PickupType collectibleType;

    protected CollectibleSpawnSystem(EntityFactory factory, PickupType type, float interval) {
        super(interval);
        this.collectibleType = type;
        this.factory = factory;
    }

    @Override
    protected void updateInterval() {
        float min = 0;
        float max = GameConfig.WORLD_HEIGHT - GameConfig.PLAYER_SIZE;

        float lifeY = MathUtils.random(min, max);
        float lifeX = GameConfig.WORLD_WIDTH;
        factory.addCollectible(lifeX, lifeY, collectibleType);
    }
}
