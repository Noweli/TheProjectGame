package com.sup.theprojectgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sup.theprojectgame.screens.MainMenuScreen;

public class TheProjectGame extends Game {
	// FINALS
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 480;
	public static final float PIXELSCALE = 50;

	public SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();
		setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}
