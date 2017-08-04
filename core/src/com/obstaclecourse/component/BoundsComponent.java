package com.obstaclecourse.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Circle;

/**
 * A component containing the bounds of an object.
 * Usually this component is added to objects which support collisions.
 */

public class BoundsComponent implements Component {
    public Circle bounds = new Circle();
}
