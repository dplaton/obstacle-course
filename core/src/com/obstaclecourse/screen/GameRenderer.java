package com.obstaclecourse.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.obstaclecourse.assets.AssetDescriptors;
import com.obstaclecourse.assets.AssetPaths;
import com.obstaclecourse.assets.RegionNames;
import com.obstaclecourse.config.GameConfig;
import com.obstaclecourse.entity.Background;
import com.obstaclecourse.entity.Obstacle;
import com.obstaclecourse.entity.Player;
import com.obstaclecourse.util.GdxUtils;
import com.obstaclecourse.util.ViewportUtils;
import com.obstaclecourse.util.debug.DebugCameraController;

/**
 * Created by platon on 14/07/2017.
 */
@Deprecated
public class GameRenderer extends InputAdapter implements Disposable{

    private static final Logger LOG = new Logger(GameRenderer.class.getName(), Logger.DEBUG);

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;
    private OrthographicCamera hudCamera;
    private Viewport hudViewport;
    private final SpriteBatch batch;
    private BitmapFont font;
    private final GlyphLayout glyphLayout = new GlyphLayout();
    private DebugCameraController debugController;
    private final GameController controller;
    private final AssetManager assetManager;

    private TextureRegion playerTexture;
    private TextureRegion obstacleTexture;
    private TextureRegion backgroundTexture;

    private boolean renderDebugGraphics = false;

    public GameRenderer(GameController controller, AssetManager assetManager, SpriteBatch batch) {
        this.controller = controller;
        this.assetManager = assetManager;
        this.batch = batch;
        init();
    }

    private void init() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        renderer = new ShapeRenderer();
        debugController = new DebugCameraController();
        debugController.setStartPosition(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y);

        hudCamera = new OrthographicCamera();
        hudViewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT, hudCamera);
        font = GdxUtils.generateStandardFont(AssetPaths.UI_FONT);

        TextureAtlas gameplayAtlas = assetManager.get(AssetDescriptors.GAMEPLAY_ASSET_DESCRIPTOR);

        playerTexture = gameplayAtlas.findRegion(RegionNames.TR_PLAYER);
        obstacleTexture =gameplayAtlas.findRegion(RegionNames.TR_OBSTACLE);
        backgroundTexture = gameplayAtlas.findRegion(RegionNames.TR_BACKGROUND);

        Gdx.input.setInputProcessor(this);
    }

    public void render(float delta) {

        debugController.handleDebugInput(delta);
        debugController.applyTo(camera);

        if (Gdx.input.isTouched() && !controller.isGameOver()) {
            Vector2 screenTouch = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            // convert the coordinates above to world units
            Vector2 worldTouch = viewport.unproject(screenTouch);
            Player player = controller.getPlayer();

            worldTouch.y = MathUtils.clamp(worldTouch.y, 0, GameConfig.HEIGHT - player.getHeight());
            player.setY(worldTouch.y);
        }

        // clear the screen
        GdxUtils.clearScreen();

        controller.update(delta);

        // render the game objects (background, player, obstacles)
        renderGamplay();

        //render the HUD
        renderHud();

        // render the debug data
        if (renderDebugGraphics) {
            renderDebug();
        }
    }

    public void resize(int width, int height) {
        viewport.update(width, height, true);
        hudViewport.update(width, height, true);
        ViewportUtils.debugPixelPerUnit(viewport);
    }

    @Override
    public void dispose() {
        renderer.dispose();
        font.dispose();
    }



    /* PRIVATE METHODS */
    private void renderGamplay() {
        viewport.apply();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        Background background = controller.getBackground();
        batch.draw(backgroundTexture,
                background.getX(),  background.getY(),
                background.getWidth(), background.getHeight());

        Player player = controller.getPlayer();
        batch.draw(playerTexture,
                player.getX(), player.getY(),
                player.getWidth(), player.getHeight());

        for (Obstacle o : controller.getObstacles()) {
            batch.draw(obstacleTexture,
                    o.getX(), o.getY(),
                    o.getWidth(), o.getHeight());
        }

        batch.end();
    }

    private void renderHud() {

        // whenever we use different viewports w/ different sizes we need to call apply
        // so that OpenGL knows about the viewport is drawing in
        hudViewport.apply();
        batch.begin();
        batch.setProjectionMatrix(hudCamera.combined);

        String liveText = "Lives: " + controller.getLives();
        glyphLayout.setText(font, liveText);

        String scoreText = "Score: " + controller.getDisplayScore();
        glyphLayout.setText(font, scoreText);


        font.draw(batch, liveText, 20, GameConfig.HUD_HEIGHT - glyphLayout.height);
        font.draw(batch, scoreText, GameConfig.HUD_WIDTH - glyphLayout.width - 20, GameConfig.HUD_HEIGHT - glyphLayout.height);
        batch.end();
    }


    private void renderDebug() {
        ViewportUtils.drawGrid(viewport, renderer);
        viewport.apply();
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        drawDebug();
        renderer.end();
    }


    private void drawDebug() {
        controller.getPlayer().drawDebug(renderer);
        for (Obstacle obs : controller.getObstacles()) {
            obs.drawDebug(renderer);
        }
    }


    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.G) {
            renderDebugGraphics = !renderDebugGraphics;
        }
        return super.keyDown(keycode);
    }
}
