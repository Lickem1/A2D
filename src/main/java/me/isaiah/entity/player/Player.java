package me.isaiah.entity.player;

import me.isaiah.block.impl.chest.Chest;
import me.isaiah.game.GamePanel;
import me.isaiah.KeyHandler;
import me.isaiah.entity.Entity;
import me.isaiah.entity.EntityDirection;
import me.isaiah.block.Block;
import me.isaiah.block.BlockManager;
import me.isaiah.block.impl.key.Key;
import me.isaiah.texture.Texture;
import me.isaiah.texture.TextureType;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    private final KeyHandler keyHandler;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyHandler) {
        super(gp);
        this.setSkin(new Texture(TextureType.PLAYER, PlayerSkinMap.RAMONA));
        this.keyHandler = keyHandler;
        this.screenX = gp.getScreenWidth()/2 - (gp.getTileSize()/2);
        this.screenY = gp.getScreenHeight()/2 - (gp.getTileSize()/2);
        this.setCurrentDirection(EntityDirection.IDLE);
        setCollisionArea(new Rectangle(8, 16, 32, 32));
        setCollisionAreaDefaultX(getCollisionArea().x);
        setCollisionAreaDefaultY(getCollisionArea().y);
        init();
    }

    public void init() {
        setWorldX(getGamePanel().getTileSize() * 23);
        setWorldY(getGamePanel().getTileSize() * 21);
        setEntitySpeed(4);
    }

    public void update() {

        // make this better
        if(keyHandler.upPressed) {
            this.setCurrentDirection(EntityDirection.UP);
        }
        if(keyHandler.downPressed) {
            this.setCurrentDirection(EntityDirection.DOWN);
        }
        if(keyHandler.leftPressed) {
            this.setCurrentDirection(EntityDirection.LEFT);
        }
        if(keyHandler.rightPressed) {
            this.setCurrentDirection(EntityDirection.RIGHT);
        }
        if(!keyHandler.upPressed && !keyHandler.downPressed && !keyHandler.leftPressed && !keyHandler.rightPressed) {
            this.setCurrentDirection(EntityDirection.IDLE);

        }


        // Checking player collision
        setCollidingWithObject(false);
        getGamePanel().getCollisionManager().checkTile(this);
        Block index = getGamePanel().getCollisionManager().checkInteract(this, true);

        getGamePanel().getBlockManager().checkCollision(index);

        if(!isCollidingWithObject()) {
            switch (getCurrentDirection()) {
                case UP:
                    this.worldY -= this.getEntitySpeed();
                    break;
                case DOWN:
                    this.worldY += this.getEntitySpeed();
                    break;
                case LEFT:
                    this.worldX -= this.getEntitySpeed();
                    break;
                case RIGHT:
                    this.worldX += this.getEntitySpeed();
                    break;
            }
        }

        getSkin().getTextureAnimation().updateAssetAnimation(getCurrentDirection());
    }

    public void pickupItem(Block block) {

        if(block != null) {

            if(block instanceof Key) {
                Key key = (Key) block;

                if(key.pickupItem) {
                    BlockManager.removeBlockFromGame(block);
                }
            }
        }

    }

    public void draw(Graphics2D g) {

        BufferedImage[] images = this.getSkin().getTextureAnimation().getAssetFromDirection(getCurrentDirection());
        BufferedImage toDrawImage = images[getSkin().getTextureAnimation().getAssetNum()];

        g.drawImage(toDrawImage, screenX, screenY, getGamePanel().getTileSize(), getGamePanel().getTileSize(), null);


    }
}
