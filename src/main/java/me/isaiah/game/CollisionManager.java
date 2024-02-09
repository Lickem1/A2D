package me.isaiah.game;

import me.isaiah.entity.Entity;
import me.isaiah.block.Block;
import me.isaiah.block.BlockManager;
import me.isaiah.entity.player.Player;
import me.isaiah.game.GamePanel;
import me.isaiah.tiles.TileManager;

import java.util.List;

public class CollisionManager {

    private final GamePanel gp;

    public CollisionManager(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.getWorldX() + entity.getCollisionArea().x;
        int entityRightWorldX = entity.getWorldX() + entity.getCollisionArea().x + entity.getCollisionArea().width;
        int entityTopWorldY = entity.getWorldY() + entity.getCollisionArea().y;
        int entityBottomWorldY = entity.getWorldY() + entity.getCollisionArea().y + entity.getCollisionArea().height;

        int entityLeftColumn = entityLeftWorldX / gp.getTileSize();
        int entityRightColumn = entityRightWorldX / gp.getTileSize();
        int entityTopRow = entityTopWorldY / gp.getTileSize();
        int entityBottomRow = entityBottomWorldY / gp.getTileSize();

        int tileNum1, tileNum2;

        switch (entity.getCurrentDirection()) {
            case UP:
                entityTopRow = (entityTopWorldY - entity.getEntitySpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileManager().getMapTileNumber()[entityLeftColumn][entityTopRow];
                tileNum2 = gp.getTileManager().getMapTileNumber()[entityRightColumn][entityTopRow];

                if (TileManager.getTiles().get(tileNum1).collision || TileManager.getTiles().get(tileNum2).collision) {
                    entity.setCollidingWithObject(true);
                }
                break;

            case LEFT:
                entityLeftColumn = (entityLeftWorldX - entity.getEntitySpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileManager().getMapTileNumber()[entityLeftColumn][entityTopRow];
                tileNum2 = gp.getTileManager().getMapTileNumber()[entityLeftColumn][entityBottomRow];

                if (TileManager.getTiles().get(tileNum1).collision || TileManager.getTiles().get(tileNum2).collision) {
                    entity.setCollidingWithObject(true);
                }
                break;

            case RIGHT:
                entityRightColumn = (entityRightWorldX + entity.getEntitySpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileManager().getMapTileNumber()[entityRightColumn][entityTopRow];
                tileNum2 = gp.getTileManager().getMapTileNumber()[entityRightColumn][entityBottomRow];

                if (TileManager.getTiles().get(tileNum1).collision || TileManager.getTiles().get(tileNum2).collision) {
                    entity.setCollidingWithObject(true);
                }
                break;

            case DOWN:
                entityBottomRow = (entityBottomWorldY - entity.getEntitySpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileManager().getMapTileNumber()[entityLeftColumn][entityBottomRow];
                tileNum2 = gp.getTileManager().getMapTileNumber()[entityRightColumn][entityBottomRow];

                if (TileManager.getTiles().get(tileNum1).collision || TileManager.getTiles().get(tileNum2).collision) {
                    entity.setCollidingWithObject(true);
                }
                break;
        }

    }

    // object / block interact
    public Block checkInteract(Entity entity, boolean player) {
        int index = 999;
        Block blockInteracted = null;

        for (Block block : BlockManager.getAllBlocks()) {

            entity.getCollisionArea().x = entity.worldX + entity.getCollisionArea().x;
            entity.getCollisionArea().y = entity.worldY + entity.getCollisionArea().y;

            block.solidArea.x = block.worldX + block.solidArea.x;
            block.solidArea.y = block.worldY + block.solidArea.y;

            switch (entity.getCurrentDirection()) {
                case UP:
                    entity.getCollisionArea().y -= entity.getEntitySpeed();

                    if (entity.getCollisionArea().intersects(block.solidArea)) {
                        if (block.solid) {
                            entity.setCollidingWithObject(true);
                        }
                        if (player) {
                            index = 1;
                        }
                        blockInteracted = block;

                    }
                    break;

                case DOWN:
                    entity.getCollisionArea().y += entity.getEntitySpeed();
                    if (entity.getCollisionArea().intersects(block.solidArea)) {
                        if (block.solid) {
                            entity.setCollidingWithObject(true);
                        }
                        if (player) {
                            index = 1;
                        }
                        blockInteracted = block;
                    }
                    break;

                case LEFT:
                    entity.getCollisionArea().x -= entity.getEntitySpeed();
                    if (entity.getCollisionArea().intersects(block.solidArea)) {
                        if (block.solid) {
                            entity.setCollidingWithObject(true);
                        }

                        if (player) {
                            index = 1;
                        }
                        blockInteracted = block;
                    }
                    break;

                case RIGHT:
                    entity.getCollisionArea().x += entity.getEntitySpeed();

                    if (entity.getCollisionArea().intersects(block.solidArea)) {
                        if (block.solid) {
                            entity.setCollidingWithObject(true);
                        }
                        if (player) {
                            index = 1;
                        }
                        blockInteracted = block;
                    }
                    break;
            }
            entity.getCollisionArea().x = entity.getCollisionAreaDefaultX();
            entity.getCollisionArea().y = entity.getCollisionAreaDefaultY();
            block.solidArea.x = block.solidAreaDefaultX;
            block.solidArea.y = block.solidAreaDefaultY;
        }
        return blockInteracted;
    }

    // NPC / mob
    public Entity checkNPCInteraction(Entity entity, List<Entity> player) {
        Entity entityInteracted = null;

        for (Entity e : player) {

            entity.getCollisionArea().x = entity.worldX + entity.getCollisionArea().x;
            entity.getCollisionArea().y = entity.worldY + entity.getCollisionArea().y;

            e.getCollisionArea().x = e.worldX + e.getCollisionArea().x;
            e.getCollisionArea().y = e.worldY + e.getCollisionArea().y;

            switch (entity.getCurrentDirection()) {
                case UP:
                    entity.getCollisionArea().y -= entity.getEntitySpeed();
                    if (entity.getCollisionArea().intersects(e.getCollisionArea())) {
                        entity.setCollidingWithObject(true);
                        entityInteracted = e;

                    }
                    break;

                case DOWN:
                    entity.getCollisionArea().y += entity.getEntitySpeed();
                    if (entity.getCollisionArea().intersects(e.getCollisionArea())) {
                        entity.setCollidingWithObject(true);
                        entityInteracted = e;
                    }
                    break;

                case LEFT:
                    entity.getCollisionArea().x -= entity.getEntitySpeed();
                    if (entity.getCollisionArea().intersects(e.getCollisionArea())) {
                        entity.setCollidingWithObject(true);
                        entityInteracted = e;
                    }
                    break;

                case RIGHT:
                    entity.getCollisionArea().x += entity.getEntitySpeed();

                    if (entity.getCollisionArea().intersects(e.getCollisionArea())) {
                        entity.setCollidingWithObject(true);
                        entityInteracted = e;
                    }
                    break;
            }
            entity.getCollisionArea().x = entity.getCollisionAreaDefaultX();
            entity.getCollisionArea().y = entity.getCollisionAreaDefaultY();
            e.getCollisionArea().x = e.getCollisionAreaDefaultX();
            e.getCollisionArea().y = e.getCollisionAreaDefaultY();
        }
        return entityInteracted;
    }

    public void npcPlayerInteraction(Entity entity) {
        Player e = gp.getPlayer();


        entity.getCollisionArea().x = entity.worldX + entity.getCollisionArea().x;
        entity.getCollisionArea().y = entity.worldY + entity.getCollisionArea().y;

        e.getCollisionArea().x = e.worldX + e.getCollisionArea().x;
        e.getCollisionArea().y = e.worldY + e.getCollisionArea().y;

        switch (entity.getCurrentDirection()) {
            case UP:
                entity.getCollisionArea().y -= entity.getEntitySpeed();
                if (entity.getCollisionArea().intersects(e.getCollisionArea())) {
                    entity.setCollidingWithObject(true);

                }
                break;

            case DOWN:
                entity.getCollisionArea().y += entity.getEntitySpeed();
                if (entity.getCollisionArea().intersects(e.getCollisionArea())) {
                    entity.setCollidingWithObject(true);
                }
                break;

            case LEFT:
                entity.getCollisionArea().x -= entity.getEntitySpeed();
                if (entity.getCollisionArea().intersects(e.getCollisionArea())) {
                    entity.setCollidingWithObject(true);
                }
                break;

            case RIGHT:
                entity.getCollisionArea().x += entity.getEntitySpeed();

                if (entity.getCollisionArea().intersects(e.getCollisionArea())) {
                    entity.setCollidingWithObject(true);
                }
                break;
        }
        entity.getCollisionArea().x = entity.getCollisionAreaDefaultX();
        entity.getCollisionArea().y = entity.getCollisionAreaDefaultY();
        e.getCollisionArea().x = e.getCollisionAreaDefaultX();
        e.getCollisionArea().y = e.getCollisionAreaDefaultY();
    }
}

