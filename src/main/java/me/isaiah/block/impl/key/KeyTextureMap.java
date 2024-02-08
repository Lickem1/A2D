package me.isaiah.block.impl.key;

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
public enum KeyTextureMap implements TextureMap {

    KEY(ar(0, 1, 2, 3, 4, 5, 6, 7));

    final int[] key;

    static int[] ar(int... i) {
        return i;
    }

    @Override
    @SneakyThrows
    public void assignTexture(Texture texture) {
        BufferedImage bigImg = ImageIO.read(getClass().getResourceAsStream(texture.getSkinType().getFilePath()));
        int width = 14;
        int height = 12;
        int rows = 1;
        int cols = 8;

        texture.getTextureAssets().add(new BufferedImage[rows * cols]);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                texture.getTextureAssets().get(0)[(i * cols) + j] = bigImg.getSubimage(j * width, i * height, width, height);
            }
        }
        KeyTextureMap key = (KeyTextureMap) texture.getTextureMap();

        BufferedImage[] closed = new BufferedImage[key.getKey().length];

        for(int i = 0; i < key.getKey().length; i++) {
            closed[i] = texture.getTextureAssets().get(0)[key.getKey()[i]];
        }
        texture.setTextureAnimation(new TextureAnimation(closed));
    }
}
