package com.obstaclecourse.component;

import com.badlogic.ashley.core.Component;

/**
 * A marker component attached to entities which are meant to be picked up by the cleanup system.
 * Examples: obstacles, collectibles
 */

public class CleanupComponent implements Component {

    //doesn't contain anything.
    // used to mark an entity that is a candidate for clean-up
}
