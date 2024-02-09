package me.isaiah.entity.npc;

import lombok.Getter;
import me.isaiah.entity.Entity;
import me.isaiah.entity.npc.alice.NPCAlice;
import me.isaiah.game.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class NPCManager {


    @Getter private static List<Entity> npcs = new ArrayList<>();
    private final GamePanel gamePanel;

    public NPCManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        new NPCAlice(gamePanel);
    }

    public static void addNPC(Entity entity) {
        npcs.add(entity);
    }

    public void updateNPCS() {
        for(Entity entity : getNpcs()) {
            entity.update();
        }
    }

    public void drawNPCS(Graphics2D g2) {

        for(Entity block : getNpcs()) {
            block.draw(g2);
        }
    }
}
