package com.sup.theprojectgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.sup.theprojectgame.TheProjectGame;

public class PreferencesScreen implements Screen {

	private TheProjectGame parent;
	private Stage stage;
	private Label titleLabel;
	private Label volumeMusicLabel;
	private Label volumeSoundLabel;
	private Texture background;

	public PreferencesScreen(TheProjectGame parent) {
		this.parent = parent;
		stage = new Stage(new ScreenViewport());
		background = new Texture("menu/background.png");

	}

	@Override
	public void show() {
		stage.clear();
		Gdx.input.setInputProcessor(stage);

		Table table = new Table();
		table.setFillParent(true);
		table.setDebug(false);
		stage.addActor(table);

		Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

		final Slider volumeMusicSlider = new Slider(0f, 1f, 0.1f, false, skin);
		volumeMusicSlider.setValue(TheProjectGame.music.getVolume());
		volumeMusicSlider.addListener(new EventListener() {
			@Override
			public boolean handle(Event event) {
				TheProjectGame.music.setVolume((volumeMusicSlider.getValue()));
				return false;
			}
		});

		final Slider soundMusicSlider = new Slider(0f, 1f, 0.1f, false, skin);
		soundMusicSlider.setValue(TheProjectGame.music.getVolume());
		soundMusicSlider.addListener(new EventListener() {
			@Override
			public boolean handle(Event event) {
				TheProjectGame.music.setVolume((volumeMusicSlider.getValue()));
				return false;
			}
		});

		final TextButton backButton = new TextButton("Back", skin, "small");
		backButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				parent.changeScreen(TheProjectGame.MENU);
				TheProjectGame.changeMusic("music/sombadi.mp3");
			}
		});

		titleLabel = new Label("Preferences", skin);
		volumeMusicLabel = new Label("Music Volume", skin);
		volumeSoundLabel = new Label("Sound Volume", skin);

		table.add(titleLabel).colspan(2);
		table.row().pad(10, 0, 0, 10);
		table.add(volumeMusicLabel).left();
		table.add(volumeMusicSlider);
		table.row().pad(10, 0, 0, 10);
		table.row().pad(10, 0, 0, 10);
		table.add(volumeSoundLabel).left();
		table.add(soundMusicSlider);
		table.row().pad(10, 0, 0, 10);
		;
		table.row().pad(10, 0, 0, 10);
		table.add(backButton).colspan(2);

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
		// TODO Auto-generated method stub

	}

}
