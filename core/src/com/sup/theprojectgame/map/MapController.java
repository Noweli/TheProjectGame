//Map render class
//Deals with map parsing, loading and rendering functions

package com.sup.theprojectgame.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.sup.theprojectgame.TheProjectGame;

public class MapController {
	private TmxMapLoader mapLoader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;

	public MapController(String mapName) {
		mapLoader = new TmxMapLoader();
		setMap(mapLoader.load(mapName));
		setRenderer(new OrthogonalTiledMapRenderer(getMap(), 1 / TheProjectGame.PIXELSCALE));
	}

	public void setRenderView(OrthographicCamera camera) {
		renderer.setView(camera);
	}

	public void renderMap() {
		renderer.render();
	}

	public void dispose() {
		map.dispose();
		renderer.dispose();
	}

	public OrthogonalTiledMapRenderer getRenderer() {
		return renderer;
	}

	public void setRenderer(OrthogonalTiledMapRenderer renderer) {
		this.renderer = renderer;
	}

	public TiledMap getMap() {
		return map;
	}

	public void setMap(TiledMap map) {
		this.map = map;
	}

}
