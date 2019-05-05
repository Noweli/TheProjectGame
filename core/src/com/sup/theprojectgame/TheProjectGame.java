package com.sup.theprojectgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sup.theprojectgame.screens.MenuScreen;
import com.sup.theprojectgame.screens.PlayScreen;
import com.sup.theprojectgame.screens.PreferencesScreen;


public class TheProjectGame extends Game {
	// FINALS
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 600;
	public static final float PIXELSCALE = 50;
	
	public final static int MENU = 0;
	public final static int PREFERENCES = 1;
	public final static int APPLICATION = 2;
	public final static int ENDGAME = 3;

	//Czemu?
	private MenuScreen menuScreen;
	private PlayScreen mainScreen;
	private PreferencesScreen preferencesScreen;

	public static Music music;
	
	
	public SpriteBatch batch;


	public static AssetManager manager;

	@Override
	public void create() {
		batch = new SpriteBatch();
		manager = new AssetManager();
		loadMusic();
		setScreen(new MenuScreen(this));
	}

	//Czemu to tu jest?
	public void changeScreen(int screen){
		switch(screen){
			case MENU:
				if(menuScreen == null) menuScreen = new MenuScreen(this);
				this.setScreen(menuScreen);
				break;
			case PREFERENCES:
				if(preferencesScreen == null) preferencesScreen = new PreferencesScreen(this);
				this.setScreen(preferencesScreen);
				break;
			case APPLICATION:
				if(mainScreen == null) mainScreen = new PlayScreen(this);
				changeMusic("music/calmAccusticMusic.mp3");
				this.setScreen(mainScreen);
				break;
		}
	}

	public void loadMusic(){
		manager.load("music/calmAccusticMusic.mp3", Music.class);
		manager.load("music/role.mp3", Music.class);
		manager.load("music/sombadi.mp3", Music.class);
		manager.finishLoading();

		music = TheProjectGame.manager.get("music/sombadi.mp3", Music.class);
		music.setLooping(true);
		music.play();
	}

	public static void changeMusic(String fileName){
		music.stop();
		music = TheProjectGame.manager.get(fileName, Music.class);
		music.setLooping(true);
		music.play();
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
