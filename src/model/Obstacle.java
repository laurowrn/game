package model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.*;

public class Obstacle extends Entity {
    private BufferedImage image;
    private String path;

    public Obstacle(int x, int y, String path) {
        this.path = path;

        try {
            image = ImageIO
                    .read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.x = x;
        this.y = y;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public void draw(Graphics g) {

        g.drawImage(image, x, y, null);

    }
}
