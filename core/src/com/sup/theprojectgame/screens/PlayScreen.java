package com.sup.theprojectgame.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sup.theprojectgame.TheProjectGame;

public class PlayScreen implements Screen {
	private TheProjectGame game;
	private OrthographicCamera camera;
	private Viewport viewPort;

	public PlayScreen() {
		game = new TheProjectGame();
		camera = new OrthographicCamera();
		viewPort = new FitViewport(TheProjectGame.GAME_WIDTH, TheProjectGame.GAME_HEIGHT, camera);
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}

}
