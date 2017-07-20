package com.obstaclecourse.desktop;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

/**
 * Created by platon on 16/07/2017.
 */

public class AssetPacker {

    private static final String RAW_ASSETS_PATH = "desktop/assets-raw";
    private static final boolean DRAW_DEBUG_OUTLINE = false;
    private static final String ASSETS_PATH = "android/assets";

    public static void main(String[] args) {
        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.debug = DRAW_DEBUG_OUTLINE;
        settings.maxHeight = 2048;
        settings.maxWidth = 2048;

        TexturePacker.process(settings, RAW_ASSETS_PATH + "/gameplay", ASSETS_PATH+ "/gameplay", "gameplay");
        TexturePacker.process(settings, RAW_ASSETS_PATH + "/skin", ASSETS_PATH + "/ui", "ui-skin");
    }

}
