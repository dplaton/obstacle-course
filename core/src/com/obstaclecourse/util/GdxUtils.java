package com.obstaclecourse.util;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Created by platon on 02/07/2017.
 */
public class GdxUtils {

    private GdxUtils() {
    }


    public static void clearScreen(Color color) {
        Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public static void clearScreen() {
        clearScreen(Color.BLACK);
    }

    /**
     * Generates a {@link BitmapFont} object from a given font file name
     *
     * @param fontFilePath the path (relative to the working directory) to the TTF / TTC file
     * @param color        the color of the font
     * @param size         the size of the font
     * @return the corresponding {@link BitmapFont} object.
     */
    public static BitmapFont generateFont(String fontFilePath, Color color, int size) {
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal(fontFilePath));
        FreeTypeFontGenerator.FreeTypeFontParameter pa = new FreeTypeFontGenerator.FreeTypeFontParameter();
        pa.size = 32;
        pa.color = Color.WHITE;
        BitmapFont generated = gen.generateFont(pa);
        gen.dispose();

        return generated;
    }

    public static BitmapFont generateStandardFont(String fontFilePath) {
        return generateFont(fontFilePath, Color.WHITE, 32);
    }
}
