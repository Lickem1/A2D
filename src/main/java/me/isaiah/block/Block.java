package me.isaiah.block;

import me.isaiah.game.GamePanel;
import me.isaiah.entity.EntityDirection;
import me.isaiah.block.impl.chest.Chest;
import me.isaiah.texture.Texture;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Block {

    public Texture texture;
    public String name;
    public boolean solid = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,48,48); // entire object is solid
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public BlockEvent event;

    public void draw(Graphics2D g, GamePanel gp) {
        int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().screenX;
        int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().screenY;


        if(worldX + gp.getTileSize() > gp.getPlayer().getWorldX() - gp.getPlayer().screenX &&
                worldX - gp.getTileSize()< gp.getPlayer().getWorldX() + gp.getPlayer().screenX &&
                worldY + gp.getTileSize()> gp.getPlayer().getWorldY() - gp.getPlayer().screenY &&
                worldY - gp.getTileSize() < gp.getPlayer().getWorldY() + gp.getPlayer().screenY) {

            BufferedImage[] images = texture.getTextureAnimation().getAssetFromDirection(EntityDirection.BLOCK);
            BufferedImage toDrawImage = images[texture.getTextureAnimation().getAssetNum()];

            g.drawImage(toDrawImage, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
        }
    }

    public void update() {

        texture.getTextureAnimation().updateAssetAnimation(EntityDirection.BLOCK);
    }
}
