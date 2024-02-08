package me.isaiah.tiles.impl;

import me.isaiah.tiles.Tile;

public class GrassTile {

    public GrassTile() {
        new Grass_Plain2();
        new Grass_Plain();
        new Grass_Textured();
    }
    public static class Grass_Plain2 extends Tile {

        public Grass_Plain2() {
            super("/assets/terrain/grass/grass00.png", 0);
        }
    }
    public static class Grass_Plain extends Tile {

        public Grass_Plain() {
            super("/assets/terrain/grass/grass00.png", 2);
        }
    }
    public static class Grass_Textured extends Tile {

        public Grass_Textured() {
            super("/assets/terrain/grass/grass01.png", 3);
        }
    }
}
