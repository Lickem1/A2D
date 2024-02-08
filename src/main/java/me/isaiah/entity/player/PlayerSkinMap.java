package me.isaiah.entity.player;

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
public enum PlayerSkinMap implements TextureMap {

    RAMONA(ar(0, 1, 2, 3, 4, 5),
            ar(12, 13, 14, 15, 16, 17),
            ar(0, 1, 2, 3, 4, 5),
            ar(6, 7, 8, 9, 10, 11),
            ar(6, 7, 8, 9, 10, 11));


    private final int[] idleLoc, upLoc, downLoc, leftLoc, rightLoc;
    private static int[] ar(int... i) {
        return i;
    }

    @Override
    @SneakyThrows
    public void assignTexture(Texture texture) {
        BufferedImage bigImg = ImageIO.read(getClass().getResourceAsStream(texture.getSkinType().getFilePath()));

        int width = 16;
        int height = 21;
        int rows = 3;
        int cols = 6;

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


        for(int i = 0; i < this.getIdleLoc().length; i++) {
            idle[i] = texture.getTextureAssets().get(0)[this.getIdleLoc()[i]];
        }

        for(int i = 0; i < this.getUpLoc().length; i++) {
            up[i] = texture.getTextureAssets().get(0)[this.getUpLoc()[i]];
        }

        for(int i = 0; i < this.getLeftLoc().length; i++) {
            left[i] = rotateImage(texture.getTextureAssets().get(0)[this.getLeftLoc()[i]]);
        }

        for(int i = 0; i < this.getRightLoc().length; i++) {
            right[i] = texture.getTextureAssets().get(0)[this.getRightLoc()[i]];
        }

        texture.setTextureAnimation(new TextureAnimation(idle, up, idle, left, right));
    }
}
