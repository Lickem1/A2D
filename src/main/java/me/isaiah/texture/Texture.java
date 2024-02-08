package me.isaiah.texture;

import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Texture {

    private final List<BufferedImage[]> textureAssets = new ArrayList<>();
    private final TextureType skinType;
    private TextureMap textureMap;
    private TextureAnimation textureAnimation;

    public Texture(TextureType type, TextureMap textureMap) {
        this.skinType = type;
        this.textureMap = textureMap;
        this.textureMap.assignTexture(this);
    }
}
