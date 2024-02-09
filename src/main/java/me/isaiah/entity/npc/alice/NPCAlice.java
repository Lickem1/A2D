package me.isaiah.entity.npc.alice;

import me.isaiah.entity.npc.NPCManager;
import me.isaiah.game.GamePanel;
import me.isaiah.entity.Entity;
import me.isaiah.entity.EntityDirection;
import me.isaiah.texture.Texture;
import me.isaiah.texture.TextureType;

import java.awt.*;
import java.util.Random;

public class NPCAlice extends Entity {

    private int counter = 0;

    public NPCAlice(GamePanel gp) {
        super(gp);
        this.setSkin(new Texture(TextureType.PLAYER, AliceSkinMap.ALICE));
        this.getSkin().getTextureAnimation().setAssetSpeed(12);
        this.setCurrentDirection(EntityDirection.IDLE);

        setCollisionArea(new Rectangle(8, 16, 32, 32));
        setCollisionAreaDefaultX(getCollisionArea().x);
        setCollisionAreaDefaultY(getCollisionArea().y);
        init();

        NPCManager.addNPC(this);

    }

    public void init() {
        setWorldX(getGamePanel().getTileSize() * 24);
        setWorldY(getGamePanel().getTileSize() * 21);
        setEntitySpeed(2);
    }


    @Override
    public void update() {

        counter++;
        if(counter == 120) {
            Random random = new Random();
            int i = random.nextInt(100)+1;

            if(i <= 25) {
                this.setCurrentDirection(EntityDirection.UP);
            }
            if(i > 25 && i <= 50) {
                this.setCurrentDirection(EntityDirection.DOWN);            // xD

            }
            if(i > 50 && i <= 75) {
                this.setCurrentDirection(EntityDirection.LEFT);

            }
            if(i > 75 && i <= 100) {
                this.setCurrentDirection(EntityDirection.RIGHT);

            }
            counter = 0;
        }
        // Checking npc collision
        setCollidingWithObject(false);
        getGamePanel().getCollisionManager().checkTile(this);
        getGamePanel().getCollisionManager().checkInteract(this, false);
        getGamePanel().getCollisionManager().npcPlayerInteraction(this);

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
}
