package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Battlefield extends StaticEntity {
    private BufferedImage image;

    public Battlefield(int x, int y) {
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
        return "src/assets/battlefield/battlefield" + rand.nextInt(3) + ".jpg";
    }

    @Override
    public void draw(Graphics2D g, JPanel observer) {
        // g.drawRect(this.x, this.y, this.width, this.height);
        g.drawImage(image, x, y, observer);
    }
}
