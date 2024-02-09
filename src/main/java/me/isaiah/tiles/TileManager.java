package me.isaiah.tiles;

import lombok.AccessLevel;
import lombok.Getter;
import me.isaiah.game.GamePanel;
import me.isaiah.entity.player.Player;
import me.isaiah.tiles.impl.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Getter
public class TileManager {

    @Getter
    private static final Map<Integer, Tile> tiles = new HashMap<>();

    @Getter(AccessLevel.NONE)
    private final GamePanel gp;
    private final int[][] mapTileNumber;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        this.mapTileNumber = new int[gp.getMaxWorldColumn()][gp.getMaxWorldRow()];

        getTileImage();
        loadMap("/assets/maps/map2.txt");
    }

    public void getTileImage() {
        new GrassTile();
        new HutTile();
        new WoodTile();
        new RoadTile();
        new TreeTile();
        new WallTile();
        new TableTile();

        tiles.put(21, new Tile("/assets/terrain/grass/water00.png", 21));
        tiles.put(22, new Tile("/assets/terrain/grass/water01.png", 22));
        tiles.put(23, new Tile("/assets/terrain/grass/water02.png", 23));
        tiles.put(24, new Tile("/assets/terrain/grass/water03.png", 24));
        tiles.put(25, new Tile("/assets/terrain/grass/water04.png", 25));
        tiles.put(26, new Tile("/assets/terrain/grass/water05.png", 26)); // Clean this up later
        tiles.put(27, new Tile("/assets/terrain/grass/water06.png", 27));
        tiles.put(28, new Tile("/assets/terrain/grass/water07.png", 28));
        tiles.put(29, new Tile("/assets/terrain/grass/water08.png", 29));
        tiles.put(30, new Tile("/assets/terrain/grass/water09.png", 30));
        tiles.put(31, new Tile("/assets/terrain/grass/water10.png", 31));
        tiles.put(32, new Tile("/assets/terrain/grass/water11.png", 32));
        tiles.put(33, new Tile("/assets/terrain/grass/water12.png", 33));
        tiles.put(34, new Tile("/assets/terrain/grass/water13.png", 34));

        for(int i = 21; i < 34; i++) {
            tiles.get(i).setCollision(true);
        }


    }

    public void loadMap(String world) {
        try {
            InputStream is = getClass().getResourceAsStream(world);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int column = 0;
            int row = 0;

            while (column < gp.getMaxWorldColumn() && row < gp.getMaxWorldRow()) {
                String line = br.readLine();


                while (column < gp.getMaxWorldColumn()) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[column]);

                    mapTileNumber[column][row] = num;
                    column++;
                }
                if (column == gp.getMaxWorldColumn()) {
                    column = 0;
                    row++;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g) {

        int worldColumn = 0;
        int worldRow = 0;

        while (worldColumn < gp.getMaxWorldColumn() && worldRow < gp.getMaxWorldRow()) {
            Player player = gp.getPlayer();

            int tileNumb = mapTileNumber[worldColumn][worldRow];

            int worldX = worldColumn * gp.getTileSize();
            int worldY = worldRow * gp.getTileSize();

            int screenX = worldX - player.getWorldX() + player.screenX;
            int screenY = worldY - player.getWorldY() + player.screenY;


            if (worldX + gp.getTileSize() > player.getWorldX() - player.screenX &&
                    worldX - gp.getTileSize() < player.getWorldX() + player.screenX && // Only draw what the camera can see
                    worldY + gp.getTileSize() > player.getWorldY() - player.screenY &&
                    worldY - gp.getTileSize() < player.getWorldY() + player.screenY) {

                if (tiles.get(tileNumb) != null) {
                    g.drawImage(tiles.get(tileNumb).image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);

                } else System.out.println(tileNumb);

            }

            worldColumn++;

            if (worldColumn == gp.getMaxWorldColumn()) {
                worldColumn = 0;
                worldRow++;
            }
        }
    }
}
