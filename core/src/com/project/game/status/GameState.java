package com.project.game.status;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameState {

	protected OrthographicCamera camera;
	protected StateManager status;

	public GameState() {
		camera = new OrthographicCamera();
		status = new StateManager();
	}

	public abstract void handleInput();

	public abstract void update(float delta);

	public abstract void render(SpriteBatch sprite);

	public abstract void dispose();

}
