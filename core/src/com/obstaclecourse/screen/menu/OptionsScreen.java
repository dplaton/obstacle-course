package com.obstaclecourse.screen.menu;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Logger;
import com.obstaclecourse.ObstacleCourseGame;
import com.obstaclecourse.assets.AssetDescriptors;
import com.obstaclecourse.assets.RegionNames;
import com.obstaclecourse.common.GameManager;
import com.obstaclecourse.config.DifficultyLevel;

/**
 * A screen that shows the game options.
 */

public class OptionsScreen extends MenuScreenBase {
    private static final Logger LOG = new Logger(OptionsScreen.class.getName(), Logger.DEBUG);

    private ButtonGroup<CheckBox> checkboxGroup;
    private CheckBox easy;
    private CheckBox medium;
    private CheckBox hard;

    public OptionsScreen(ObstacleCourseGame game) {
       super(game);
    }

    @Override
    protected Actor createUi() {
        Table table = new Table();
        table.defaults().pad(15);
        table.setFillParent(true);

        TextureAtlas gameplayAtlas = assetManager.get(AssetDescriptors.GAMEPLAY_ASSET_DESCRIPTOR);

        TextureRegion background = gameplayAtlas.findRegion(RegionNames.TR_BACKGROUND);
        table.setBackground(new TextureRegionDrawable(background));

        Label label = new Label("Difficulty", uiSkin);

        easy = checkBox(DifficultyLevel.EASY.name(),uiSkin);
        medium = checkBox(DifficultyLevel.MEDIUM.name(),uiSkin);
        hard = checkBox(DifficultyLevel.HARD.name(),uiSkin);

        checkboxGroup = new ButtonGroup<>(easy, medium, hard);
        DifficultyLevel level = GameManager.getInstance().getDifficultyLevel();
        checkboxGroup.setChecked(level.name());

        TextButton backButton = new TextButton("Back", uiSkin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                goBack();
            }
        });

        ChangeListener difficultyChangeListener = new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                difficultyChanged();
            }
        };

        easy.addListener(difficultyChangeListener);
        medium.addListener(difficultyChangeListener);
        hard.addListener(difficultyChangeListener);

        Table contentTable = new Table(uiSkin);
        contentTable.defaults().pad(10);
        contentTable.setBackground(RegionNames.PANEL);

        contentTable.add(label).row();
        contentTable.add(easy).row();
        contentTable.add(medium).row();
        contentTable.add(hard).row();
        contentTable.add(backButton).row();


        table.add(contentTable);
        table.center();
        table.pack();

        return table;
    }


    private void goBack() {
        LOG.debug("Going back");
        game.setScreen(new MenuScreen(game));
    }

    private void difficultyChanged() {
        CheckBox checked = checkboxGroup.getChecked();
        if (checked == easy) {
            GameManager.getInstance().updateDifficulty(DifficultyLevel.EASY);
        } else if (checked == medium) {
            GameManager.getInstance().updateDifficulty(DifficultyLevel.MEDIUM);
        } else if (checked == hard) {
            GameManager.getInstance().updateDifficulty(DifficultyLevel.HARD);
        }
    }

    private CheckBox checkBox(String text, Skin skin) {
        CheckBox checkBox = new CheckBox(text, skin);
        checkBox.left().pad(10);
        checkBox.getLabelCell().pad(8);
        return checkBox;
    }
}
