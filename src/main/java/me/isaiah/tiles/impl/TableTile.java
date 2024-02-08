package me.isaiah.tiles.impl;

import me.isaiah.tiles.Tile;

public class TableTile extends Tile {
    public TableTile() {
        super("/assets/terrain/table01.png", 18);
        setCollision(true);
    }
}
