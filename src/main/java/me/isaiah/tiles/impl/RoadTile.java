package me.isaiah.tiles.impl;

import me.isaiah.tiles.Tile;

public class RoadTile {

    public RoadTile() {
        new RoadTile.Road_Plain();
        new RoadTile.Road_Top_Left();
        new RoadTile.Road_Bottom_Center();
        new RoadTile.Road_Bottom_Left();
        new RoadTile.Road_Center_Right();
        new RoadTile.Road_Center_Left();
        new RoadTile.Road_Top_Right();
        new RoadTile.Road_Top_Center();
        new RoadTile.Road_Bottom_Right();
        new RoadTile.Road_Bottom_Right_Corner();
        new RoadTile.Road_Bottom_Left_Corner();
        new RoadTile.Road_Top_Right_Corner();
        new RoadTile.Road_Top_Left_Corner();
    }

    public static class Road_Plain extends Tile {

        public Road_Plain() {
            super("/assets/terrain/road00.png", 5);
        }
    }

    public static class Road_Top_Left extends Tile {

        public Road_Top_Left() {
            super("/assets/terrain/road01.png", 6);
        }
    }

    public static class Road_Bottom_Center extends Tile {
        public Road_Bottom_Center() {
            super("/assets/terrain/road02.png", 7);
        }
    }

    public static class Road_Top_Right extends Tile {
        public Road_Top_Right() {
            super("/assets/terrain/road03.png", 8);
        }
    }
    public static class Road_Center_Right extends Tile {
        public Road_Center_Right() {
            super("/assets/terrain/road04.png", 9);
        }
    }
    public static class Road_Center_Left extends Tile {
        public Road_Center_Left() {
            super("/assets/terrain/road05.png", 10);
        }
    }

    public static class Road_Bottom_Left extends Tile {
        public Road_Bottom_Left() {
            super("/assets/terrain/road06.png", 11);
        }
    }
    public static class Road_Top_Center extends Tile {
        public Road_Top_Center() {
            super("/assets/terrain/road07.png", 12);
        }
    }
    public static class Road_Bottom_Right extends Tile {
        public Road_Bottom_Right() {
            super("/assets/terrain/road08.png", 13);
        }
    }

    public static class Road_Bottom_Right_Corner extends Tile {
        public Road_Bottom_Right_Corner() {
            super("/assets/terrain/road09.png", 14);
        }
    }
    public static class Road_Bottom_Left_Corner extends Tile {
        public Road_Bottom_Left_Corner() {
            super("/assets/terrain/road10.png", 15);
        }
    }

    public static class Road_Top_Right_Corner extends Tile {
        public Road_Top_Right_Corner() {
            super("/assets/terrain/road11.png", 16);
        }
    }

    public static class Road_Top_Left_Corner extends Tile {
        public Road_Top_Left_Corner() {
            super("/assets/terrain/road12.png", 17);
        }
    }

}
