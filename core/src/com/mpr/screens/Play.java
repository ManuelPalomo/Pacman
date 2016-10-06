package com.mpr.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mpr.PacmanGame;
import com.mpr.Tools.Cell;
import com.mpr.Tools.Constants;
import com.mpr.Tools.MapLoader;
import com.mpr.actors.Movement;
import com.mpr.actors.Pacman;

public class Play extends InputAdapter implements Screen {
    private PacmanGame game;
    private OrthographicCamera camera;
    private Viewport gamePort;

    Texture coin;

    Stage stage;
    Pacman pacman;

    TiledMap map;
    Cell[][] cellMap;
    TiledMapRenderer tiledMapRenderer;

    public Play(PacmanGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();

        gamePort = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        coin = new Texture("coin.png");

        MapLoader loader = new MapLoader("pacman.tmx");
        map = loader.getTiledMap();
        cellMap = loader.getCellMap();

        stage = new Stage(gamePort, game.batch);
        pacman = new Pacman(32f, 24f,cellMap);
        stage.addActor(pacman);

        tiledMapRenderer = new OrthogonalTiledMapRenderer(map, game.batch);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);

    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.RIGHT:
                pacman.changeMovement(Movement.RIGHT);
                break;
            case Input.Keys.LEFT:
                pacman.changeMovement(Movement.LEFT);
                break;
            case Input.Keys.UP:
                pacman.changeMovement(Movement.UP);
                break;
            case Input.Keys.DOWN:
                pacman.changeMovement(Movement.DOWN);
                break;
        }

        return true;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        camera.update();

        tiledMapRenderer.setView(camera);
        int[] backgroundLayer = {0, 3};
        tiledMapRenderer.render(backgroundLayer);


        stage.act();

        game.batch.begin();
        for (int x = 0; x < cellMap.length; x++) {
            for (int y = 0; y < cellMap[x].length; y++) {
                if (cellMap[x][y] == Cell.COIN) {
                    game.batch.draw(coin, x * Constants.TILESIZE, y * Constants.TILESIZE);
                }
            }
        }
        game.batch.end();

        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        coin.dispose();
        map.dispose();
        stage.dispose();
    }
}
