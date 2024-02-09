package me.isaiah.entity.npc.alice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import me.isaiah.texture.Texture;
import me.isaiah.texture.TextureAnimation;
import me.isaiah.texture.TextureMap;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

@Getter
@AllArgsConstructor
public enum AliceSkinMap implements TextureMap {

    ALICE(ar(0, 1, 2),
            ar(9, 10, 11),
            ar(0, 1, 2),
            ar(3, 4, 5),
            ar(6, 7, 8),
            "/assets/player/alice.png");


    private final int[] idleLoc, upLoc, downLoc, leftLoc, rightLoc;
    private final String path;

    private static int[] ar(int... i) {
        return i;
    }

    @Override
    @SneakyThrows
    public void assignTexture(Texture texture) {
        BufferedImage bigImg = ImageIO.read(getClass().getResourceAsStream(path));

        int width = 15;
        int height = 16;
        int rows = 4;
        int cols = 3;

        texture.getTextureAssets().add(new BufferedImage[rows * cols]);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                texture.getTextureAssets().get(0)[(i * cols) + j] = bigImg.getSubimage(j * width, i * height, width, height);
            }
        }

        BufferedImage[] idle = new BufferedImage[this.getIdleLoc().length];
        BufferedImage[] up = new BufferedImage[this.getUpLoc().length];
        BufferedImage[] right = new BufferedImage[this.getRightLoc().length];
        BufferedImage[] left = new BufferedImage[this.getLeftLoc().length];


        for (int i = 0; i < this.getIdleLoc().length; i++) {
            idle[i] = texture.getTextureAssets().get(0)[this.getIdleLoc()[i]];
        }

        for (int i = 0; i < this.getUpLoc().length; i++) {
            up[i] = texture.getTextureAssets().get(0)[this.getUpLoc()[i]];
        }

        for (int i = 0; i < this.getLeftLoc().length; i++) {
            left[i] = texture.getTextureAssets().get(0)[this.getLeftLoc()[i]];
        }

        for (int i = 0; i < this.getRightLoc().length; i++) {
            right[i] = texture.getTextureAssets().get(0)[this.getRightLoc()[i]];
        }

        texture.setTextureAnimation(new TextureAnimation(idle, up, idle, left, right));
    }
}
