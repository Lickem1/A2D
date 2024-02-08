package me.isaiah.block;

import lombok.Getter;
import me.isaiah.game.GamePanel;
import me.isaiah.block.impl.chest.Chest;
import me.isaiah.block.impl.key.Key;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BlockManager {

    @Getter private static final List<Block> allBlocks = new ArrayList<>();

    private final GamePanel gamePanel;

    public BlockManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        new Chest(gamePanel, 25, 19);
        new Chest(gamePanel, 27, 19);
        new Key(gamePanel);

    }


    public static void addBlockToGame(Block block) {
        allBlocks.add(block);
    }
    public static void removeBlockFromGame(Block block) {
        allBlocks.remove(block);
    }

    public void drawBlocks(Graphics2D g2) {

        for(Block block : allBlocks) {
            block.draw(g2, gamePanel);
        }
    }

    public void updateBlocks() {

        for(Block block : allBlocks) {
            block.update();
        }
    }

    public void checkCollision(Block block) {
        if(block == null) return;
        if(block.event == null) return;

        block.event.event(block);
    }
}
