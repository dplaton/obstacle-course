package com.obstaclecourse.screen.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.obstaclecourse.ObstacleCourseGame;
import com.obstaclecourse.assets.AssetDescriptors;
import com.obstaclecourse.assets.AssetPaths;
import com.obstaclecourse.common.EntityFactory;
import com.obstaclecourse.common.GameManager;
import com.obstaclecourse.component.PickupComponent;
import com.obstaclecourse.config.GameConfig;
import com.obstaclecourse.screen.menu.MenuScreen;
import com.obstaclecourse.system.BoundsSystem;
import com.obstaclecourse.system.CleanupSystem;
import com.obstaclecourse.system.CoinSpawnSystem;
import com.obstaclecourse.system.HudRenderSystem;
import com.obstaclecourse.system.LifeSpawnSystem;
import com.obstaclecourse.system.MovementSystem;
import com.obstaclecourse.system.ObstacleSpawnSystem;
import com.obstaclecourse.system.PlayerSystem;
import com.obstaclecourse.system.RenderSystem;
import com.obstaclecourse.system.ScoreSystem;
import com.obstaclecourse.system.WorldWrapSystem;
import com.obstaclecourse.system.collision.CollisionListener;
import com.obstaclecourse.system.collision.CollisionSystem;
import com.obstaclecourse.system.debug.DebugCameraSystem;
import com.obstaclecourse.system.debug.DebugRenderSystem;
import com.obstaclecourse.system.debug.GridRenderSystem;
import com.obstaclecourse.util.GdxUtils;

/**
 * Created by platon on 14/07/2017.
 */
public class GameScreen implements Screen {

    private static final Logger LOG = new Logger(GameScreen.class.getName(), Logger.DEBUG);
    private static final boolean DEBUG = false;

    private final ObstacleCourseGame game;
    private final AssetManager assetManager;

    private Viewport viewport;
    private ShapeRenderer renderer;
    private PooledEngine engine;
    private OrthographicCamera camera;
    private EntityFactory factory;
    private Viewport hudViewport;
    private Sound lifeCollected;
    private Sound coinCollected;
    private Sound obstacleHit;
    private boolean reset;


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
        factory = new EntityFactory(engine, assetManager);

        hudViewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT);
        BitmapFont font = GdxUtils.generateStandardFont(AssetPaths.UI_FONT);
        lifeCollected = assetManager.get(AssetDescriptors.LIFE_SOUND);
        obstacleHit = assetManager.get(AssetDescriptors.HIT_SOUND);
        coinCollected = assetManager.get(AssetDescriptors.PICKUP_SOUND);

        CollisionListener listener = new CollisionListener() {
            @Override
            public void hitObstacle() {
                if (DEBUG) {
                    return;
                }
                GameManager.getInstance().decrementLives();
                obstacleHit.play();
                if (GameManager.getInstance().isGameOver()) {
                    GameManager.getInstance().updateHighScore();
                } else {
                    engine.removeAllEntities();
                    reset = true;
                }
            }

            @Override
            public void collect(Entity entity) {
                PickupComponent pickup = entity.getComponent(PickupComponent.class);
                LOG.debug("Collected a " + pickup.pickupType);
                if (pickup.pickupType.isLife()) {
                    lifeCollected.play();
                    GameManager.getInstance().incrementLives();
                } else if (pickup.pickupType.isCoin()) {
                    GameManager.getInstance().incrementScore(GameConfig.COLLECTABLE_VALUE);
                    coinCollected.play();
                }
                engine.removeEntity(entity);
            }

        };

        // the order in which we add the systems matter!
        engine.addSystem(new PlayerSystem());
        engine.addSystem(new MovementSystem());
        engine.addSystem(new WorldWrapSystem(viewport));
        engine.addSystem(new BoundsSystem());
        engine.addSystem(new ObstacleSpawnSystem(factory));
        engine.addSystem(new LifeSpawnSystem(factory));
        engine.addSystem(new CoinSpawnSystem(factory));
        engine.addSystem(new CollisionSystem(listener));
        engine.addSystem(new CleanupSystem());

        engine.addSystem(new RenderSystem(viewport, game.getSpriteBatch()));
        if (DEBUG) {
            engine.addSystem(new DebugCameraSystem(camera, GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y));
            engine.addSystem(new GridRenderSystem(viewport, renderer));
            engine.addSystem(new DebugRenderSystem(viewport, renderer));
        }
        engine.addSystem(new HudRenderSystem(hudViewport, game.getSpriteBatch(), font));
        engine.addSystem(new ScoreSystem());
        addEntities();
    }


    @Override
    public void render(float delta) {
        GdxUtils.clearScreen();
        engine.update(delta);
        if (reset) {
            reset = !reset;
            addEntities();
        }

        if (GameManager.getInstance().isGameOver()) {
            GameManager.getInstance().reset();
            game.setScreen(new MenuScreen(game));
        }
    }

    private void addEntities() {
        factory.addBackground();
        factory.addPlayer();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        hudViewport.update(width, height, true);
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
