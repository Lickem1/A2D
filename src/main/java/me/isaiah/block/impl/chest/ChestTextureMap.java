package me.isaiah.block.impl.chest;

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
public enum ChestTextureMap implements TextureMap {

    CHEST(ar(0, 1, 2, 3, 4, 5, 6),
            ar(0, 1, 2, 3));

    final int[] closed,open;

    static int[] ar(int... i) {
        return i;
    }

    @Override
    @SneakyThrows
    public void assignTexture(Texture texture) {
        BufferedImage bigImg = ImageIO.read(getClass().getResourceAsStream(texture.getSkinType().getFilePath()));

        int width = 16;
        int height = 13;
        int rows = 1;
        int cols = 7;

        //texture.getTextureAssets().add(new BufferedImage[rows * cols]);
        //for (int i = 0; i < rows; i++) {
        //    for (int j = 0; j < cols; j++) {
        //        texture.getTextureAssets()[(i * cols) + j] = bigImg.getSubimage(j * width, i * height, width, height);
        //    }
        //}
//
        //BufferedImage[] closed = new BufferedImage[this.getClosed().length];
//
        //for(int i = 0; i < this.getClosed().length; i++) {
        //    closed[i] = texture.getTextureAssets()[this.getClosed()[i]];
        //}

        BufferedImage[] closed = textureClosedRescale(0, bigImg, texture, 16,13,1,7);



        texture.setTextureAnimation(new TextureAnimation(closed));

    }

    private BufferedImage[] textureClosedRescale(int index, BufferedImage image, Texture texture, int width,  int height, int rows, int cols) {

        texture.getTextureAssets().add(new BufferedImage[rows * cols]);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                texture.getTextureAssets().get(index)[(i * cols) + j] = image.getSubimage(j * width, i * height, width, height);
            }
        }

        BufferedImage[] rescaled = new BufferedImage[this.getClosed().length];

        for(int i = 0; i < this.getClosed().length; i++) {
            rescaled[i] = texture.getTextureAssets().get(index)[this.getClosed()[i]];
        }

        return rescaled;
    }
}
