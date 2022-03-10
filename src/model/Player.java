package model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.*;
import utils.Constants;

public class Player extends MovingEntity {
    private int id;
    private BufferedImage image;

    public Player(int x, int y) {
        super(Constants.stepPlayer);

        try {
            image = ImageIO.read(new File("src/assets/player.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.x = x;
        this.y = y;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public void checkCollisionWith(Player player) {
        if (this.intersects(player)) {
            this.setDirection(getOppositeDirection(this.direction));
            this.move(this.step + 2);
            this.setDirection(this.getRandomDirection());

            player.setDirection(getOppositeDirection(player.getDirection()));
            player.move(this.step + 2);
            this.setDirection(this.getRandomDirection());
        }
    }

    public void checkCollisionWith(Obstacle obstacle) {
        if (this.intersects(obstacle)) {
            this.setDirection(getOppositeDirection(this.direction));
            this.move(this.step + 2);
            this.setDirection(this.getRandomDirection());
        }
    }

    public void checkCollisionWith(Battlefield battlefield) {
        if (this.x <= battlefield.getX() || this.y <= battlefield.getY()
                || this.x + this.width >= battlefield.getX() + battlefield.getWidth()
                || this.y + this.height >= battlefield.getY() + battlefield.getHeight()) {

            this.setDirection(getOppositeDirection(this.direction));
            this.move(this.step + 2);
            this.setDirection(this.getRandomDirection());
        }
    }

    public void draw(Graphics g) {

        g.drawImage(image, x, y, null);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.drawString(Integer.toString(id), x + 15, y + ((int) this.getHeight()) + 20);

    }
}
