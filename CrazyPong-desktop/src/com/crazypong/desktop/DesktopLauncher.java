package com.crazypong.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.crazypong.Settings;
import com.crazypong.crazypong;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Crazy Pong";
		config.width = Settings.windowWidth;
		config.height = Settings.windowHeight;
		new LwjglApplication(new crazypong(), config);
	}
}
