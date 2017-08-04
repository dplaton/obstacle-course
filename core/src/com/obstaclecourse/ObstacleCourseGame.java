package com.obstaclecourse;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.obstaclecourse.screen.loading.LoadingScreen;

/**
 * The main class of our game. It manages the screens.
 */
public class ObstacleCourseGame extends Game {

    private static final Logger LOG = new Logger(ObstacleCourseGame.class.getName(), Logger.DEBUG);

    private AssetManager assetManager;
    private SpriteBatch batch;

    @Override
    public void create() {

        assetManager = new AssetManager();
        Gdx.app.setLogLevel(Application.LOG_INFO);
        batch = new SpriteBatch();
        setScreen(new LoadingScreen(this));
    }

    @Override
    public void dispose() {
        assetManager.dispose();
        batch.dispose();
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public SpriteBatch getSpriteBatch() {
        return batch;
    }
}
