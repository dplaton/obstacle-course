package com.obstaclecourse.screen.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.obstaclecourse.ObstacleCourseGame;
import com.obstaclecourse.screen.GameController;
import com.obstaclecourse.screen.GameRenderer;
import com.obstaclecourse.screen.menu.MenuScreen;

/**
 * Created by platon on 14/07/2017.
 */

public class GameScreen implements Screen {

    private GameController controller;
    private GameRenderer renderer;
    private final ObstacleCourseGame game;
    private final AssetManager assetManager;

    public GameScreen(ObstacleCourseGame obstacleCourseGame) {
        this.game = obstacleCourseGame;
        this.assetManager = this.game.getAssetManager();
    }

    @Override
    public void show() {
        controller = new GameController(game);
        renderer = new GameRenderer(controller, assetManager, game.getSpriteBatch());
    }


    @Override
    public void render(float delta) {
        renderer.render(delta);

        if (controller.isGameOver()) {
            game.setScreen(new MenuScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
