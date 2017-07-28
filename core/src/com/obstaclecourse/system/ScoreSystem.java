package com.obstaclecourse.system;

import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.math.MathUtils;
import com.obstaclecourse.common.GameManager;
import com.obstaclecourse.config.GameConfig;

/**
 * Created by platon on 28/07/2017.
 */

public class ScoreSystem extends IntervalSystem {

    public ScoreSystem() {
        super(GameConfig.SCORE_MAX_TIME);
    }

    @Override
    protected void updateInterval() {
        GameManager.getInstance().updateScore(MathUtils.random(1,5));
    }
}
