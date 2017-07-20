package com.obstaclecourse.util.debug;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.text.MessageFormat;

/**
 * Created by platon on 08/07/2017.
 */
public class DebugCameraController {

    private Vector2 position = new Vector2();
    private Vector2 startPosition = new Vector2();
    private float zoom = 1.0f;

    public DebugCameraConfig config;

    public DebugCameraController() {
        config = new DebugCameraConfig();
    }

    public void setStartPosition(float x, float y) {
        startPosition.set(x, y);
        setPosition(x, y);
    }


    public void applyTo(OrthographicCamera camera) {
        camera.zoom = zoom;
        camera.position.set(position, 0);
        camera.update();
    }

    public void handleDebugInput(float delta) {
        if (Gdx.app.getType() != Application.ApplicationType.Desktop) {
            return;
        }

        float moveSpeed = DebugCameraConfig.DEFAULT_MOVE_SPEED * delta;
        float zoomSpeed = DebugCameraConfig.DEFAULT_ZOOM_SPEED * delta;

        if (Gdx.input.isKeyPressed(config.getLeftKey())) {
            moveLeft(moveSpeed);
        }
        if (Gdx.input.isKeyPressed(config.getRightKey())) {
            moveRight(moveSpeed);
        }
        if (Gdx.input.isKeyPressed(config.getUpKey())) {
            moveUp(moveSpeed);
        }
        if (Gdx.input.isKeyPressed(config.getDownKey())) {
            moveDown(moveSpeed);
        }
        if (Gdx.input.isKeyPressed(config.getZoomInKey())) {
            zoomIn(zoomSpeed);
        }
        if (Gdx.input.isKeyPressed(config.getZoomOutKey())) {
            zoomOut(zoomSpeed);
        }
        if (Gdx.input.isKeyPressed(config.getLogKey())) {
            printLog();
        }
        if (Gdx.input.isKeyPressed(config.getResetKey())) {
            reset();
        }
    }


    private void setPosition(float x, float y) {
        position.set(x, y);
    }

    private void moveCamera(float xSpeed, float ySpeed) {
        setPosition(position.x + xSpeed, position.y + ySpeed);
    }

    private void moveLeft(float speed) {
        moveCamera(-speed, 0);
    }

    private void moveRight(float speed) {
        moveCamera(speed, 0);
    }

    private void moveUp(float speed) {
        moveCamera(0, speed);
    }

    private void moveDown(float speed) {
        moveCamera(0, -speed);
    }

    private void setZoom(float value) {
        zoom = MathUtils.clamp(value, config.getMaxZoomIn(), config.getMaxZoomOut());
    }

    private void zoomIn(float speed) {
        setZoom(zoom + speed);
    }

    private void zoomOut(float speed) {
        setZoom(zoom - speed);
    }

    private void printLog() {
        DebugCameraConfig.LOG.debug(MessageFormat.format("Position={0}, zoom={1}", position, zoom));
    }

    private void reset() {
        position.set(startPosition);
        zoom = 1f;
    }
}
