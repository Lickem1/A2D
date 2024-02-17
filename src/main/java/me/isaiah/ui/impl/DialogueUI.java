package me.isaiah.ui.impl;

import me.isaiah.game.GamePanel;
import me.isaiah.ui.UI;

import java.awt.*;

public class DialogueUI extends UI {

    private final GamePanel gamePanel;

    private String[] index = {"Test message1", "Test message 2"};
    public DialogueUI(GamePanel gp) {
        this.gamePanel = gp;
        //gp.setGameState(GameState.PAUSED);
    }

    private int currentIndex = 0;

    @Override
    public void draw(Graphics2D graphics2D) {
        drawDialogue(index[currentIndex], graphics2D);

    }

    public void updateIndex() {
        if(index.length == currentIndex) return;
        currentIndex++;

    }

    private void drawDialogue(String string, Graphics2D graphics2D) {
        int x = gamePanel.getTileSize()*2;
        int y = gamePanel.getTileSize()+350;
        int width = gamePanel.getScreenWidth() - (gamePanel.getTileSize()*4);
        int height = gamePanel.getTileSize()*4;

        Color c = new Color(0,0,0,100);
        graphics2D.setColor(c);
        graphics2D.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255,255,255);
        graphics2D.setColor(c);
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.drawRoundRect(x+5, y+5, width-10, height-10, 25,25);


        x += gamePanel.getTileSize();
        y += gamePanel.getTileSize();
        graphics2D.drawString(string, x,y);
    }
}
