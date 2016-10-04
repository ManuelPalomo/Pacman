package com.mpr;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mpr.Tools.Cell;
import com.mpr.Tools.Constants;
import com.mpr.Tools.MapLoader;


public class PacmanGame extends ApplicationAdapter {
    SpriteBatch batch;
    MapLoader mapLoader;
    TiledMap map;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;

    Texture coin;
    Cell[][] cellMap;

    @Override
    public void create() {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        mapLoader = new MapLoader("pacman.tmx");
        map = mapLoader.getTiledMap();

        coin = new Texture("coin.png");


        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        camera.update();

        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);
        batch = new SpriteBatch();
        cellMap = mapLoader.getCellMap();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        tiledMapRenderer.setView(camera);
        int[] backgroundLayer = {0, 3};
        tiledMapRenderer.render(backgroundLayer);


        batch.begin();
        for (int x = 0; x < cellMap.length; x++) {
            for (int y = 0; y < cellMap[x].length; y++) {
                if (cellMap[x][y] == Cell.COIN) {
                    batch.draw(coin, x * Constants.TILESIZE, y * Constants.TILESIZE);
                }
            }
        }
        batch.end();

    }

    @Override
    public void dispose() {
        map.dispose();
        batch.dispose();

    }
}
