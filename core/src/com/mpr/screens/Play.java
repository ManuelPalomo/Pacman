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
import com.mpr.Tools.map.Cell;
import com.mpr.Tools.Constants;
import com.mpr.Tools.map.CellMap;
import com.mpr.Tools.map.MapLoader;
import com.mpr.actors.Direction;
import com.mpr.actors.ghosts.AiComponent;
import com.mpr.actors.ghosts.Ghost;
import com.mpr.actors.pacman.Pacman;
import com.mpr.ai.AStarPathFinder;
import com.mpr.ai.NodeMap;


public class Play extends InputAdapter implements Screen {
    private PacmanGame game;
    private OrthographicCamera camera;
    private Viewport gamePort;

    Texture coin;

    Stage stage;
    Pacman pacman;
    AiComponent ai;

    TiledMap map;
    CellMap cellMap;
    NodeMap nodeMap;
    TiledMapRenderer tiledMapRenderer;
    AStarPathFinder finder;


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
        nodeMap = new NodeMap(cellMap.getCells());
        finder = new AStarPathFinder(nodeMap);


        stage = new Stage(gamePort, game.batch);
        pacman = new Pacman(32f, 24f, cellMap);

        ai = new AiComponent(finder, pacman);
        Ghost ghost = new Ghost(new Texture("blinky.png"), 110f, 170f, cellMap, ai);
        stage.addActor(pacman);
        stage.addActor(ghost);

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
                pacman.changeMovement(Direction.RIGHT);
                break;
            case Input.Keys.LEFT:
                pacman.changeMovement(Direction.LEFT);
                break;
            case Input.Keys.UP:
                pacman.changeMovement(Direction.UP);
                break;
            case Input.Keys.DOWN:
                pacman.changeMovement(Direction.DOWN);
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

        if (cellMap.allCoinsEaten()) {
            game.setScreen(new Play(game));
            this.dispose();
        }

        stage.act();

        game.batch.begin();
        for (int x = 0; x < cellMap.getCells().length; x++) {
            for (int y = 0; y < cellMap.getCells()[x].length; y++) {
                if (cellMap.getCell(x, y) == Cell.COIN) {
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
        pacman.dispose();
    }
}
