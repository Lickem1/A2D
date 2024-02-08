package me.isaiah.ui;


import java.awt.*;


public abstract class UI {


    public UI() {
        UIManager.addUIToGame(this);
    }
    public abstract void draw(Graphics2D graphics2D);
}
