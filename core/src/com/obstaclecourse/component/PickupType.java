package com.obstaclecourse.component;

/**
 * Created by platon on 03/08/2017.
 */

public enum PickupType {
    LIFE,
    COIN;

    public boolean isLife() {
        return this == LIFE;
    }

    public boolean isCoin() {
        return this == COIN;
    }
}
