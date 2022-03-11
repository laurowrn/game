package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Battlefield extends StaticEntity {
    private BufferedImage image;

    public Battlefield(int x, int y, int width, int height) {
        try {
            image = ImageIO.read(new File("src/assets/battlefield/battlefield1.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.x = x;
        this.y = y;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public void draw(Graphics g) {
        g.drawRect(this.x, this.y, this.width, this.height);
        // g.drawImage(image, x, y, null);
    }
}
