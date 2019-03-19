//Map render class
//Deals with map parsing, loading and rendering functions

package com.sup.theprojectgame.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class MapController {
	private TmxMapLoader mapLoader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	
	public MapController(String mapName) {
		mapLoader = new TmxMapLoader();
		map = mapLoader.load(mapName);
		setRenderer(new OrthogonalTiledMapRenderer(map));
	}
	
	public void setRnderView(OrthographicCamera camera) {
		renderer.setView(camera);
	}
	
	public void renderMap() {
		renderer.render();
	}

	public OrthogonalTiledMapRenderer getRenderer() {
		return renderer;
	}

	public void setRenderer(OrthogonalTiledMapRenderer renderer) {
		this.renderer = renderer;
	}

}
