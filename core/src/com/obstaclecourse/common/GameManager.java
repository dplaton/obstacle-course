package com.obstaclecourse.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.obstaclecourse.ObstacleCourseGame;
import com.obstaclecourse.config.DifficultyLevel;
import com.obstaclecourse.config.GameConfig;

/**
 * Singleton object which manages the game state
 */

public class GameManager {

    private static final GameManager INSTANCE = new GameManager();

    private static final String HIGHSCORE_KEY = "highscore";
    private static final String DIFFICULTY_KEY = "difficulty";

    private int highScore;
    private DifficultyLevel difficultyLevel;
    private int lives = GameConfig.LIVES_START;
    private int score;

    private Preferences PREFS;

    private GameManager() {
        PREFS = Gdx.app.getPreferences(ObstacleCourseGame.class.getSimpleName());
        this.highScore = PREFS.getInteger(HIGHSCORE_KEY, 0);
        String difficultyName = PREFS.getString(DIFFICULTY_KEY, DifficultyLevel.MEDIUM.name());
        difficultyLevel = DifficultyLevel.valueOf(difficultyName);
    }

    /**
     * Retrieves the high score of the game
     * @return an int value
     */
    public String getHighScore() {
        return String.valueOf(highScore);
    }

    /**
     * Updates the high score with the current score, if it's greater than the high score
     */
    public void updateHighScore() {
        if (this.score > this.highScore) {
            this.highScore = this.score;
            PREFS.putInteger(HIGHSCORE_KEY, score);
            PREFS.flush();
        }
    }

    /**
     * Returns the difficulty level set in the game
     * @return the level, as a value from {@link DifficultyLevel}
     */
    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    /**
     * Updates the difficulty level with a new value
     * @param difficultyLevel the new {@link DifficultyLevel}
     */
    public void updateDifficulty(DifficultyLevel difficultyLevel) {
        if (difficultyLevel != this.difficultyLevel) {
            PREFS.putString(DIFFICULTY_KEY, difficultyLevel.name());
            this.difficultyLevel = difficultyLevel;
        }
    }

    /**
     * Returns the number of "lives" that the player has left
     * @return an integer value
     */
    public int getLives() {
        return lives;
    }

    /**
     * Increments the number of lives by one unit
     */
    public void incrementLives() {
        lives++;
    }

    /**
     * Returns the current score of the game
     * @return and integer value
     */
    public int getScore() {
        return score;
    }

    /**
     * Decrements the number of lives
     */
    public void decrementLives() {
        this.lives--;
    }

    /**
     * Checks if the game is over (i.e. the number of lives is 0)
     * @return {@code true} if the number of lives is 0, {@code false} otherwise
     */
    public boolean isGameOver() {
        return lives <= 0;
    }

    /**
     * Resets the game state.
     * This operation involves settings the lives to {@link GameConfig#LIVES_START} and the score to 0
     */
    public void reset() {
        this.score = 0;
        this.lives = GameConfig.LIVES_START;
    }
    /**
     * Updates the score by the amount of the specified amount
     * @param amount the amount by which to increment the score
     */
    public void updateScore(int amount) {
        score += amount;
    }

    /**
     * Returns the instance of this object
     * @return the instance of {@link GameManager}
     */
    public static GameManager getInstance() {
        return INSTANCE;
    }



}
