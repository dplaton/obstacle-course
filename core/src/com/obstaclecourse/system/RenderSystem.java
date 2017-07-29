package com.obstaclecourse.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.obstaclecourse.common.Mappers;
import com.obstaclecourse.component.DimensionComponent;
import com.obstaclecourse.component.PositionComponent;
import com.obstaclecourse.component.TextureComponent;

/**
 * Created by platon on 28/07/2017.
 */

public class RenderSystem extends EntitySystem {

    private static final Family FAMILY = Family.all(
            TextureComponent.class,
            DimensionComponent.class,
            PositionComponent.class).get();

    private final Viewport viewport;
    private final SpriteBatch batch;

    private Array<Entity> renderQueue = new Array<>();

    public RenderSystem(Viewport viewport, SpriteBatch batch) {
        this.viewport = viewport;
        this.batch = batch;
    }

    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> entities = getEngine().getEntitiesFor(FAMILY);
        renderQueue.addAll(entities.toArray());

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();
        draw();

        batch.end();
        renderQueue.clear();
    }

    private void draw() {
        for (Entity entity: renderQueue) {
            PositionComponent position = Mappers.POSITION_MAPPER.get(entity);
            DimensionComponent dimension = Mappers.DIMENSION_MAPPER.get(entity);
            TextureComponent textureComponent = Mappers.TEXTURE_MAPPER.get(entity);

            batch.draw(textureComponent.textureRegion, position.x, position.y, dimension.width, dimension.height);
        }
    }
}
