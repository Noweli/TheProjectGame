package com.sup.theprojectgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sup.theprojectgame.TheProjectGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = TheProjectGame.GAME_WIDTH;
		config.height = TheProjectGame.GAME_HEIGHT;
		
		new LwjglApplication(new TheProjectGame(), config);
	}
}
