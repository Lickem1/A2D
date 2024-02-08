package me.isaiah.tiles;

import lombok.Setter;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

@Setter
public class Tile {

    private final String asset;
    public BufferedImage image;
    public boolean collision = false;
    private final int id;

    @SneakyThrows
    public Tile(String asset, int id) {
        this.asset = asset;
        this.image = ImageIO.read(getClass().getResourceAsStream(asset));
        this.id = id;
        TileManager.getTiles().put(id, this);
    }
}
