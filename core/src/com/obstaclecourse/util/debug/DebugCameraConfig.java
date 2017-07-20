package com.obstaclecourse.util.debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Logger;

/**
 * Created by platon on 09/07/2017.
 */
public class DebugCameraConfig {

    static final Logger LOG = new Logger(DebugCameraController.class.getName(), Logger.DEBUG);
    static final String CONFIG_FILE_PATH = "debug/debugCameraConfig.json";

    static final int DEFAULT_LEFT_KEY = Input.Keys.A;
    static final int DEFAULT_RIGHT_KEY = Input.Keys.D;
    static final int DEFAULT_UP_KEY = Input.Keys.S;
    static final int DEFAULT_DOWN_KEY = Input.Keys.W;
    static final int DEFAULT_ZOOM_IN_KEY = Input.Keys.COMMA ;
    static final int DEFAULT_ZOOM_OUT_KEY = Input.Keys.PERIOD ;
    static final int DEFAULT_RESET_KEY = Input.Keys.BACKSPACE ;
    static final int DEFAULT_LOG_KEY = Input.Keys.ENTER;
    static final float DEFAULT_MOVE_SPEED = 20.0f;
    static final float DEFAULT_ZOOM_SPEED = 2.0f;
    static final float MAX_ZOOM_IN = 0.20f;
    static final float MAX_ZOOM_OUT = 30f;

    private float maxZoomIn;
    private float maxZoomOut;
    private float moveSpeed;
    private float zoomSpeed;

    private int upKey;


    private int downKey;
    private int leftKey;
    private int rightKey;

    private int zoomInKey;
    private int zoomOutKey;
    private int resetKey;
    private int logKey;

    private FileHandle fileHandle;

    public DebugCameraConfig() {
        init();
    }

    private void init() {
        fileHandle = Gdx.files.internal(CONFIG_FILE_PATH);

        if (fileHandle.exists()) {
            load();
        } else {
            LOG.debug("Config file not found, using defaults");
            setupDefaults();
        }
    }

    private void load() {
        JsonValue root = new JsonReader().parse(fileHandle);
        maxZoomIn = root.getFloat("maxZoomIn");
        maxZoomOut = root.getFloat("maxZoomOut");

        moveSpeed = root.getFloat("moveSpeed");
        zoomSpeed = root.getFloat("zoomSpeed");

        upKey = getInputKeyValue(root, "upKey", DEFAULT_UP_KEY);
        downKey = getInputKeyValue(root, "downKey", DEFAULT_DOWN_KEY);
        leftKey = getInputKeyValue(root, "leftKey", DEFAULT_LEFT_KEY);
        rightKey = getInputKeyValue(root, "rightKey", DEFAULT_RIGHT_KEY);

        zoomInKey = getInputKeyValue(root,"zoomInKey",DEFAULT_ZOOM_IN_KEY);
        zoomOutKey = getInputKeyValue(root, "zoomOutKey", DEFAULT_ZOOM_OUT_KEY);
        resetKey = getInputKeyValue(root, "resetKey", DEFAULT_RESET_KEY);
        logKey = getInputKeyValue(root, "logKey", DEFAULT_LOG_KEY);
    }

    private void setupDefaults() {
        maxZoomIn = MAX_ZOOM_IN;
        maxZoomOut = MAX_ZOOM_OUT;
        moveSpeed = DEFAULT_MOVE_SPEED;
        zoomSpeed = DEFAULT_ZOOM_SPEED;

        upKey = DEFAULT_UP_KEY;
        downKey = DEFAULT_DOWN_KEY;
        leftKey = DEFAULT_LEFT_KEY;
        rightKey = DEFAULT_RIGHT_KEY;

        zoomInKey = DEFAULT_ZOOM_IN_KEY;
        zoomOutKey = DEFAULT_ZOOM_OUT_KEY;
        resetKey = DEFAULT_RESET_KEY;
        logKey = DEFAULT_LOG_KEY;
    }

    public float getMaxZoomIn() {
        return maxZoomIn;
    }

    public float getMaxZoomOut() {
        return maxZoomOut;
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public float getZoomSpeed() {
        return zoomSpeed;
    }

    public int getUpKey() {
        return upKey;
    }

    public int getDownKey() {
        return downKey;
    }

    public int getLeftKey() {
        return leftKey;
    }

    public int getRightKey() {
        return rightKey;
    }

    public int getZoomInKey() {
        return zoomInKey;
    }

    public int getZoomOutKey() {
        return zoomOutKey;
    }

    public int getResetKey() {
        return resetKey;
    }

    public int getLogKey() {
        return logKey;
    }

    public static int getInputKeyValue(JsonValue value, String name, int defaultInputKey) {
        String keyString = value.getString(name, Input.Keys.toString(defaultInputKey));
        return Input.Keys.valueOf(keyString);
    }
}
