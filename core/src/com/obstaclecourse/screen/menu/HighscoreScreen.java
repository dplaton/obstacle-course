package com.obstaclecourse.screen.menu;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Logger;
import com.obstaclecourse.ObstacleCourseGame;
import com.obstaclecourse.assets.AssetDescriptors;
import com.obstaclecourse.assets.RegionNames;
import com.obstaclecourse.common.GameManager;

/**
 * Created by platon on 18/07/2017.
 */

public class HighscoreScreen extends MenuScreenBase {
    private static final Logger LOG = new Logger(HighscoreScreen.class.getName(), Logger.DEBUG);


    public HighscoreScreen(ObstacleCourseGame game) {
        super(game);
    }

    @Override
    protected Actor createUi() {
        Table table = new Table();

        TextureAtlas gameplayAtlas = assetManager.get(AssetDescriptors.GAMEPLAY_ASSET_DESCRIPTOR);
        TextureRegion background = gameplayAtlas.findRegion(RegionNames.TR_BACKGROUND);

        table.center().setFillParent(true);
        table.setBackground(new TextureRegionDrawable(background));

        Label highscoreText = new Label("Highscore", uiSkin);
        Label highscoreLabel = new Label(GameManager.getInstance().getHighScore(), uiSkin);

        TextButton backButton = new TextButton("Back", uiSkin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                goBack();
            }
        });

        Table contentTable = new Table(uiSkin);
        contentTable.defaults().pad(20);
        contentTable.setBackground(RegionNames.PANEL);

        contentTable.add(highscoreText).row();
        contentTable.add(highscoreLabel).row();
        contentTable.add(backButton);

        contentTable.center();
        table.add(contentTable);
        table.center();
        table.pack();

        return table;
    }

    private void goBack() {
        LOG.debug("Going back");
        game.setScreen(new MenuScreen(game));
    }
}
