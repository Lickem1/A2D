package me.isaiah.sound;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SoundType {

    CHEST_OPEN("/sound/chest_open.wav"),
    DING("/sound/coin.wav");

    private final String path;


}
