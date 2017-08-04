package com.obstaclecourse.config;

/**
 * The difficulty level of the game
 */

public enum DifficultyLevel {

    EASY(GameConfig.EASY_OBSTACLE_SPEED),
    MEDIUM(GameConfig.MEDIUM_OBSTACLE_SPEED),
    HARD(GameConfig.HARD_OBSTACLE_SPEED);

    public final float obstacleSpeed;

    /**
     * Constructs a new entry in the enum
     * @param obstacleSpeed the obstacle speed corresponding to this difficulty level
     */
    DifficultyLevel(float obstacleSpeed) {
        this.obstacleSpeed = obstacleSpeed;
    }

    public float getObstacleSpeed() {
        return obstacleSpeed;
    }

    public boolean isEasy() {
        return this == DifficultyLevel.EASY;
    }

    public boolean isHard() {
        return this == DifficultyLevel.HARD;
    }

    public boolean isMedium() {
        return this == DifficultyLevel.MEDIUM;
    }
}
