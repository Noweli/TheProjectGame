package com.sup.theprojectgame.scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sup.theprojectgame.TheProjectGame;


public class Hud {
    public Stage stage;
    private Viewport viewport;

    //TO BE MODIFIED LATER
    public Hud(SpriteBatch sb) {
        viewport = new FitViewport(TheProjectGame.GAME_WIDTH, TheProjectGame.GAME_WIDTH, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        stage.addActor(table);
    }
}
