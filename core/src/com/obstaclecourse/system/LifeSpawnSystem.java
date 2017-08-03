package com.obstaclecourse.system;

import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Logger;
import com.obstaclecourse.common.EntityFactory;
import com.obstaclecourse.component.PickupType;
import com.obstaclecourse.config.GameConfig;
import com.obstaclecourse.screen.game.GameScreen;

/**
 * Created by platon on 30/07/2017.
 */

public class LifeSpawnSystem extends IntervalSystem {

    private static final Logger LOG = new Logger(LifeSpawnSystem.class.getName(), Logger.DEBUG);

    private final EntityFactory factory;

    public LifeSpawnSystem(EntityFactory factory) {
        super(GameConfig.LIFE_SPAWN_TIME);
        this.factory = factory;
    }

    @Override
    protected void updateInterval() {
        float min = 0;
        float max = GameConfig.WORLD_HEIGHT - GameConfig.PLAYER_SIZE;

        float lifeY = MathUtils.random(min, max);
        float lifeX = GameConfig.WORLD_WIDTH;
        LOG.debug("Spawning new pickup object");
        factory.addCollectible(lifeX, lifeY, PickupType.LIFE);
    }
}
