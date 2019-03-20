//Main camera class
//Should contain only things that resizes and reposition camera
//Do not implement HUD here!


package com.sup.theprojectgame.cameras;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sup.theprojectgame.TheProjectGame;

public class GameCamera {
	private OrthographicCamera camera;
	private Viewport viewPort;
	
	public GameCamera() {
		setCamera(new OrthographicCamera());
		setViewPort(new FitViewport(TheProjectGame.GAME_WIDTH, TheProjectGame.GAME_HEIGHT, getCamera()));
		
		getCamera().position.set(getViewPort().getWorldWidth() / 2, getViewPort().getWorldHeight() / 2, 0);
	}
	
	public void cameraUpdate() {
		camera.update();
	}
	
	public void viewPortUpdate(int width, int height) {
		viewPort.update(width, height);
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public void setCamera(OrthographicCamera camera) {
		this.camera = camera;
	}

	public Viewport getViewPort() {
		return viewPort;
	}

	public void setViewPort(Viewport viewPort) {
		this.viewPort = viewPort;
	}
	

}
