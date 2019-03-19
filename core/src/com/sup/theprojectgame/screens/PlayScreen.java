//Main play state class
//Contains and handles all stuff during gameplay


package com.sup.theprojectgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.sup.theprojectgame.TheProjectGame;
import com.sup.theprojectgame.cameras.GameCamera;
import com.sup.theprojectgame.map.MapController;
import com.sup.theprojectgame.scenes.Hud;

public class PlayScreen implements Screen {
	private TheProjectGame game;
	
	private GameCamera camera;
	private MapController map;
	private Hud hud;
	

	public PlayScreen(TheProjectGame game) {
		this.game = game;
		
		camera = new GameCamera();
		hud = new Hud(game.batch);
		map = new MapController("level1.tmx");
	}

	@Override
	public void show() {

	}

	public void handleInput(float dt) {
		if(Gdx.input.isTouched())
			camera.getCamera().position.x += 100 * dt;
	}

	public void update(float dt) {
		handleInput(dt);

		camera.cameraUpdate();
		map.setRnderView(camera.getCamera());
	}

	@Override
	public void render(float delta) {
		update(delta);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		map.renderMap();

		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		camera.viewPortUpdate(width, height);
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
