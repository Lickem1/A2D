package me.isaiah.tiles.impl;

import me.isaiah.tiles.Tile;

public class TreeTile extends Tile {
    public TreeTile() {
        super("/assets/terrain/tree.png", 19);
        setCollision(true);
    }
}
