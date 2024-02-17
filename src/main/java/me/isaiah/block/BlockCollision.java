package me.isaiah.block;

import me.isaiah.entity.Entity;
import me.isaiah.game.GamePanel;

public class BlockCollision {

    public BlockCollision(GamePanel gp, Entity entity, boolean player) {

        Block block = checkInteract(entity);

        if (!player) return;
        gp.getBlockManager().checkCollision(block);
    }

    private Block checkInteract(Entity entity) {
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
                        blockInteracted = block;

                    }
                    break;

                case DOWN:
                    entity.getCollisionArea().y += entity.getEntitySpeed();
                    if (entity.getCollisionArea().intersects(block.solidArea)) {
                        if (block.solid) {
                            entity.setCollidingWithObject(true);
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
                        blockInteracted = block;
                    }
                    break;

                case RIGHT:
                    entity.getCollisionArea().x += entity.getEntitySpeed();

                    if (entity.getCollisionArea().intersects(block.solidArea)) {
                        if (block.solid) {
                            entity.setCollidingWithObject(true);
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
}
