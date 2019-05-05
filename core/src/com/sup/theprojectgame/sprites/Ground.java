//Ground class for collision detection

package com.sup.theprojectgame.sprites;

import com.badlogic.gdx.math.Rectangle;
import com.sup.theprojectgame.collision.SpriteCollisionBits;
import com.sup.theprojectgame.screens.PlayScreen;

public class Ground extends StaticSpriteObject  {

	public Ground(PlayScreen screen, Rectangle bounds) {
		super(screen, bounds);
		fixture.setUserData(this);
		setCategoryBit(SpriteCollisionBits.GROUND_BIT);

	}
}
