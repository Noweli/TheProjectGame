//Main camera class
//Should contain only things that resizes and reposition camera
//Do not implement HUD here!

package com.sup.theprojectgame.cameras;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sup.theprojectgame.TheProjectGame;

public class GameCamera {
	private float cameraZoom = -8.5f;

	private OrthographicCamera camera;
	private Viewport viewPort;

	public GameCamera() {
		setCamera(new OrthographicCamera());
		setViewPort(new FitViewport(TheProjectGame.GAME_WIDTH / TheProjectGame.PIXELSCALE + cameraZoom,
				TheProjectGame.GAME_HEIGHT / TheProjectGame.PIXELSCALE + cameraZoom, getCamera()));

		getCamera().position.set(getViewPort().getWorldWidth() / 2, getViewPort().getWorldHeight() / 2, 0);
	}

	public void cameraUpdate(float posX, float posY) {
		camera.position.x = posX;
		camera.position.y = posY;
		//if(256 - posY > 250f )
			//camera.position.y = posY + 2f;
		camera.update();
	}

	public void viewPortUpdate(int width, int height) {
		viewPort.update(width, height);
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	private void setCamera(OrthographicCamera camera) {
		this.camera = camera;
	}

	private Viewport getViewPort() {
		return viewPort;
	}

	private void setViewPort(Viewport viewPort) {
		this.viewPort = viewPort;
	}

}
