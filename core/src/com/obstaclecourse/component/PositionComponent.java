package com.obstaclecourse.component;

import com.badlogic.ashley.core.Component;

/**
 * A component which adds position when attached to entities
 */

public class PositionComponent implements Component {
    /**
     * The X axis coordinate
     */
    public float x;
    /**
     * The Y axis coordinate
     */
    public float y;
}
