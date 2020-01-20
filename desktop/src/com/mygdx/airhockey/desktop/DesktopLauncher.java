package com.mygdx.airhockey.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.airhockey.backend.Config;
import com.mygdx.airhockey.screens.MyGame;

public class DesktopLauncher {
    /**
     * Main method for launching the game.
     * @param args for the launch.
     */
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL", "true");
        config.samples = 1;
        config.title = "AIR HOCKEY";
        config.useGL30 = true;
        config.height = (int) Config.getInstance().resolution;
        config.width = (int) Config.getInstance().resolution;
        new LwjglApplication(new MyGame(), config);
    }
}
