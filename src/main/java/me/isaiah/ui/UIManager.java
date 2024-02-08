package me.isaiah.ui;

import me.isaiah.game.GamePanel;
import me.isaiah.ui.impl.PauseUI;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UIManager {

    private static final List<UI> allUIs = new ArrayList<>();
    private final GamePanel gamePanel;

    public UIManager(GamePanel gp) {
        this.gamePanel = gp;

        new PauseUI(gp);
    }

    public void drawUIs(Graphics2D graphics2D) {

        for(UI uis : allUIs) {
            uis.draw(graphics2D);
        }
    }

    public static void addUIToGame(UI ui) {
        allUIs.add(ui);
    }
}
