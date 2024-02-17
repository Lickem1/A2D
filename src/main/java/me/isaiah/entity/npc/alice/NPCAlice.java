package me.isaiah.entity.npc.alice;

import com.sun.xml.internal.bind.v2.model.core.ID;
import lombok.Setter;
import me.isaiah.block.BlockCollision;
import me.isaiah.entity.npc.NPCManager;
import me.isaiah.entity.player.Player;
import me.isaiah.game.GamePanel;
import me.isaiah.entity.Entity;
import me.isaiah.entity.EntityDirection;
import me.isaiah.sound.Sound;
import me.isaiah.sound.SoundType;
import me.isaiah.texture.Texture;
import me.isaiah.texture.TextureType;

import java.awt.*;
import java.util.List;
import java.util.Random;

@Setter
public class NPCAlice extends Entity {

    private int counter = 0;
    private boolean test = false;

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
        if (counter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                this.setCurrentDirection(EntityDirection.UP);
            }
            if (i > 25 && i <= 50) {
                this.setCurrentDirection(EntityDirection.DOWN);            // xD

            }
            if (i > 50 && i <= 75) {
                this.setCurrentDirection(EntityDirection.LEFT);

            }
            if (i > 75 && i <= 100) {
                this.setCurrentDirection(EntityDirection.RIGHT);

            }
            counter = 0;
        }
        // Checking npc collision
        setCollidingWithObject(false);
        getGamePanel().getCollisionManager().checkTile(this);
        new BlockCollision(getGamePanel(), this, false);
        getGamePanel().getCollisionManager().npcPlayerInteraction(this);

        if (isCollidingWithObject()) return;

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


        getSkin().getTextureAnimation().updateAssetAnimation(getCurrentDirection());
    }

    @Override
    public void draw(Graphics2D g) {


        int screenX = worldX - getGamePanel().getPlayer().getWorldX() + getGamePanel().getPlayer().screenX;
        int screenY = worldY - getGamePanel().getPlayer().getWorldY() + getGamePanel().getPlayer().screenY;

        g.setColor(new Color(255, 103, 0));
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Hey there!", screenX + 3, screenY - 30);

        super.draw(g);
    }
}
