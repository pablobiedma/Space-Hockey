package com.mygdx.airhockey.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.airhockey.screens.MyGame;

public class DesktopLauncher {
    /**
     * Main method for launching the game.
     * @param args for the launch.
     */
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Air Hockey";
        config.useGL30 = true;
        config.height = 1000;
        config.width = 1000;
        new LwjglApplication(new MyGame(), config);
    }
}
