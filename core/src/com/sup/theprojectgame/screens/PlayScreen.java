package com.sup.theprojectgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sup.theprojectgame.TheProjectGame;
import com.sup.theprojectgame.scenes.Hud;

public class PlayScreen implements Screen {
	private TheProjectGame game;
	private OrthographicCamera camera;
	private Viewport viewPort;

	private TmxMapLoader mapLoader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;

	private Hud hud;

	public PlayScreen(TheProjectGame game) {
		this.game = game;
		//create cam used to follow character through cam world
		camera = new OrthographicCamera();

		viewPort = new FitViewport(TheProjectGame.GAME_WIDTH, TheProjectGame.GAME_HEIGHT, camera);

		hud = new Hud(game.batch);

		//loading map to the screen
		mapLoader = new TmxMapLoader();
		map = mapLoader.load("test.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);

		camera.position.set(viewPort.getWorldWidth() / 2, viewPort.getWorldHeight() / 2, 0);
	}

	@Override
	public void show() {

	}

	public void handleInput(float dt) {
		if(Gdx.input.isTouched())
			camera.position.x += 100 * dt;
	}

	public void update(float dt) {
		handleInput(dt);

		camera.update();
		renderer.setView(camera);
	}

	@Override
	public void render(float delta) {
		update(delta);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		renderer.render();

		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();
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
