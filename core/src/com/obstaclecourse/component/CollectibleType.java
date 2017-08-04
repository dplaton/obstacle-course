package com.obstaclecourse.component;

/**
 * The type of the collectible
 */

public enum CollectibleType {

    LIFE,
    COIN;

    public boolean isLife() {
        return this == LIFE;
    }

    public boolean isCoin() {
        return this == COIN;
    }
}
