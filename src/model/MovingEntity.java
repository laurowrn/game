package model;

import java.util.Random;

public abstract class MovingEntity extends Entity implements Movable {
    private Direction direction = Direction.RIGHT;
    private int step;

     public MovingEntity(int step) {
		super();
		this.step = step;
	}
    
    @Override
    public void move() {
        switch (direction) {
            case UP:
                System.out.println("UP");
                this.y = this.y - step;
                break;
            case RIGHT:
                System.out.println("RIGHT");
                this.x = this.x + step;
                break;
            case DOWN:
                System.out.println("DOWN");
                this.y = this.y + step;
                break;
            case LEFT:
                System.out.println("LEFT");
                this.x = this.x - step;
                break;
        }
    }

    @Override
    public void changeDirection() {
    	Direction newDirection = direction;
    	while(newDirection == direction)
			newDirection = Direction.values()[new Random().nextInt(Direction.values().length)] ;
		direction = newDirection;
    }
}
