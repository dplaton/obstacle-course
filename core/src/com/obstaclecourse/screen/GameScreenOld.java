package com.obstaclecourse.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.obstaclecourse.assets.AssetPaths;
import com.obstaclecourse.config.DifficultyLevel;
import com.obstaclecourse.config.GameConfig;
import com.obstaclecourse.entity.Obstacle;
import com.obstaclecourse.entity.Player;
import com.obstaclecourse.util.GdxUtils;
import com.obstaclecourse.util.ViewportUtils;
import com.obstaclecourse.util.debug.DebugCameraController;

/**
 * One of our game screens. A {@link Screen} is responsible for rendering all elements in it (doh!)
 */
@Deprecated
public class GameScreenOld implements Screen {

    private static final Logger LOG = new Logger(GameScreenOld.class.getName(), Logger.DEBUG);

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;
    private OrthographicCamera hudCamera;
    private Viewport hudViewport;
    private SpriteBatch batch;
    private BitmapFont font;
    private final GlyphLayout glyphLayout = new GlyphLayout();
    private DebugCameraController debugController;
    private Player player;
    private Array<Obstacle> obstacles = new Array<Obstacle>();
    private float obstacleTimer = 0;
    private float scoreTimer = 0f;
    private static int lives = GameConfig.LIVES_START;
    private int score;
    private int displayScore;
    private DifficultyLevel difficultyLevel = DifficultyLevel.MEDIUM;



    @Override
    public void show() {

        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        renderer = new ShapeRenderer();

        // create player

        player = new Player();

        float startPlayerX = GameConfig.WORLD_WIDTH / 2;
        float startPlayerY = 1;

        player.setPosition(startPlayerX, startPlayerY);

        debugController = new DebugCameraController();
        debugController.setStartPosition(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y);

        hudCamera = new OrthographicCamera();
        hudViewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT, hudCamera);
        batch = new SpriteBatch();
        font = GdxUtils.generateStandardFont(AssetPaths.UI_FONT);
    }

    @Override
    public void render(float delta) {

        debugController.handleDebugInput(delta);
        debugController.applyTo(camera);


        update(delta);
        // clear the screen
        GdxUtils.clearScreen();

        //render the HUD
        renderHud();

        // render the debug data
        renderDebug();
    }


    private void update(float delta) {
        // update the game objects first

        if (isGameOver()) {
            LOG.debug("Game over");
            return;
        }
        updatePlayer();
        updateObstacles(delta);
        updateScore(delta);
        updateDisplayScore(delta);

        // check collisions and decide if we continue or not
        if (isPlayerCollidingWithObstacle()) {
            lives--;
        }
    }

    private boolean isGameOver() {
        return lives <= 0;
    }

    private void updateScore(float delta) {
        scoreTimer += delta;
        if (scoreTimer >= GameConfig.SCORE_MAX_TIME) {
            score += MathUtils.random(1, 5);
            scoreTimer = 0.0f;
        }
    }

    private void updateDisplayScore(float delta) {
        if (displayScore < score) {
            displayScore = Math.min(score, displayScore + (int) (60 * delta));
        }
    }

    private boolean isPlayerCollidingWithObstacle() {
        for (Obstacle obs : obstacles) {
            if (obs.isNotHit() && obs.isCollidingWithPlayer(player)) {
                return true;
            }
        }
        return false;
    }


    private void updatePlayer() {
        blockPlayerFromLeavingTheWorld();
        player.update();
    }

    private void renderHud() {
        batch.begin();
        batch.setProjectionMatrix(hudCamera.combined);

        String liveText = "Lives: " + lives;
        glyphLayout.setText(font, liveText);

        String scoreText = "Score: " + displayScore;
        glyphLayout.setText(font, scoreText);


        font.draw(batch, liveText, 20, GameConfig.HUD_HEIGHT - glyphLayout.height);
        font.draw(batch, scoreText, GameConfig.HUD_WIDTH - glyphLayout.width - 20, GameConfig.HUD_HEIGHT - glyphLayout.height);
        batch.end();
    }


    private void renderDebug() {
        ViewportUtils.drawGrid(viewport, renderer);
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        drawDebug();
        renderer.end();
    }

    private void blockPlayerFromLeavingTheWorld() {
        float playerX = MathUtils.clamp(player.getX(), player.getWidth() / 2f, GameConfig.WORLD_WIDTH - player.getWidth() / 2f);

        // the expression above is similar to:

//        if (playerX < player.getWidth() / 2f) {
//            playerX = player.getWidth() / 2f;
//        } else if (playerX > GameConfig.WORLD_WIDTH - player.getWidth() / 2f) {
//            playerX = GameConfig.WORLD_WIDTH - player.getWidth() / 2f;
//            }

        player.setPosition(playerX, player.getY());
    }

    private void updateObstacles(float delta) {
        for (Obstacle o : obstacles) {
            o.update();
        }

        createNewObstacle(delta);
    }

    private void createNewObstacle(float delta) {
        obstacleTimer += delta;
        if (obstacleTimer >= GameConfig.OBSTACLE_SPAWN_TIME) {
            float min = 0f;
            float max = GameConfig.WORLD_WIDTH;
            float obsX = MathUtils.random(min, max);
            float obsY = GameConfig.WORLD_HEIGHT;
            Obstacle obs = new Obstacle();
            obs.setXSpeed(difficultyLevel.getObstacleSpeed());
            obs.setPosition(obsX, obsY);
            obstacles.add(obs);
            obstacleTimer = 0f;
        }


    }


    private void drawDebug() {
        player.drawDebug(renderer);
        for (Obstacle obs : obstacles) {
            obs.drawDebug(renderer);
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        hudViewport.update(width, height, true);
        ViewportUtils.debugPixelPerUnit(viewport);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        renderer.dispose();
        batch.dispose();
        font.dispose();
    }
}
