package com.obstaclecourse.screen.game;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.obstaclecourse.ObstacleCourseGame;
import com.obstaclecourse.common.EntityFactory;
import com.obstaclecourse.config.GameConfig;
import com.obstaclecourse.system.BoundsSystem;
import com.obstaclecourse.system.CleanupSystem;
import com.obstaclecourse.system.MovementSystem;
import com.obstaclecourse.system.ObstacleSpawnSystem;
import com.obstaclecourse.system.PlayerSystem;
import com.obstaclecourse.system.WorldWrapSystem;
import com.obstaclecourse.system.debug.DebugCameraSystem;
import com.obstaclecourse.system.debug.DebugRenderSystem;
import com.obstaclecourse.system.debug.GridRenderSystem;
import com.obstaclecourse.util.GdxUtils;

/**
 * Created by platon on 14/07/2017.
 */
public class GameScreen implements Screen {

    private static final Logger LOG = new Logger(GameScreen.class.getName(), Logger.DEBUG);

    private final ObstacleCourseGame game;
    private final AssetManager assetManager;

    private Viewport viewport;
    private ShapeRenderer renderer;
    private PooledEngine engine;
    private OrthographicCamera camera;
    private EntityFactory factory;

    public GameScreen(ObstacleCourseGame obstacleCourseGame) {
        this.game = obstacleCourseGame;
        this.assetManager = this.game.getAssetManager();
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        renderer = new ShapeRenderer();
        engine = new PooledEngine();

        factory = new EntityFactory(engine);

        // the order in which we add the systems matter!
        engine.addSystem(new DebugCameraSystem(camera, GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y));

        engine.addSystem(new PlayerSystem());
        engine.addSystem(new MovementSystem());
        engine.addSystem(new WorldWrapSystem(viewport));
        engine.addSystem(new BoundsSystem());
        engine.addSystem(new ObstacleSpawnSystem(factory));
        engine.addSystem(new CleanupSystem());

        engine.addSystem(new GridRenderSystem(viewport, renderer));
        engine.addSystem(new DebugRenderSystem(viewport, renderer));
        factory.addPlayer();
    }


    @Override
    public void render(float delta) {
        GdxUtils.clearScreen();
        engine.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
