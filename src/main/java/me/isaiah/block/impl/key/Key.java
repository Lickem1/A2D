package me.isaiah.block.impl.key;

import me.isaiah.block.Block;
import me.isaiah.block.BlockEvent;
import me.isaiah.block.BlockManager;
import me.isaiah.game.GamePanel;
import me.isaiah.sound.Sound;
import me.isaiah.sound.SoundType;
import me.isaiah.texture.Texture;
import me.isaiah.texture.TextureType;


public class Key extends Block implements BlockEvent {

    public boolean pickupItem = true;
    private final Sound sound;

    public Key(GamePanel gp) {
        this.name = "Key";
        this.texture = new Texture(TextureType.ITEM_KEY, KeyTextureMap.KEY);
        this.worldX = 25 * gp.getTileSize();
        this.worldY = 22 * gp.getTileSize();
        this.texture.getTextureAnimation().setAssetSpeed(5);
        this.event = this;
        this.sound = new Sound(SoundType.DING);

        BlockManager.addBlockToGame(this);

    }

    @Override
    public void event(Block b) {

        if(pickupItem) {
            sound.play();
            System.out.println("You picked up a key!");
            BlockManager.removeBlockFromGame(this);
        }
    }
}
