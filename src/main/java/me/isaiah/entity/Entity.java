package me.isaiah.entity;

import lombok.Getter;
import lombok.Setter;
import me.isaiah.texture.Texture;
import me.isaiah.game.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

@Getter
@Setter
public class Entity {

    private final GamePanel gamePanel;

    public int worldX, worldY;
    private int screenX, screenY;
    private int entitySpeed;
    private Rectangle collisionArea;
    public int collisionAreaDefaultX, collisionAreaDefaultY;

    private boolean collidingWithObject;

    private Texture skin;
    private EntityDirection currentDirection;

    public Entity(GamePanel gp) {
        this.gamePanel = gp;
    }

    public void update() {}
    public void draw(Graphics2D g) {
        GamePanel gp = getGamePanel();

        int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().screenX;
        int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().screenY;


        if(worldX + gp.getTileSize() > gp.getPlayer().getWorldX() - gp.getPlayer().screenX &&
                worldX - gp.getTileSize()< gp.getPlayer().getWorldX() + gp.getPlayer().screenX &&
                worldY + gp.getTileSize()> gp.getPlayer().getWorldY() - gp.getPlayer().screenY &&
                worldY - gp.getTileSize() < gp.getPlayer().getWorldY() + gp.getPlayer().screenY) {

            BufferedImage[] images = getSkin().getTextureAnimation().getAssetFromDirection(getCurrentDirection());
            BufferedImage toDrawImage = images[getSkin().getTextureAnimation().getAssetNum()];

            g.drawImage(toDrawImage, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
        }
    }
}
