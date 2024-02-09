package me.isaiah.ui;

import me.isaiah.game.GamePanel;
import me.isaiah.ui.impl.PauseUI;
import me.isaiah.ui.impl.Test;

import java.awt.*;
import java.util.*;

public class UIManager {

    private static final Map<UUID, UI> allUIs = new HashMap<>();
    private final GamePanel gamePanel;

    public UIManager(GamePanel gp) {
        this.gamePanel = gp;

        new PauseUI(gp);
        new Test();
    }

    public void drawUIs(Graphics2D graphics2D) {

        for(UI uis : allUIs.values()) {
            uis.draw(graphics2D);
        }
    }

    public static void addUIToGame(UI ui) {
        allUIs.put(UUID.randomUUID(), ui);
    }
    public static UI getUI(UUID ui) {
        return allUIs.get(ui);
    }
}
