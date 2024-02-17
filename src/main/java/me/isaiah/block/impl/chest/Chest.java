package me.isaiah.block.impl.chest;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import me.isaiah.block.Block;
import me.isaiah.block.BlockEvent;
import me.isaiah.block.BlockManager;
import me.isaiah.game.GamePanel;
import me.isaiah.sound.Sound;
import me.isaiah.sound.SoundType;
import me.isaiah.texture.Texture;
import me.isaiah.texture.TextureAnimation;
import me.isaiah.texture.TextureType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class Chest extends Block implements BlockEvent {

    private final GamePanel gamePanel;
    private boolean opened = false;
    private final List<Sound> sounds = new ArrayList<>();

    public Chest(GamePanel gp, int x, int y) {
        this.gamePanel = gp;
        this.name = "Chest";
        this.texture = new Texture(TextureType.ITEM_CHEST, ChestTextureMap.CHEST);
        this.worldX = x * gp.getTileSize();
        this.worldY = y * gp.getTileSize();
        this.texture.getTextureAnimation().setAssetSpeed(5);
        this.solid = true;
        this.event = this;


        sounds.add(new Sound(SoundType.CHEST_OPEN));

        BlockManager.addBlockToGame(this);
    }

    @SneakyThrows
    public void openChest() {
        opened = true;

        BufferedImage bigImg2 = ImageIO.read(getClass().getResourceAsStream("/assets/objects/opened.png"));
        BufferedImage[] opened = textureOpenedRescale(bigImg2, texture, 16, 21, 1, 4);
        TextureAnimation animation = new TextureAnimation(opened);
        animation.setAnimationFreeze(3);

        texture.setTextureAnimation(animation);
        sounds.get(0).play();
    }

    private BufferedImage[] textureOpenedRescale(BufferedImage image, Texture texture, int width, int height, int rows, int cols) {

        ChestTextureMap chest = (ChestTextureMap) texture.getTextureMap();

        texture.getTextureAssets().add(new BufferedImage[rows * cols]);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                texture.getTextureAssets().get(0)[(i * cols) + j] = image.getSubimage(j * width, i * height, width, height);
            }
        }

        BufferedImage[] rescaled = new BufferedImage[chest.getOpen().length];

        for (int i = 0; i < chest.getOpen().length; i++) {
            rescaled[i] = texture.getTextureAssets().get(0)[chest.getOpen()[i]];
        }

        return rescaled;
    }

    public void event(Block b) {


        if(isOpened()) return;

        openChest();
        System.out.println("Opened chest!");
    }

}
