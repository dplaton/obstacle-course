package com.obstaclecourse.system.debug;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.obstaclecourse.common.Mappers;
import com.obstaclecourse.component.BoundsComponent;

/**
 * Created by platon on 21/07/2017.
 */

public class DebugRenderSystem extends IteratingSystem {
    private static final Family FAMILY = Family.all(BoundsComponent.class).get();

    private final Viewport viewport;
    private final ShapeRenderer renderer;

    public DebugRenderSystem(Viewport viewport, ShapeRenderer renderer) {
        super(FAMILY);
        this.viewport = viewport;
        this.renderer = renderer;
    }


    @Override
    public void update(float deltaTime) {
        Color oldColor = renderer.getColor().cpy();
        viewport.apply();
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.CHARTREUSE);
        super.update(deltaTime);
        renderer.end();

        renderer.setColor(oldColor);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BoundsComponent bc = Mappers.BOUNDS_MAPPER.get(entity);
        renderer.circle(bc.bounds.x, bc.bounds.y, bc.bounds.radius, 30);
    }
}
