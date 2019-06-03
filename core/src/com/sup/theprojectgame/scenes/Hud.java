//Game main HUD
//Should contain information about HP, Shield, Ammo and reserve ammo
//Also below should be level name


package com.sup.theprojectgame.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sup.theprojectgame.TheProjectGame;


public class Hud {
    public Stage stage;
    private Viewport viewport;

    private static Integer healthPoints;
    private static Integer score;

    static Label healthPointsLabel;
    static Label scoreLabel;
    Label healthName;
    Label scoreName;

    public Hud(SpriteBatch sb) {
        healthPoints = 5;
        score = 0;

        viewport = new FitViewport(TheProjectGame.GAME_WIDTH / 2, TheProjectGame.GAME_WIDTH / 2, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        healthPointsLabel = new Label(String.format("%01d", healthPoints), new Label.LabelStyle(new BitmapFont(), Color.RED));
        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        healthName = new Label("HEALTH POINTS", new Label.LabelStyle(new BitmapFont(), Color.RED));
        scoreName = new Label("SCORE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(healthName).expandX().padTop(10);
        table.add(scoreName).expandX().padTop(10);
        table.row();
        table.add(healthPointsLabel).expandX().padTop(10);
        table.add(scoreLabel).expandX().padTop(10);

        stage.addActor(table);
    }

    public static void addScore(int value) {
        score += value;
        scoreLabel.setText(String.format("%06d", score));
    }
    
    public static void updateHealth() {
    	healthPointsLabel.setText(String.format("%01d", healthPoints));
    }

    public static void loseHealth() {
        if(healthPoints > 0)
            healthPoints--;
        healthPointsLabel.setText(String.format("%01d", healthPoints));
    }
    
    public static Integer getHealthPoints() {
		return healthPoints;
	}
    
    public static void setHealthPoints(Integer healthPoints) {
		Hud.healthPoints = healthPoints;
	}
}
