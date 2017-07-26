package com.obstaclecourse.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.obstaclecourse.ObstacleCourseGame;
import com.obstaclecourse.config.DifficultyLevel;
import com.obstaclecourse.config.GameConfig;

/**
 * Created by platon on 18/07/2017.
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

    public String getHighScore() {
        return String.valueOf(highScore);
    }

    public void updateHighScore(int score) {
        if (score > highScore) {
            this.highScore = score;
            PREFS.putInteger(HIGHSCORE_KEY, score);
            PREFS.flush();
        }
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void updateDifficulty(DifficultyLevel difficultyLevel) {
        if (difficultyLevel != this.difficultyLevel) {
            PREFS.putString(DIFFICULTY_KEY, difficultyLevel.name());
            this.difficultyLevel = difficultyLevel;
        }
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }

    public static GameManager getInstance() {
        return INSTANCE;
    }



}
