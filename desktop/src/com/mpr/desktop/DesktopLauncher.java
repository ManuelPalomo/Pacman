package com.mpr.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mpr.PacmanGame;
import com.mpr.Tools.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height= Constants.HEIGHT;
		config.width=Constants.WIDTH;
		new LwjglApplication(new PacmanGame(), config);
	}
}
