//Main character class
//Contains his shape, body and abilities

package com.sup.theprojectgame.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.sup.theprojectgame.TheProjectGame;

public class Player extends Sprite {
	private float speedScale = 1.6f;

	public World world;
	public Body b2body;

	public Player(World world) {
		this.world = world;
		definePlayer();
	}

	public void definePlayer() {
		BodyDef bdef = new BodyDef();
		bdef.position.set(336 / TheProjectGame.PIXELSCALE, 214.34f / TheProjectGame.PIXELSCALE);
		bdef.type = BodyDef.BodyType.DynamicBody;

		b2body = world.createBody(bdef);

		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(5 / TheProjectGame.PIXELSCALE);
		fdef.shape = shape;
		b2body.createFixture(fdef);
	}

	public void movePlayer(float dt) {
		if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && !world.getContactList().isEmpty()) 
			b2body.applyLinearImpulse(new Vector2(0, 4f * speedScale), b2body.getWorldCenter(), true);			
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && b2body.getLinearVelocity().x <= 1)
			b2body.applyLinearImpulse(new Vector2(speedScale, 0), b2body.getWorldCenter(), true);
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && b2body.getLinearVelocity().x >= -1)
			b2body.applyLinearImpulse(new Vector2(-speedScale, 0), b2body.getWorldCenter(), true);
	}
}
