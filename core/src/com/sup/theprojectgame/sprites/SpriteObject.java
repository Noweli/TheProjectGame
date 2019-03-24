package com.sup.theprojectgame.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.sup.theprojectgame.TheProjectGame;

public abstract class SpriteObject {
	protected World world;
	protected TiledMap map;
	protected Rectangle bounds;
	protected Body body;

	public SpriteObject(World world, TiledMap map, Rectangle bounds) {
		this.world = world;
		this.map = map;
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
		body.createFixture(fdef);
	}

}
