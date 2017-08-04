package com.obstaclecourse.component;

import com.badlogic.ashley.core.Component;

/**
 * A component which adds dimensions to an entity
 */

public class DimensionComponent implements Component {

    /**
     * The width of the dimension
     */
    public float width;
    /**
     * The height of the dimension
     */
    public float height;

}
