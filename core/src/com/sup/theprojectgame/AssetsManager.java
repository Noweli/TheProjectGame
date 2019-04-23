package com.sup.theprojectgame;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.assets.loaders.SkinLoader.SkinParameter;

public class AssetsManager {
	
	public final AssetManager manager = new AssetManager();

	// Sounds
	
	// Music
	public final String playingSong = "music/Rolemusic_-_pl4y1ng.mp3";
	
	// Skin
	public final String skin = "skin/glassy-ui.json";
	
	// Textures
	
	
	public void queueAddFonts(){
		
	}
	
	public void queueAddParticleEffects(){
		
	}
	
	public void queueAddImages(){
		
	}
	
	
	public void queueAddSkin(){
		SkinParameter params = new SkinParameter("skin/glassy-ui.atlas");
		manager.load(skin, Skin.class, params);
		
	}
	
	public void queueAddMusic(){
		manager.load(playingSong, Music.class);
	}
	
	public void queueAddSounds(){

	}
	
	
}
