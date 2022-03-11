package model;

import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Obstacle extends StaticEntity {
    private BufferedImage image;

    public Obstacle(int x, int y) {

        try {
            image = ImageIO.read(new File(generateRandomObstacleTexture()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.x = x;
        this.y = y;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    private String generateRandomObstacleTexture() {
        Random rand = new Random();
        return "src/assets/obstacle/obstacle" + rand.nextInt(6) + ".png";
    }

    public void draw(Graphics g) {

        g.drawImage(image, x, y, null);

    }
}
