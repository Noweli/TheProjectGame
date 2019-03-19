package com.sup.theprojectgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sup.theprojectgame.screens.PlayScreen;

public class TheProjectGame extends Game {
	//FINALS
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 480;
	
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
