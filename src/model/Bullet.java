package model;

import java.awt.Graphics;

import utils.Constants;

public class Bullet extends MovingEntity {
    private int ownerId;

    public Bullet(int x, int y, Direction direction, int step, int ownerId) {
        super(step);
        this.width = Constants.bulletWidth;
        this.height = Constants.bulletHeight;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.ownerId = ownerId;
        this.energy = Constants.bulletEnergy;
    }

    public int getOwnerId() {
        return this.ownerId;
    }

    public void checkCollisionWith(Obstacle obstacle) {
        if (this.intersects(obstacle)) {
            this.loseEnergy(Constants.bulletEnergy);
        }
    }

    public void checkCollisionWith(Battlefield battlefield) {
        if (this.x <= battlefield.getX() || this.y <= battlefield.getY()
                || this.x + this.width >= battlefield.getX() + battlefield.getWidth()
                || this.y + this.height >= battlefield.getY() + battlefield.getHeight()) {

            this.loseEnergy(Constants.bulletEnergy);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.fillRect(x, y, width, height);
    }

}
