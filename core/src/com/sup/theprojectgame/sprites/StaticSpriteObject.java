//Static sprites here
//Only objects that cannot move should inherit from it

package com.sup.theprojectgame.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.sup.theprojectgame.TheProjectGame;
import com.sup.theprojectgame.screens.PlayScreen;

public abstract class StaticSpriteObject {
	protected World world;
	protected TiledMap map;
	protected Rectangle bounds;
	protected Body body;
	protected Fixture fixture;

	public StaticSpriteObject(PlayScreen screen, Rectangle bounds) {
		this.world = screen.getWorld();
		this.map = screen.getMap().getMap();
		this.bounds = bounds;

		BodyDef bdef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		Body body;

		bdef.type = BodyDef.BodyType.StaticBody;
		bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / TheProjectGame.PIXELSCALE,
				(bounds.getY() + bounds.getHeight() / 2) / TheProjectGame.PIXELSCALE);

		body = world.createBody(bdef);

		shape.setAsBox((bounds.getWidth() / 2) / TheProjectGame.PIXELSCALE,
				(bounds.getHeight() / 2) / TheProjectGame.PIXELSCALE);
		fdef.shape = shape;
		fixture = body.createFixture(fdef);
	}

	public void setCategoryBit(short bit){
		Filter filter = new Filter();
		filter.categoryBits = bit;
		fixture.setUserData(filter);
	}
}
