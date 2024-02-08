package me.isaiah.entity;

import lombok.Getter;
import lombok.Setter;
import me.isaiah.texture.Texture;
import me.isaiah.game.GamePanel;

import java.awt.*;

@Getter
@Setter
public class Entity {

    private final GamePanel gamePanel;

    public int worldX, worldY;
    private int screenX, screenY;
    private int entitySpeed;
    private Rectangle collisionArea;
    public int collisionAreaDefaultX, collisionAreaDefaultY;

    private boolean collidingWithObject = false;

    private Texture skin;
    private EntityDirection currentDirection;

    public Entity(GamePanel gp) {
        this.gamePanel = gp;
    }
}
