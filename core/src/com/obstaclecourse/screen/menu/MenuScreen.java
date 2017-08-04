package com.obstaclecourse.screen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Logger;
import com.obstaclecourse.ObstacleCourseGame;
import com.obstaclecourse.assets.AssetDescriptors;
import com.obstaclecourse.assets.RegionNames;
import com.obstaclecourse.screen.game.GameScreen;

/**
 * The screen which shows the main menu
 */

public class MenuScreen extends MenuScreenBase {

    private static final Logger LOG = new Logger(MenuScreen.class.getName(), Logger.DEBUG);

    public MenuScreen(ObstacleCourseGame game) {
        super(game);
    }

    @Override
    protected Actor createUi() {
        Table table = new Table();

        TextureAtlas gameplayAtlas = assetManager.get(AssetDescriptors.GAMEPLAY_ASSET_DESCRIPTOR);
        TextureRegion background = gameplayAtlas.findRegion(RegionNames.TR_BACKGROUND);

        TextButton playButton = new TextButton("Play", uiSkin);
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                play();
            }
        });

        TextButton highscoreButton = new TextButton("High scores", uiSkin);
        highscoreButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                showHighscore();
            }
        });

        TextButton optionsButton = new TextButton("Options", uiSkin);
        optionsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                showOptions();
            }
        });

        TextButton quitButton = new TextButton("Quit", uiSkin);
        quitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                quit();
            }
        });

        Table buttonTable = new Table(uiSkin);
        buttonTable.defaults().pad(20);
        buttonTable.setBackground(RegionNames.PANEL);

        buttonTable.add(playButton).row();
        buttonTable.add(highscoreButton).row();
        buttonTable.add(optionsButton).row();
        buttonTable.add(quitButton).row();
        table.center().setFillParent(true);
        table.setBackground(new TextureRegionDrawable(background));

        table.add(buttonTable);
        table.row();
        table.pack();
        return table;
    }

    private void quit() {
        Gdx.app.exit();
    }

    private void play() {
        LOG.debug("Playing.");
        game.setScreen(new GameScreen(game));
    }

    private void showHighscore() {
        LOG.debug("Highscore");
        game.setScreen(new HighscoreScreen(game));
    }

    private void showOptions() {
        LOG.debug("Options");
        game.setScreen(new OptionsScreen(game));
    }

}
