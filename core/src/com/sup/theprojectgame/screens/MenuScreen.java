package com.sup.theprojectgame.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.sup.theprojectgame.TheProjectGame;

public class MenuScreen implements Screen{
	
	private TheProjectGame parent;
	private Stage stage;
	private Skin skin;
	private Texture background;

	public final AssetManager manager = new AssetManager();

	public MenuScreen(TheProjectGame parent){
		this.parent = parent;

		stage = new Stage(new ScreenViewport());

		queueAddSkin();
		manager.finishLoading();
		skin = manager.get("skin/glassy-ui.json");
		background = new Texture("menu/background.png");
	}

	public void queueAddSkin(){
		SkinLoader.SkinParameter params = new SkinLoader.SkinParameter("skin/glassy-ui.atlas");
		manager.load("skin/glassy-ui.json", Skin.class, params);

	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage); 
		
		Table table = new Table();
		table.setFillParent(true);
        table.setDebug(false);
        stage.addActor(table);
        
        
        //create buttons
        TextButton newGame = new TextButton("New Game", skin);
        TextButton preferences = new TextButton("Preferences", skin);
        TextButton exit = new TextButton("Exit", skin);
        
        //add buttons to table
        table.add(newGame).fillX().uniformX();
		table.row().pad(10, 0, 10, 0);
		table.add(preferences).fillX().uniformX();
		table.row();
		table.add(exit).fillX().uniformX();
		
		// create button listeners
		exit.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Gdx.app.exit();				
			}
		});
		
		newGame.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				parent.changeScreen(TheProjectGame.APPLICATION);			
			}
		});
		
		preferences.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				parent.changeScreen(TheProjectGame.PREFERENCES);					
			}
		});
		
	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		
		stage.getBatch().begin();
		stage.getBatch().draw(background, 0, 0, TheProjectGame.GAME_WIDTH, TheProjectGame.GAME_HEIGHT);
		stage.getBatch().end();
		
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// dispose of assets when not needed anymore
		stage.dispose();
	}

}
