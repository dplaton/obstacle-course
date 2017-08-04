package com.obstaclecourse.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * The component which adds texture to the entities
 */

public class TextureComponent implements Component {
    /**
     * The {@link TextureRegion} defining the texture
     */
    public TextureRegion textureRegion;
}
