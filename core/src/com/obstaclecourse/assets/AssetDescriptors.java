package com.obstaclecourse.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * The {@link AssetDescriptor} objects used to retrieved the assets from the Asset Manager
 */

public class AssetDescriptors {

    public static final AssetDescriptor<TextureAtlas> GAMEPLAY_ASSET_DESCRIPTOR = new AssetDescriptor<TextureAtlas>(AssetPaths.GAMEPLAY_ATLAS, TextureAtlas.class);
    public static final AssetDescriptor<Skin> UI_SKIN = new AssetDescriptor<Skin>(AssetPaths.UI_SKIN, Skin.class);
    public static final AssetDescriptor<Sound> PICKUP_SOUND = new AssetDescriptor(AssetPaths.PICKUP_SOUND, Sound.class);
    public static final AssetDescriptor<Sound> HIT_SOUND = new AssetDescriptor<Sound>(AssetPaths.HIT_SOUND, Sound.class);
    public static final AssetDescriptor<Sound> LIFE_SOUND = new AssetDescriptor<Sound>(AssetPaths.LIFE_SOUND, Sound.class);

    private AssetDescriptors() {

    }

}
