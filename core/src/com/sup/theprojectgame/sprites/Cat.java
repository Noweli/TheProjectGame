package com.sup.theprojectgame.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.sup.theprojectgame.TheProjectGame;
import com.sup.theprojectgame.screens.PlayScreen;

public class Cat extends Enemy {

    private float stateTime;
    private Animation walkAnimation;
    private Array<TextureRegion> frames;

    public Cat(PlayScreen screen, float x, float y) {
        super(screen, x, y);

        setRegion(screen.getEnemyAtlas().findRegion("kitku"));

        frames = new Array<TextureRegion>();

        for(int i = 0 ; i <= 34; i+=34)
            frames.add(new TextureRegion(getTexture(), i + 70, 2, 32, 32));

        walkAnimation = new Animation(0.4f, frames);
        stateTime = 0;

        defineEnemy(8, 8, 0.5f, 336, 214.34f);

        setBounds(getX(), getY(), 32 / TheProjectGame.PIXELSCALE / 2, 32 / TheProjectGame.PIXELSCALE / 2);
    }

    public void update(float dt){
        stateTime += dt;
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion((TextureRegion)walkAnimation.getKeyFrame(stateTime, true));
        if(b2body.getLinearVelocity().x > 0)
            flip(true, false);
        moveEnemy(0.05f);
    }

}
