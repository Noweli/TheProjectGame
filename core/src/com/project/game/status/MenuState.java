package com.project.game.status;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.project.game.TheProjectGame;

public class MenuState extends GameState {

	private Texture background;
	private Texture test;

	private Vector2 testPos;

	public MenuState() {
		camera.setToOrtho(false, TheProjectGame.GAME_WIDTH / 1.65f, TheProjectGame.GAME_HEIGHT / 1.7f);
		background = new Texture("gamePlatform.png");

		test = new Texture("test.png");
		testPos = new Vector2(-200, 0);
	}

	@Override
	public void handleInput() {

	}

	@Override
	public void update(float delta) {
		testPos.add(14, 0);
	}

	@Override
	public void render(SpriteBatch sprite) {
		sprite.setProjectionMatrix(camera.combined);
		sprite.begin();
		sprite.draw(background, 0, 0);
		sprite.draw(test, testPos.x, testPos.y);
		sprite.end();

	}

	@Override
	public void dispose() {
		background.dispose();

	}

}
