//Main character class
//Contains his shape, body and abilities

package com.sup.theprojectgame.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.sup.theprojectgame.TheProjectGame;
import com.sup.theprojectgame.screens.PlayScreen;

public class Player extends Sprite {

	private TextureRegion playerStandin;
	private Animation playerRun;
	private Animation playerJump;
	private boolean runningRight;
	private float stateTimer;

	public enum State { RUNNING, STANDING, JUMPING };
	public State currentState;
	public State previousState;

	public World world;
	public Body b2body;

	public Player(World world, PlayScreen playsc) {
		super(playsc.getAtlas().findRegion("Idle"));
		this.world = world;

		playerStandin = new TextureRegion(getTexture(), 0, 2, 32, 32);
		currentState = State.STANDING;
		previousState = State.STANDING;
		stateTimer = 0;
		runningRight = true;

		Array<TextureRegion> frames = new Array<TextureRegion>();
		for(int i = 3; i < 8; i++) {
			frames.add(new TextureRegion(getTexture(), i * 34, 2, 32, 32));
		}
		playerRun = new Animation(0.08f, frames);
		frames.clear();

		for(int i = 1; i < 3; i++) {
			frames.add(new TextureRegion(getTexture(), i*34, 2, 32, 32));
		}
		playerJump = new Animation(0.06f, frames);

		definePlayer();

		setBounds(0, 0, 32 / TheProjectGame.PIXELSCALE, 32 / TheProjectGame.PIXELSCALE);
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
		
		rec.setAsBox(getWidth() / 2 / TheProjectGame.PIXELSCALE / 2, getHeight() / TheProjectGame.PIXELSCALE / 2);
		shape.setRadius(5 / TheProjectGame.PIXELSCALE);
		fdef.shape = rec;
		b2body.createFixture(fdef);
	}

	public void movePlayer(float dt) {
		if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && !world.getContactList().isEmpty()
				&& b2body.getLinearVelocity().y <= 2f) {
			if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && b2body.getLinearVelocity().x == 0f)
				b2body.applyLinearImpulse(new Vector2(-3f, 5f), b2body.getWorldCenter(), true);
			else if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && b2body.getLinearVelocity().x == 0f)
				b2body.applyLinearImpulse(new Vector2(3f, 5f), b2body.getWorldCenter(), true);
			else
				b2body.applyLinearImpulse(new Vector2(0, 5.8f), b2body.getWorldCenter(), true);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && b2body.getLinearVelocity().x <= 2.5)
			b2body.applyLinearImpulse(new Vector2(0.1f, 0), b2body.getWorldCenter(), true);
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && b2body.getLinearVelocity().x >= -2.5)
			b2body.applyLinearImpulse(new Vector2(-0.1f, 0), b2body.getWorldCenter(), true);
		
		updateSprite(dt);
	}

	public void updateSprite(float dt) {
		setPosition(b2body.getPosition().x - getWidth() / 2, (b2body.getPosition().y - getHeight() / 2));
		setRegion(getFrame(dt));
	}

	public TextureRegion getFrame(float dt) {
		currentState = getState();

		TextureRegion region;
		switch (currentState) {
			case JUMPING:
				region = (TextureRegion) playerJump.getKeyFrame(stateTimer);
				break;
			case RUNNING:
				region = (TextureRegion) playerRun.getKeyFrame(stateTimer, true);
				break;
			case STANDING:
			default:
				region = playerStandin;
				break;
		}

		if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {
			region.flip(true, false);
			runningRight = false;
		}
		else if((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {
			region.flip(true, false);
			runningRight = true;
		}

		stateTimer = currentState == previousState ? stateTimer + dt : 0;
		previousState = currentState;

		return region;
	}

	public State getState() {
		if(b2body.getLinearVelocity().y > 0 || (b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING))
			return State.JUMPING;
		if(b2body.getLinearVelocity().x != 0)
			return State.RUNNING;
		else
			return State.STANDING;
	}
}
