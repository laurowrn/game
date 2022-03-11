package model;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.*;
import utils.Constants;

public class Player extends MovingEntity {
    private BufferedImage image;
    private int ricochetStep;
    private int shootingCooldown;
    private int id;

    public Player(int x, int y, int id) {
        super(Constants.playerStep);
        this.energy = Constants.playerEnergy;
        this.ricochetStep = Constants.playerRicochetStep;
        this.shootingCooldown = Constants.playerShootingCooldown;
        this.id = id;

        try {
            image = ImageIO.read(new File(generateRandomPlayerTexture()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.x = x;
        this.y = y;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    private String generateRandomPlayerTexture() {
        Random rand = new Random();
        return "src/assets/player/player" + rand.nextInt(3) + ".png";
    }

    private void setShootingCooldown(int shootingCooldown) {
        this.shootingCooldown = shootingCooldown;
    }

    public int getShootingCooldown() {
        return this.shootingCooldown;
    }

    private void reduceShootingCooldown() {
        setShootingCooldown(getShootingCooldown() - 1);
    }

    public void checkCollisionWith(Player player) {
        if (this.intersects(player)) {
            this.setDirection(getOppositeDirection(this.direction));
            this.move(this.step + ricochetStep);
            this.setDirection(this.getRandomDirection());

            player.setDirection(getOppositeDirection(player.getDirection()));
            player.move(this.step + ricochetStep);
            this.setDirection(this.getRandomDirection());

            this.loseEnergy(Constants.collisioWithPlayerLoss);
            player.loseEnergy(Constants.collisioWithPlayerLoss);
        }
    }

    public void checkCollisionWith(Bullet bullet) {
        if (this.intersects(bullet) && bullet.getOwnerId() != this.id) {
            this.loseEnergy(Constants.collisioWithPlayerLoss);
            bullet.loseEnergy(Constants.bulletEnergy);
        }
    }

    public void checkCollisionWith(Obstacle obstacle) {
        if (this.intersects(obstacle)) {
            this.setDirection(getOppositeDirection(this.direction));
            this.move(this.step + ricochetStep);
            this.setDirection(this.getRandomDirection());
            this.loseEnergy(Constants.collisioWithObstacleLoss);
        }
    }

    public void checkCollisionWith(Battlefield battlefield) {
        if (this.x <= battlefield.getX() || this.y <= battlefield.getY()
                || this.x + this.width >= battlefield.getX() + battlefield.getWidth()
                || this.y + this.height >= battlefield.getY() + battlefield.getHeight()) {

            this.setDirection(getOppositeDirection(this.direction));
            this.move(this.step + ricochetStep);
            this.setDirection(this.getRandomDirection());
        }
    }

    public void shoot(LinkedList<Bullet> shots) {
        if (this.shootingCooldown == Constants.playerShootingCooldown) {
            reduceShootingCooldown();
            shots.add(new Bullet(this.x, this.y, this.direction, Constants.bulletStep, this.id));
        } else if (this.shootingCooldown < Constants.playerShootingCooldown && this.shootingCooldown != 0) {
            reduceShootingCooldown();
        } else {
            setShootingCooldown(Constants.playerShootingCooldown);
        }

    }

    public void draw(Graphics g) {

        g.drawImage(image, x, y, null);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.drawString(Integer.toString(this.energy), x + 15, y + ((int) this.getHeight()) + 20);

    }

}
