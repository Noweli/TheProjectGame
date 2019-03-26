//Main character class
//Contains his shape, body and abilities

package com.sup.theprojectgame.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.sup.theprojectgame.TheProjectGame;
import com.sup.theprojectgame.screens.PlayScreen;

public class Player extends Sprite {

	private TextureRegion playerStandin;

	public World world;
	public Body b2body;

	public Player(World world, PlayScreen playsc) {
		super(playsc.getAtlas().findRegion("player_attack"));
		this.world = world;
		definePlayer();

		playerStandin = new TextureRegion(getTexture(), 37, 0, 16, 32);
		setBounds(0, 0, 16 / TheProjectGame.PIXELSCALE, 32 / TheProjectGame.PIXELSCALE);
		setRegion(playerStandin);
	}

	public void definePlayer() {
		BodyDef bdef = new BodyDef();
		bdef.position.set(336 / TheProjectGame.PIXELSCALE, 214.34f / TheProjectGame.PIXELSCALE);
		bdef.type = BodyDef.BodyType.DynamicBody;

		b2body = world.createBody(bdef);

		FixtureDef fdef = new FixtureDef();
		PolygonShape rec = new PolygonShape();
		CircleShape shape = new CircleShape();
		
		rec.setAsBox(getWidth() / TheProjectGame.PIXELSCALE / 2, getHeight() / TheProjectGame.PIXELSCALE / 2);
		shape.setRadius(5 / TheProjectGame.PIXELSCALE);
		fdef.shape = rec;
		b2body.createFixture(fdef);
	}

	public void movePlayer(float dt) {
		if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && !world.getContactList().isEmpty()
				&& b2body.getLinearVelocity().y <= 2f)
			b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && b2body.getLinearVelocity().x <= 2.5)
			b2body.applyLinearImpulse(new Vector2(0.1f, 0), b2body.getWorldCenter(), true);
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && b2body.getLinearVelocity().x >= -2.5)
			b2body.applyLinearImpulse(new Vector2(-0.1f, 0), b2body.getWorldCenter(), true);
		
		updateSprite(dt);
	}
	
	public void updateSprite(float dt) {
		setPosition(b2body.getPosition().x - getWidth() / 2, (b2body.getPosition().y - getHeight() / 2));
	}
}
