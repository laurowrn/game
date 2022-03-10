package model;

import java.awt.*;

public class Battlefield extends StaticEntity {

    public Battlefield(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g) {
        g.drawRect(this.x, this.y, this.width, this.height);
    }
}
