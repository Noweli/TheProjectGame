package com.sup.theprojectgame.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.sup.theprojectgame.TheProjectGame;
import com.sup.theprojectgame.collision.SpriteCollisionBits;
import com.sup.theprojectgame.screens.PlayScreen;

public class Hedgehog extends Enemy {

    private float stateTime;
    private Animation walkAnimation;
    private Array<TextureRegion> frames;

    public Hedgehog(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        setRegion(screen.getEnemyAtlas().findRegion("jez"));

        frames = new Array<TextureRegion>();

        for(int i = 0 ; i < 2; i++)
            frames.add(new TextureRegion(getTexture(),i * 36, 2, 32, 32));
        walkAnimation = new Animation(0.4f, frames);
        stateTime = 0;

        defineEnemy();

        setBounds(getX(), getY(), 32 / TheProjectGame.PIXELSCALE / 2, 32 / TheProjectGame.PIXELSCALE / 2);
    }

    @Override
    protected void defineEnemy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(336 / TheProjectGame.PIXELSCALE, 214.34f / TheProjectGame.PIXELSCALE);
        bdef.type = BodyDef.BodyType.DynamicBody;

        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape rec = new PolygonShape();
        rec.setAsBox(8 / TheProjectGame.PIXELSCALE, 8 / TheProjectGame.PIXELSCALE);

        fdef.filter.categoryBits = SpriteCollisionBits.ENEMY_BIT;
        fdef.filter.maskBits = SpriteCollisionBits.GROUND_BIT | SpriteCollisionBits.WALL_BIT | SpriteCollisionBits.PLAYER_BIT;

        fdef.shape = rec;
        b2body.createFixture(fdef);
    }

    public void update(float dt){
        stateTime += dt;
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion((TextureRegion)walkAnimation.getKeyFrame(stateTime, true));
    }
}
