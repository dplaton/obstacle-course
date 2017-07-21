package com.obstaclecourse.system.debug;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.obstaclecourse.util.ViewportUtils;

/**
 * Created by platon on 21/07/2017.
 */

public class GridRenderSystem extends EntitySystem {

    private static final Logger LOG = new Logger(GridRenderSystem.class.getName(), Logger.DEBUG);

    private final Viewport viewport;
    private final ShapeRenderer renderer;

    public GridRenderSystem(Viewport viewport, ShapeRenderer renderer) {
        this.viewport = viewport;
        this.renderer = renderer;
    }

    @Override
    public void update(float deltaTime) {
        viewport.apply();
        ViewportUtils.drawGrid(viewport, renderer);
    }
}
