package com.sup.theprojectgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.sup.theprojectgame.TheProjectGame;

public class MainMenuScreen implements Screen {

	private static final int PLAY_BUTTON_WIDTH = 340;
	private static final int PLAY_BUTTON_HEIGHT = 80;
	private static final int SETTINGS_BUTTON_WIDTH = 340;
	private static final int SETTINGS_BUTTON_HEIGHT = 80;
	private static final int EXIT_BUTTON_WIDTH = 340;
	private static final int EXIT_BUTTON_HEIGHT = 80;

	private static final int PLAY_BUTTON_Y = 300;
	private static final int SETTINGS_BUTTON_Y = 200;
	private static final int EXIT_BUTTON_Y = 100;

	TheProjectGame game;

	// Background Texture
	Texture background;

	// Buttons Textures
	Texture playButtonActive;
	Texture playButtonInactive;
	Texture settingsButtonActive;
	Texture settingsButtonInactive;
	Texture exitButtonActive;
	Texture exitButtonInactive;

	public MainMenuScreen(TheProjectGame game) {

		this.game = game;

		background = new Texture("menu/background.png");

		playButtonActive = new Texture("menu/startinactive.png");
		playButtonInactive = new Texture("menu/startactive.png");
		settingsButtonActive = new Texture("menu/settingsinactive.png");
		settingsButtonInactive = new Texture("menu/settingsactive.png");
		exitButtonActive = new Texture("menu/exitinactive.png");
		exitButtonInactive = new Texture("menu/exitactive.png");

	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.begin();

		game.batch.draw(background, 0, 0, 800, 600);

		int x;

		// PLAY_BUTTON
		x = TheProjectGame.GAME_WIDTH / 2 - PLAY_BUTTON_WIDTH / 2;
		if (Gdx.input.getX() < x + PLAY_BUTTON_WIDTH && Gdx.input.getX() > x
				&& TheProjectGame.GAME_HEIGHT - Gdx.input.getY() < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT
				&& TheProjectGame.GAME_HEIGHT - Gdx.input.getY() > PLAY_BUTTON_Y) {

			if (Gdx.input.isTouched()) {

				this.dispose();

				game.setScreen(new PlayScreen(game));

			}

			game.batch.draw(playButtonActive, (TheProjectGame.GAME_WIDTH - PLAY_BUTTON_WIDTH) / 2, PLAY_BUTTON_Y,
					PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);

		} else {

			game.batch.draw(playButtonInactive, (TheProjectGame.GAME_WIDTH - PLAY_BUTTON_WIDTH) / 2, PLAY_BUTTON_Y,
					PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
		}

		// SETTINGS_BUTTON
		x = TheProjectGame.GAME_WIDTH / 2 - SETTINGS_BUTTON_WIDTH / 2;
		if (Gdx.input.getX() < x + SETTINGS_BUTTON_WIDTH && Gdx.input.getX() > x
				&& TheProjectGame.GAME_HEIGHT - Gdx.input.getY() < SETTINGS_BUTTON_Y + SETTINGS_BUTTON_HEIGHT
				&& TheProjectGame.GAME_HEIGHT - Gdx.input.getY() > SETTINGS_BUTTON_Y) {

			game.batch.draw(settingsButtonActive, (TheProjectGame.GAME_WIDTH - SETTINGS_BUTTON_WIDTH) / 2,
					SETTINGS_BUTTON_Y, SETTINGS_BUTTON_WIDTH, SETTINGS_BUTTON_HEIGHT);

		} else {

			game.batch.draw(settingsButtonInactive, (TheProjectGame.GAME_WIDTH - SETTINGS_BUTTON_WIDTH) / 2,
					SETTINGS_BUTTON_Y, SETTINGS_BUTTON_WIDTH, SETTINGS_BUTTON_HEIGHT);
		}

		// EXIT_BUTTON
		x = TheProjectGame.GAME_WIDTH / 2 - EXIT_BUTTON_WIDTH / 2;
		if (Gdx.input.getX() < x + EXIT_BUTTON_WIDTH && Gdx.input.getX() > x
				&& TheProjectGame.GAME_HEIGHT - Gdx.input.getY() < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT
				&& TheProjectGame.GAME_HEIGHT - Gdx.input.getY() > EXIT_BUTTON_Y) {

			if (Gdx.input.isTouched()) {

				Gdx.app.exit();

			}

			game.batch.draw(exitButtonActive, (TheProjectGame.GAME_WIDTH - EXIT_BUTTON_WIDTH) / 2, EXIT_BUTTON_Y,
					EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);

		} else {

			game.batch.draw(exitButtonInactive, (TheProjectGame.GAME_WIDTH - EXIT_BUTTON_WIDTH) / 2, EXIT_BUTTON_Y,
					EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
		}

		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {

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
