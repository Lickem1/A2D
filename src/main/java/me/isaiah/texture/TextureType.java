package me.isaiah.texture;

import lombok.Getter;
import lombok.Setter;

@Getter

public enum TextureType {

    PLAYER("/assets/player/test.png"),
    ITEM_CHEST("/assets/objects/Chest_Closed.png"),
    ITEM_KEY("/assets/objects/Key.png");


    private final String filePath;

    TextureType(String filePath) {
        this.filePath = filePath;
    }
}
