package com.sup.theprojectgame.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.sup.theprojectgame.TheProjectGame;
import com.sup.theprojectgame.collision.SpriteCollisionBits;
import com.sup.theprojectgame.screens.PlayScreen;

public abstract class Enemy extends Sprite {
    protected World world;
    protected PlayScreen screen;

    public Body b2body;

    public Enemy(PlayScreen screen, float x, float y){
        this.world = screen.getWorld();
        this.screen = screen;
    }

    public void moveEnemy(float speedX){
        float playerPosX = 0;
        if(getPlayerBody() != null)
            playerPosX = getPlayerBody().getPosition().x;
        if(playerPosX !=0 ) {
            if(playerPosX > b2body.getPosition().x)
                b2body.applyLinearImpulse(new Vector2(speedX,0), b2body.getWorldCenter(), true);
            else if(playerPosX < b2body.getPosition().x) {
                b2body.applyLinearImpulse(new Vector2(-speedX, 0), b2body.getWorldCenter(), true);
            }
        }

        if(b2body.getLinearVelocity().x < 0.08f && b2body.getLinearVelocity().y == 0 && getPlayerBody().getLinearVelocity().y < 0.1f)
            b2body.applyLinearImpulse(new Vector2(0, 6.1f), b2body.getWorldCenter(), true);
    }

    private Body getPlayerBody(){
        for(Fixture fix : screen.getWorldFixtures())
            if(fix.getUserData() == "player")
                return fix.getBody();
            return null;
    }


    protected void defineEnemy(float boxX, float boxY, float restitution, float bdefPosX, float bdefPosY){
        BodyDef bdef = new BodyDef();
        bdef.position.set(bdefPosX / TheProjectGame.PIXELSCALE, bdefPosY / TheProjectGame.PIXELSCALE);
        bdef.type = BodyDef.BodyType.DynamicBody;

        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape rec = new PolygonShape();
        rec.setAsBox(boxX / TheProjectGame.PIXELSCALE, boxY / TheProjectGame.PIXELSCALE);

        fdef.filter.categoryBits = SpriteCollisionBits.ENEMY_BIT;
        fdef.filter.maskBits = SpriteCollisionBits.GROUND_BIT | SpriteCollisionBits.WALL_BIT | SpriteCollisionBits.PLAYER_BIT;

        fdef.shape = rec;
        fdef.restitution = restitution;
        b2body.createFixture(fdef);
    }
}
