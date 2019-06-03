package com.sup.theprojectgame.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.sup.theprojectgame.TheProjectGame;
import com.sup.theprojectgame.screens.PlayScreen;

public class Snake extends Enemy {
	
	private float stateTime;
    private Animation<TextureRegion> walkAnimation;
    private Array<TextureRegion> frames;

	public Snake(PlayScreen screen, float x, float y) {
		super(screen, x, y);
		setRegion(screen.getEnemyAtlas2().findRegion("snake1"));
		
		frames = new Array<TextureRegion>();

        
        setPosition(x, y);
        for(int i = 0 ; i <= 34; i+=34)
            frames.add(new TextureRegion(getTexture(), i + 69, 2, 32, 32));

        walkAnimation = new Animation<TextureRegion>(0.3f, frames);
        stateTime = 0;

        defineEnemy(8,8,0.5f,x,y);
        
        setBounds(getX(), getY(), 32 / TheProjectGame.PIXELSCALE / 2, 32 / TheProjectGame.PIXELSCALE / 2);
	}

	@Override
	public void update(float dt) {
		stateTime += dt;
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion((TextureRegion)walkAnimation.getKeyFrame(stateTime, true));
        if(b2body.getLinearVelocity().x < 0)
            flip(true, false);
        moveEnemy(0.05f);
	}

}
