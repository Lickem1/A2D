package me.isaiah.texture;

import lombok.Getter;
import lombok.Setter;
import me.isaiah.entity.EntityDirection;

import java.awt.image.BufferedImage;
import java.util.HashMap;

@Getter
@Setter
public class TextureAnimation {

    private final HashMap<EntityDirection, BufferedImage[]> directionAsset = new HashMap<>();

    private int animationFreeze = 0;
    private int assetCounter = 0;
    private int assetSpeed = 8;
    private int assetNum = 0;

    public TextureAnimation(BufferedImage[] idleAnimations, BufferedImage[] upAnimations, BufferedImage[] downAnimations, BufferedImage[] leftAnimations, BufferedImage[] rightAnimations) {
        this.directionAsset.put(EntityDirection.IDLE, idleAnimations);
        this.directionAsset.put(EntityDirection.UP,  upAnimations);
        this.directionAsset.put(EntityDirection.DOWN, downAnimations);
        this.directionAsset.put(EntityDirection.LEFT,  leftAnimations);
        this.directionAsset.put(EntityDirection.RIGHT,  rightAnimations);
    }

    public TextureAnimation(BufferedImage[] idleAnimations) {
        this.directionAsset.put(EntityDirection.BLOCK, idleAnimations);
    }

    public BufferedImage[] getAssetFromDirection(EntityDirection direction) {
        return this.directionAsset.get(direction);
    }

    public void updateAssetAnimation(EntityDirection currentDirection) {
        assetCounter++;

        if(assetCounter > assetSpeed) {

            if(animationFreeze == 0) {

                if(assetNum >= (getAssetFromDirection(currentDirection).length-1)) assetNum = 0;
                else assetNum++;
            } else {

                if(assetNum >= (getAssetFromDirection(currentDirection).length-1)) {
                    assetNum = animationFreeze;
                    assetSpeed = 999999999;
                }
                else assetNum++;
            }
            assetCounter = 0;
        }
    }
}
