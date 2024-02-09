package me.isaiah.game;

import lombok.Getter;
import lombok.Setter;
import me.isaiah.KeyHandler;
import me.isaiah.block.BlockManager;
import me.isaiah.entity.npc.NPCManager;
import me.isaiah.entity.player.Player;
import me.isaiah.sound.SoundManager;
import me.isaiah.tiles.TileManager;
import me.isaiah.ui.UIManager;

import javax.swing.*;
import java.awt.*;

@Getter
public class GamePanel extends JPanel implements Runnable {

    private final int originalTileSize = 16; // 16x16 tile (2D)
    private final int scale = 4;
    private final int tileSize = originalTileSize * scale; //64x64
    private final int maxScreenColumn = 16;
    private final int maxScreenRow = 12;
    private final int screenWidth = tileSize * maxScreenColumn; // 768 pixels
    private final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    private final int maxWorldColumn = 50;
    private final int maxWorldRow = 50;
    private final int worldWidth = tileSize * maxWorldColumn;
    private final int worldHeight = tileSize * maxWorldRow;

    private final int FPS = 60;
    private Thread gameThread;

    private final Player player;
    @Setter private GameState gameState;

    private final KeyHandler keyHandler;
    private final TileManager tileManager;
    private final CollisionManager collisionManager;
    private final BlockManager blockManager;
    private final UIManager uiManager;
    private final SoundManager soundManager;
    private final NPCManager npcManager;


    public GamePanel() {
        this.keyHandler = new KeyHandler(this);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(this.keyHandler);
        this.setFocusable(true);
        this.tileManager = new TileManager(this);
        this.collisionManager = new CollisionManager(this);
        this.blockManager = new BlockManager(this);
        this.uiManager = new UIManager(this);
        this.soundManager = new SoundManager(this);
        this.npcManager = new NPCManager(this);

        this.gameState = GameState.PLAYING;
        this.player = new Player(this, keyHandler);


    }

    public void startThread() {

        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {

        switch (gameState) {

            case LOADING:
            case PLAYING:
                blockManager.updateBlocks();
                npcManager.updateNPCS(); // npc
                player.update();
                break;
            case PAUSED:
                break;
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileManager.draw(g2);
        blockManager.drawBlocks(g2);
        npcManager.drawNPCS(g2); // npc
        player.draw(g2);
        uiManager.drawUIs(g2);


        g2.dispose();

    }
}
