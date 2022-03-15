package model;

import java.util.Random;

public abstract class MovingEntity extends Entity {
    protected Direction direction = Direction.RIGHT;
    protected int step;
    protected int energy;

    public MovingEntity(int step) {
        super();
        this.step = step;
        this.direction = getRandomDirection();
    }

    protected void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    protected void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getEnergy() {
        return this.energy;
    }

    protected void loseEnergy(int energyLoss) {
        this.setEnergy(this.energy - energyLoss);
    }

    public void move() {
        switch (direction) {
        case UP:
            this.y = this.y - step;
            break;
        case RIGHT:
            this.x = this.x + step;
            break;
        case DOWN:
            this.y = this.y + step;
            break;
        case LEFT:
            this.x = this.x - step;
            break;
        }

    }

    public Direction getRandomDirection() {
        Direction newDirection = this.direction;
        Random rand = new Random();
        while (newDirection == direction)
            newDirection = Direction.values()[rand.nextInt(Direction.values().length)];
        return newDirection;
    }

    public Direction getOppositeDirection(Direction direction) {
        switch (direction) {
        case UP:
            return Direction.DOWN;
        case DOWN:
            return Direction.UP;
        case RIGHT:
            return Direction.LEFT;
        case LEFT:
            return Direction.RIGHT;
        default:
            return Direction.RIGHT;
        }
    }
    
    //Toda entidade que se move é obrigada a implementar colisões com todas as entidades do jogo
    public abstract void checkCollisionWith(Player player);
    public abstract void checkCollisionWith(Bullet bullet);
    public abstract void checkCollisionWith(Obstacle obstacle);
    public abstract void checkCollisionWith(Battlefield battlefield);
}