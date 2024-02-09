package me.isaiah.sound;

import lombok.Getter;
import lombok.SneakyThrows;

import javax.sound.sampled.*;

@Getter
public class Sound {

    private final SoundType soundType;
    private final AudioInputStream audio;

    @SneakyThrows
    public Sound(SoundType soundType) {
        this.soundType = soundType;
        this.audio = AudioSystem.getAudioInputStream(getClass().getResource(soundType.getPath()));
    }

    @SneakyThrows
    public void play() {
        Clip clip = AudioSystem.getClip();
        clip.open(audio);
        clip.start();
    }

    public void stop() {

    }

}
