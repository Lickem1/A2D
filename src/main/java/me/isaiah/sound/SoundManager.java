package me.isaiah.sound;

import lombok.SneakyThrows;
import me.isaiah.game.GamePanel;

import java.util.HashMap;

public class SoundManager {

    private final HashMap<SoundType, Sound> sounds = new HashMap<>();

    private final GamePanel gp;
    @SneakyThrows
    public SoundManager(GamePanel gamePanel) {
        this.gp = gamePanel;

        //for(SoundType types : SoundType.values()) {
        //    Sound sound = new Sound(types);
        //    sounds.put(types, sound);
        //}
    }

    public Sound getSound(SoundType type) {
        return sounds.get(type);
    }
}
