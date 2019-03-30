//Main play state class
//Contains and handles all stuff during gameplay

package com.sup.theprojectgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.sup.theprojectgame.TheProjectGame;
import com.sup.theprojectgame.cameras.GameCamera;
import com.sup.theprojectgame.map.MapController;
import com.sup.theprojectgame.map.WorldCreator;
import com.sup.theprojectgame.scenes.Hud;
import com.sup.theprojectgame.sprites.Player;

public class PlayScreen implements Screen {
	private TheProjectGame game;

	private GameCamera camera;
	private MapController map;
	private Hud hud;

	private World world;
	private Box2DDebugRenderer b2dr;
	private Player player;
	
	//Texture packs
	private TextureAtlas atlas;

	public PlayScreen(TheProjectGame game) {
		this.game = game;

		camera = new GameCamera();
		hud = new Hud(game.batch);
		map = new MapController("map/Level_1 (kwadraty).tmx");

		world = new World(new Vector2(0, -13), true);
		b2dr = new Box2DDebugRenderer();
		
		
		atlas = new TextureAtlas("sprites/Placeholder.pack");
		
		player = new Player(world, this);
		new WorldCreator(world, map.getMap());

	}

	@Override
	public void show() {

	}

	public void handleInput(float dt) {
		player.movePlayer(dt);
	}

	public void update(float dt) {
		handleInput(dt);

		world.step(1 / 60f, 6, 2);
		camera.cameraUpdate(player.b2body.getPosition().x, player.b2body.getPosition().y);
		map.setRenderView(camera.getCamera());
		
	}

	@Override
	public void render(float delta) {
		update(delta);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		map.renderMap();

		b2dr.render(world, camera.getCamera().combined);
		
		game.batch.setProjectionMatrix(camera.getCamera().combined);
		game.batch.begin();
		player.draw(game.batch);
		game.batch.end();

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
		map.dispose();
		world.dispose();
		b2dr.dispose();
	}
	
	public TextureAtlas getAtlas() {
		return atlas;
	}

}
