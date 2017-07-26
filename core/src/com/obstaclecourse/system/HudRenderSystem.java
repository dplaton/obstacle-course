package com.obstaclecourse.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.obstaclecourse.common.GameManager;
import com.obstaclecourse.config.GameConfig;

/**
 * Created by platon on 26/07/2017.
 */

public class HudRenderSystem extends EntitySystem {
    private static final Logger LOG = new Logger(HudRenderSystem.class.getName(), Logger.DEBUG);
    private final Viewport viewport;
    private final SpriteBatch batch;
    private final BitmapFont font;

    public HudRenderSystem(Viewport viewport, SpriteBatch batch, BitmapFont font) {
        this.viewport = viewport;
        this.batch = batch;
        this.font = font;
    }

    private final GlyphLayout layout = new GlyphLayout();


    @Override
    public void update(float deltaTime) {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        draw();

        batch.end();
    }

    private void draw() {
        String livesString = "Lives: " + GameManager.getInstance().getLives();
        layout.setText(font,livesString);
        font.draw(batch, livesString, 20, GameConfig.HUD_HEIGHT - layout.height);

        String scoreString = "Score: " + GameManager.getInstance().getScore();
        layout.setText(font, scoreString);
        font.draw(batch, scoreString, GameConfig.HUD_WIDTH - layout.width - 20, GameConfig.HUD_HEIGHT - layout.height);
    }
}
