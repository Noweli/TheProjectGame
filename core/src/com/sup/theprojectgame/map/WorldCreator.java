//WorldCreator makes Ground objects to collide with player
//giving him possibility to move through the World

package com.sup.theprojectgame.map;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;

import com.badlogic.gdx.physics.box2d.World;
import com.sup.theprojectgame.screens.PlayScreen;
import com.sup.theprojectgame.sprites.Ground;
import com.sup.theprojectgame.sprites.Wall;

public class WorldCreator {

	public WorldCreator(PlayScreen screen) {
		World world = screen.getWorld();
		TiledMap map = screen.getMap().getMap();

		// Box2D for ground object
		for (MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new Ground(screen, rect);
		}

		for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new Wall(screen, rect);
		}
	}
}
