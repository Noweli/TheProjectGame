package com.project.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.project.game.status.MenuState;
import com.project.game.status.StateManager;

public class TheProjectGame extends ApplicationAdapter {
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 600;
	
	private StateManager state;
	
	SpriteBatch batch;
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		state = new StateManager();
		state.push( new MenuState() );
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		state.update(Gdx.graphics.getDeltaTime());
		state.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
