package com.sup.theprojectgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sup.theprojectgame.TheProjectGame;

public class PlayScreen implements Screen {
	private TheProjectGame game;
	private OrthographicCamera camera;
	private Viewport viewPort;
	
	//TEST
	private Texture test;

	public PlayScreen(TheProjectGame game) {
		test = new Texture("badlogic.jpg");
		
		this.game = game;
		camera = new OrthographicCamera();
		viewPort = new FitViewport(TheProjectGame.GAME_WIDTH, TheProjectGame.GAME_HEIGHT, camera);
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.setProjectionMatrix(camera.combined);
		
		game.batch.begin();
		game.batch.draw(test, 0, 0);
		game.batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		viewPort.update(width, height);
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
