package me.isaiah.tiles.impl;

import me.isaiah.tiles.Tile;

public class HutTile extends Tile {

    public HutTile() {
        super("/assets/terrain/hut.png", 4);
        setCollision(true);
    }
}
