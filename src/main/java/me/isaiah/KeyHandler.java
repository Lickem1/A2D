package me.isaiah;

import me.isaiah.game.GamePanel;
import me.isaiah.game.GameState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private final GamePanel gp;
    public boolean upPressed,downPressed,leftPressed,rightPressed;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            upPressed = true;
        }

        else if(code == KeyEvent.VK_A) {
            leftPressed = true;
        }

        else if(code == KeyEvent.VK_S) {
            downPressed = true;
        }

        else if(code == KeyEvent.VK_D) {
            rightPressed = true;
        }

        else if(code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_P) {
            if(gp.getGameState() == GameState.PAUSED)
                gp.setGameState(GameState.PLAYING);
            else gp.setGameState(GameState.PAUSED);
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            upPressed = false;
        }
        else if(code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        else if(code == KeyEvent.VK_S) {
            downPressed = false;
        }
        else if(code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
