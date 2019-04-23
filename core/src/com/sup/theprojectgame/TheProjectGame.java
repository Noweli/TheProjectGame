package com.sup.theprojectgame;

import com.badlogic.gdx.Game;
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

	private AppPreferences preferences;
	
	private PreferencesScreen preferencesScreen;
	private MenuScreen menuScreen;
	private PlayScreen mainScreen;
	
	
	public SpriteBatch batch;
	public AssetsManager assets = new AssetsManager();
	
	private Music playingSong;

	@Override
	public void create() {
		batch = new SpriteBatch();
		setScreen(new MenuScreen(this));
		preferences = new AppPreferences();
		
		assets.queueAddMusic();
		
		assets.manager.finishLoading();
		playingSong = assets.manager.get("music/Rolemusic_-_pl4y1ng.mp3");
		
		playingSong.setVolume(getPreferences().getMusicVolume());
		playingSong.play();
	}

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
				this.setScreen(mainScreen);
				break;
		}
	}
	
	public AppPreferences getPreferences(){
		return this.preferences;
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
