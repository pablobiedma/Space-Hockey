package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGame;
//import com.mygdx.game.screens.GameScreen;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Air Hockey";
        config.useGL30 = true;
        config.height = 900;
        config.width = 1366;
		new LwjglApplication(new MyGame(), config);
	}
}
