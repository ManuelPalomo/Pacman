package com.mpr.Tools;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class MapLoader {
    private String path;
    private TiledMap map;

    public MapLoader(String path) {
        this.path = path;
    }

    public TiledMap getTiledMap() {
        if (map == null) {
            map = new TmxMapLoader().load(path);
        }
        return map;
    }

    public Cell[][] getCellMap() {
        Cell[][] cellMap = initializeCellArray();
        fillCellMapWithLayer(cellMap, 3, Cell.COIN);
        fillCellMapWithLayer(cellMap, 2, Cell.WALL);
        return cellMap;
    }

    private Cell[][] initializeCellArray() {
        TiledMapTileLayer background = (TiledMapTileLayer) map.getLayers().get(0);
        Cell[][] cellMap = new Cell[background.getWidth()][background.getHeight()];
        for (int x = 0; x < cellMap.length; x++) {
            for (int y = 0; y < cellMap[x].length; y++) {
                cellMap[x][y] = Cell.FLOOR;
            }
        }
        return cellMap;
    }

    private void fillCellMapWithLayer(Cell[][] cellMap, int layerIndex, Cell cell) {
        MapLayer layer = map.getLayers().get(layerIndex);
        MapObjects objects = layer.getObjects();

        for (MapObject object : objects) {
            float positionX = (Float) object.getProperties().get("x");
            float positionY = (Float) object.getProperties().get("y");
            cellMap[Math.round(positionX / Constants.TILESIZE)][Math.round(positionY / Constants.TILESIZE)] = cell;
        }
    }


}
