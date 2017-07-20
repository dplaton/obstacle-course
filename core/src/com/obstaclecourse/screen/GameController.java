package com.obstaclecourse.screen;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.obstaclecourse.ObstacleCourseGame;
import com.obstaclecourse.assets.AssetDescriptors;
import com.obstaclecourse.common.GameManager;
import com.obstaclecourse.config.GameConfig;
import com.obstaclecourse.entity.Background;
import com.obstaclecourse.entity.Obstacle;
import com.obstaclecourse.entity.Player;

/**
 * Contains all the logic of our games - manages objects, keeps score, state, check collisions etc.
 */

public class    GameController {

    /* Constants */
    private static final Logger LOG = new Logger(GameController.class.getName(), Logger.DEBUG);
    private final ObstacleCourseGame game;
    private final AssetManager assetManager;

    /* Game objects */
    private Player player;
    private Array<Obstacle> obstacles = new Array<Obstacle>();
    private Background background;

    /* Game parameters */
    private float obstacleTimer = 0;
    private int lives = GameConfig.LIVES_START;
    private int score;
    private final float startPlayerY = (GameConfig.WORLD_HEIGHT - GameConfig.PLAYER_SIZE) / 2f;
    private final float startPlayerX = 1 - GameConfig.PLAYER_SIZE / 2;
    private Pool<Obstacle> obstaclePool;
    private Sound pickedUp;

    public GameController(ObstacleCourseGame game) {
        this.game = game;
        this.assetManager = game.getAssetManager();
        init();
    }


    private void init() {
        player = new Player();

        float startPlayerX = 1;
        float startPlayerY = (GameConfig.WORLD_HEIGHT - player.getHeight()) / 2f;
        player.setPosition(startPlayerX, startPlayerY);

        background = new Background();
        background.setPosition(0, 0);
        background.setSize(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT);

        obstaclePool = Pools.get(Obstacle.class, 40);

        pickedUp = assetManager.get(AssetDescriptors.PICKUP_SOUND);
    }

    /* Public methods */

    public void update(float delta) {
        // update the game objects first

        if (isGameOver()) {
            return;
        }
        updatePlayer();
        updateObstacles(delta);

        // check collisions and decide if we continue or not
        Obstacle collected = getCollectedObstacle();
        if (collected != null) {
            pickedUp.play();
            score += GameConfig.COLLECTABLE_VALUE;
            removeObstacle(collected);

        }

        if (missedObstacle()) {
            lives--;
            if (isGameOver()) {
                LOG.debug("Game over");
                GameManager.getInstance().updateHighScore(score);
            } else {
                restartGame();
            }
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Array<Obstacle> getObstacles() {
        return obstacles;
    }

    public int getLives() {
        return lives;
    }

    public int getDisplayScore() {
        return score;
    }

    public Background getBackground() {
        return background;
    }

    public boolean isGameOver() {
        return lives <= 0;
    }

    /* Private methods */

    private void restartGame() {
        // free all obstacles from the pool
        obstaclePool.freeAll(obstacles);
        obstaclePool.clear();
        obstacles.clear();

        player.setPosition(startPlayerX, startPlayerY);
    }

    private void removeObstacle(Obstacle obstacle) {
        obstaclePool.free(obstacle);
        obstacles.removeValue(obstacle, true);
    }

    private Obstacle getCollectedObstacle() {
        for (Obstacle obs : obstacles) {
            if (obs.isNotHit() && obs.isCollidingWithPlayer(player)) {
                LOG.debug("Collected!");
                return obs;
            }
        }
        return null;
    }

    private void updatePlayer() {
        blockPlayerFromLeavingTheWorld();
        player.update();
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

            Obstacle obs = obstaclePool.obtain();

            float min = obs.getHeight() / 2;
            float max = GameConfig.WORLD_HEIGHT- obs.getHeight();

            float obsY = MathUtils.random(min, max);
            float obsX = GameConfig.WORLD_WIDTH;

            obs.setXSpeed(GameManager.getInstance().getDifficultyLevel().getObstacleSpeed());
            obs.setPosition(obsX, obsY);
            obstacles.add(obs);
            obstacleTimer = 0f;
        }


    }

    private boolean missedObstacle() {
        if (obstacles.size > 0) {
            Obstacle first = obstacles.first();

            float minObstacleX = -first.getHeight() / 2;
            if (first.getX() < minObstacleX) {
                return true;
            }
        }
        return false;
    }

    private void blockPlayerFromLeavingTheWorld() {
        float playerY = MathUtils.clamp(player.getY(), 0, GameConfig.WORLD_HEIGHT - player.getHeight());

        // the expression above is similar to:

//        if (playerX < player.getWidth() / 2f) {
//            playerX = player.getWidth() / 2f;
//        } else if (playerX > GameConfig.WORLD_WIDTH - player.getWidth() / 2f) {
//            playerX = GameConfig.WORLD_WIDTH - player.getWidth() / 2f;
//            }

        player.setPosition(player.getX(), playerY);
    }


}
