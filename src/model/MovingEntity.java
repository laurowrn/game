package model;

import java.util.Random;
import java.util.Date;
import utils.Constants;

public abstract class MovingEntity extends Entity implements Movable {
    protected Direction direction = Direction.RIGHT;
    protected int step;

    public MovingEntity(int step) {
        super();
        this.step = step;
        this.direction = getRandomDirection();
    }

    @Override
    public void move(int step) {
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

    @Override
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

    protected void setDirection(Direction direction) {
        this.direction = direction;
    }

    protected Direction getDirection() {
        return direction;
    }

}
