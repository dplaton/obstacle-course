package com.obstaclecourse.screen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.obstaclecourse.ObstacleCourseGame;
import com.obstaclecourse.assets.AssetDescriptors;
import com.obstaclecourse.config.GameConfig;
import com.obstaclecourse.util.GdxUtils;

/**
 * Created by platon on 18/07/2017.
 */

public abstract class MenuScreenBase extends ScreenAdapter {

    protected ObstacleCourseGame game;
    protected final AssetManager assetManager;
    protected final Skin uiSkin;

    private Viewport viewport;
    private Stage stage;

    public MenuScreenBase(ObstacleCourseGame game) {
        this.game = game;
        this.assetManager = game.getAssetManager();
        this.uiSkin = assetManager.get(AssetDescriptors.UI_SKIN);
    }

    @Override
    public void show() {
        viewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT);
        stage = new Stage(viewport, game.getSpriteBatch());
        Gdx.input.setInputProcessor(stage);
        stage.addActor(createUi());
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render(float delta) {
        GdxUtils.clearScreen();
        stage.act();
        stage.draw();
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    protected abstract Actor createUi();

    protected ImageButton createButton(TextureAtlas atlas, String upRegionName, String downRegionName) {
        TextureRegion up = atlas.findRegion(upRegionName);
        if (downRegionName != null) {
            TextureRegion down = atlas.findRegion(downRegionName);
            return new ImageButton(new TextureRegionDrawable(up), new TextureRegionDrawable(down));
        } else {
            return new ImageButton(new TextureRegionDrawable(up));
        }
    }

    protected ImageButton createButton(TextureAtlas atlas, String regionName) {
        return createButton(atlas, regionName, null);
    }
}
