package model;

import java.awt.Graphics;

import utils.Constants;

public class Bullet extends MovingEntity {
    private int ownerId;
    private int energy;

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

    @Override
    public void draw(Graphics g) {
        g.fillRect(x, y, width, height);
    }

}
