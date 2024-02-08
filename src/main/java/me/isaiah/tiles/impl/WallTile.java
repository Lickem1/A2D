package me.isaiah.tiles.impl;

import me.isaiah.tiles.Tile;

public class WallTile extends Tile {

    public WallTile() {
        super("/assets/terrain/wall.png", 20);
        setCollision(true);
    }
}
