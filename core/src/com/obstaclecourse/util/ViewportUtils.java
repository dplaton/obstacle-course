package com.obstaclecourse.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.text.MessageFormat;

/**
 * Created by platon on 07/07/2017.
 */
public class ViewportUtils {

    private static final Logger LOG = new Logger(ViewportUtils.class.getName(), Logger.DEBUG);

    private static final int DEFAULT_CELL_SIZE = 1;

    private ViewportUtils() {

    }

    public static void drawGrid(Viewport viewport, ShapeRenderer shapeRenderer) {
        drawGrid(viewport, shapeRenderer, DEFAULT_CELL_SIZE);
    }

    public static void drawGrid(Viewport viewport, ShapeRenderer shapeRenderer, int cellSize) {
        if (viewport == null) {
            throw new IllegalArgumentException("Viewport not supplied");
        }

        if (shapeRenderer == null) {
            throw new IllegalArgumentException("ShapeRenderer not supplied");
        }
        if (cellSize <= 0) {
            cellSize = DEFAULT_CELL_SIZE;
        }

        Color oldColor = new Color(shapeRenderer.getColor());

        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.TEAL);

        int worldWidth = (int) viewport.getWorldWidth();
        int worldHeight = (int) viewport.getWorldHeight();
        int doubleWorldWidth = worldWidth * 2;
        int doubleWorldHeight = worldHeight * 2;

        for (int x = -doubleWorldWidth; x < doubleWorldWidth; x+=cellSize) {
            shapeRenderer.line(x, -doubleWorldHeight, x, doubleWorldHeight);
        }
        for (int y = -doubleWorldHeight; y < doubleWorldHeight; y+=cellSize) {
            shapeRenderer.line(-doubleWorldWidth, y, doubleWorldWidth, y);
        }

        shapeRenderer.setColor(Color.RED);

        shapeRenderer.line(-worldWidth, 0, worldWidth, 0);
        shapeRenderer.line(0, -worldHeight, 0, worldHeight);

        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.line(0, worldHeight, worldWidth, worldHeight);
        shapeRenderer.line(worldWidth, worldHeight, worldWidth, 0);

        shapeRenderer.setColor(oldColor);
        shapeRenderer.end();
    }

    public static void debugPixelPerUnit(Viewport viewport) {
        if (viewport == null) {
            throw new IllegalArgumentException("The Viewport argument is null");
        }

        float screenWidth = viewport.getScreenWidth();
        float screenHeight = viewport.getScreenHeight();

        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        // pixels per world unit
        float xPPU = screenWidth / worldWidth;
        float yPPU = screenHeight / worldHeight;

        LOG.debug(MessageFormat.format("xPPU = {0}; yPPU = {1}", xPPU, yPPU));
    }

}
