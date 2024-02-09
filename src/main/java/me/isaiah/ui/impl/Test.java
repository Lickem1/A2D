package me.isaiah.ui.impl;

import me.isaiah.ui.UI;

import java.awt.*;

public class Test extends UI {

    Font font;
    public Test() {

        font = new Font("Arial", Font.PLAIN, 25);
    }

    @Override
    public void draw(Graphics2D graphics2D) {

        graphics2D.setFont(font);
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawString("dev a1.0.5", 900 ,760);

    }
}
