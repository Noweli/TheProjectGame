package com.sup.theprojectgame.sprites;

import com.badlogic.gdx.math.Rectangle;
import com.sup.theprojectgame.collision.SpriteCollisionBits;
import com.sup.theprojectgame.screens.PlayScreen;

public class Wall extends StaticSpriteObject {

	public Wall(PlayScreen screen, Rectangle bounds) {
		super(screen, bounds);
		fixture.setUserData(this);
		setCategoryBit(SpriteCollisionBits.WALL_BIT);
	}
}
