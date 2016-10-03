package com.mpr;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mpr.Tools.Cell;
import com.mpr.Tools.MapLoader;


public class PacmanGame extends ApplicationAdapter {
    SpriteBatch batch;
    MapLoader mapLoader;
    TiledMap map;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;

    @Override
    public void create() {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        mapLoader = new MapLoader("pacman.tmx");
        map = mapLoader.getTiledMap();

        Cell[][] cellMap = mapLoader.getCellMap();
        for (int x = 0; x < cellMap.length; x++) {
            for (int y = 0; y < cellMap[x].length; y++) {
                System.out.println(cellMap[x][y]);
            }
        }
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        camera.update();

        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);
        batch = new SpriteBatch();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        tiledMapRenderer.setView(camera);
        int[] backgroundLayer = {0};
        tiledMapRenderer.render(backgroundLayer);

    }

    @Override
    public void dispose() {
        map.dispose();
        batch.dispose();

    }
}
