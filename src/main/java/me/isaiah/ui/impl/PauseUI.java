package me.isaiah.ui.impl;

import me.isaiah.game.GamePanel;
import me.isaiah.game.GameState;
import me.isaiah.ui.UI;

import java.awt.*;

public class PauseUI extends UI {

    private GamePanel gp;
    private Font font;

    public PauseUI(GamePanel gp) {
        this.gp = gp;
        this.font = new Font("Arial", Font.PLAIN, 50);
    }

    public void draw(Graphics2D graphics2D) {

        if(gp.getGameState() == GameState.PAUSED) {

            graphics2D.setColor(Color.WHITE);
            graphics2D.setFont(font);

            String string = "PAUSED";
            int length = (int) graphics2D.getFontMetrics().getStringBounds(string, graphics2D).getWidth();

            int x = gp.getScreenWidth()/2 - length/2;
            int y = gp.getScreenHeight()/2;

            graphics2D.drawString(string, x, y);
        }
    }
}
