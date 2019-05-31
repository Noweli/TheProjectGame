//Main play state class
//Contains and handles all stuff during gameplay

package com.sup.theprojectgame.screens;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.sup.theprojectgame.TheProjectGame;
import com.sup.theprojectgame.cameras.GameCamera;
import com.sup.theprojectgame.collision.WorldContactListener;
import com.sup.theprojectgame.map.MapController;
import com.sup.theprojectgame.map.WorldCreator;
import com.sup.theprojectgame.scenes.Hud;
import com.sup.theprojectgame.sprites.Cat;
import com.sup.theprojectgame.sprites.Enemy;
import com.sup.theprojectgame.sprites.Hedgehog;
import com.sup.theprojectgame.sprites.Player;

public class PlayScreen implements Screen {
	private TheProjectGame game;

	private GameCamera camera;
	private MapController map;
	private Hud hud;

	private World world;
	private Box2DDebugRenderer b2dr;

	//Collision system
	WorldContactListener worldContactListener;


	//Interactive sprites
	private Player player;
	
	private Array<Cat> cat;
	private Array<Hedgehog> hedgehog;
	
	private Hedgehog test;
	
	
	//Texture packs
	private TextureAtlas atlas;
	private TextureAtlas enemyAtlas;

	//Fixture array
	Array<Fixture> fixtures = new Array<Fixture>();

	public PlayScreen(TheProjectGame game) {
		this.game = game;

		camera = new GameCamera();
		hud = new Hud(game.batch);
		map = new MapController("map/Level_1_longer.tmx");
		worldContactListener = new WorldContactListener();

		world = new World(new Vector2(0, -13), true);
		b2dr = new Box2DDebugRenderer();

		world.setContactListener(worldContactListener);
		
		
		atlas = new TextureAtlas("sprites/player.atlas");
		enemyAtlas = new TextureAtlas("sprites/enemies.atlas");
		
		player = new Player(this);
		
		test = new Hedgehog(this, player.b2body.getPosition().x * TheProjectGame.PIXELSCALE + 2, player.b2body.getPosition().y * TheProjectGame.PIXELSCALE + 100);
		
		
		spawnMonster();

		new WorldCreator(this);
	}

	@Override
	public void show() {

	}

	public void handleInput(float dt) {
		player.updatePlayer(dt);
	}

	public void update(float dt) {
		handleInput(dt);
		
		test.update(dt);

		world.step(1 / 60f, 6, 2);
		camera.cameraUpdate(player.b2body.getPosition().x, player.b2body.getPosition().y);
		
		for(Enemy enemy : getEnemy()) {
            enemy.update(dt);
        }
		
		
		map.setRenderView(camera.getCamera());

		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			TheProjectGame.changeMusic("music/sombadi.mp3");
			game.setScreen(new MenuScreen(game));
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.K)) {
			
			try {
				setPointsSpawn(player.b2body.getPosition().x, player.b2body.getPosition().y);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
		test.draw(game.batch);
		
		for(Enemy e : getEnemy()) {
			e.draw(game.batch);
		}
		
		
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

	public Array<Fixture> getWorldFixtures(){
		world.getFixtures(fixtures);
		return fixtures;
	}
	
	public TextureAtlas getAtlas() {
		return atlas;
	}

	public TextureAtlas getEnemyAtlas() {
		return enemyAtlas;
	}

	public World getWorld() {
		return world;
	}

	public MapController getMap() {
		return map;
	}
	public void setPointsSpawn(Float x, Float y) throws IOException{
		
		String name = "cords.txt";
		File file = new File(name);
		file.createNewFile();
		
		PrintWriter out = null;
		try {
		    out = new PrintWriter(new BufferedWriter(new FileWriter(name, true)));
		    out.println(x + "," + y);
		}catch (IOException e) {
		    System.err.println(e);
		}finally{
		    if(out != null){
		        out.close();
		    }
		} 
	}
	
	public ArrayList<String> getPointsSpawn(){
		
		ArrayList<String> list = new ArrayList<String>();
		
		try{
			Scanner cs = new Scanner(new File("cords.txt"));
			while(cs.hasNextLine()){
				list.add(cs.nextLine());
			}
			cs.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		return list;
	}
	public void spawnMonster(){
		
		Random random = new Random();
		
		ArrayList<String> cords = getPointsSpawn();
		
		cat = new Array<Cat>();
		hedgehog = new Array<Hedgehog>();
		
		
		for (String string : cords) {
			
			String[] parts = string.split(",");
			
			Float x = Float.parseFloat(parts[0]);
			Float y = Float.parseFloat(parts[1]);

			if(random.nextInt(2) == 0) {
				
				cat.add(new Cat(this, x, y));
				
			}else {
				
				hedgehog.add(new Hedgehog(this, x, y));
				
			}	
			
		}
		
	}
	
	public Array<Enemy> getEnemy(){
		
		Array<Enemy> enemies = new Array<Enemy>();
        enemies.addAll(cat);
        enemies.addAll(hedgehog);
		return enemies;
		
		
	}
}
