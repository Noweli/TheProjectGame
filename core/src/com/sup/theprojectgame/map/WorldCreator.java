package com.sup.theprojectgame.map;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;

import com.badlogic.gdx.physics.box2d.World;
import com.sup.theprojectgame.sprites.Ground;

public class WorldCreator {

	public WorldCreator(World world, TiledMap map) {

		// Box2D for ground object
		for (MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new Ground(world, map, rect);
		}
	}

}
