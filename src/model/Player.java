package model;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.image.BufferedImage;
import java.awt.*;
import utils.Constants;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

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

    public int getBulletX() {
        if (this.direction == Direction.UP || this.direction == Direction.RIGHT) {
            return this.x + (int) this.getWidth();
        } else {
            return this.x;
        }
    }

    public int getBulletY() {
        if (this.direction == Direction.RIGHT || this.direction == Direction.DOWN) {
            return this.y + (int) this.getHeight();
        } else {
            return this.y;
        }
    }

    public void shoot(LinkedList<Bullet> shots) {
        if (this.shootingCooldown == Constants.playerShootingCooldown) {
            reduceShootingCooldown();
            shots.add(new Bullet(getBulletX(), getBulletY(), this.direction, Constants.bulletStep, this.id));
        } else if (this.shootingCooldown < Constants.playerShootingCooldown && this.shootingCooldown != 0) {
            reduceShootingCooldown();
        } else {
            setShootingCooldown(Constants.playerShootingCooldown);
        }
    }

    @Override
    public void draw(Graphics2D g, JPanel observer) {
        double rotationRequired = Math.toRadians(0);
        double locationX = image.getWidth() / 2;
        double locationY = image.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationY, locationX);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        if (this.direction == Direction.DOWN) {
            rotationRequired = Math.toRadians(90);
            locationX = image.getWidth() / 2;
            locationY = image.getHeight() / 2;
            tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
            op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        } else if (this.direction == Direction.RIGHT) {
            rotationRequired = Math.toRadians(0);
            locationX = image.getWidth() / 2;
            locationY = image.getHeight() / 2;
            tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
            op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        } else if (this.direction == Direction.LEFT) {
            rotationRequired = Math.toRadians(180);
            locationX = image.getWidth() / 2;
            locationY = image.getHeight() / 2;
            tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
            op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        } else if (this.direction == Direction.UP) {
            rotationRequired = Math.toRadians(270);
            locationX = image.getWidth() / 2;
            locationY = image.getHeight() / 2;
            tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
            op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        }
        g.drawImage(op.filter(image, null), x, y, observer);

        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.setColor(Color.yellow);
        g.drawString(Integer.toString(this.energy), x + 10, y + ((int) this.getHeight()) + 15);
    }
}