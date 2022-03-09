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
    public void changeToRandomDirection() {
    	Direction newDirection = direction;
    	while(newDirection == direction)
			newDirection = Direction.values()[new Random().nextInt(Direction.values().length)] ;
		direction = newDirection;
    }

    public void checkCollisionWith(MovingEntity entity){
        if(this.intersects(entity)){
            this.changeToRandomDirection();
            entity.changeToRandomDirection();
        }
    }

    public void checkCollisionWith(Obstacle obstacle){
        if(this.intersects(obstacle)){
            this.changeToRandomDirection();
        }
      }

    public void checkCollisionWith(Battlefield battlefield){
        if(this.x <= battlefield.getX() || 
           this.y <= battlefield.getY() ||
           this.x + this.width >= battlefield.getX() + battlefield.getWidth() ||
           this.y + this.height >= battlefield.getY() + battlefield.getHeight()){
            
            this.changeToRandomDirection();
        }
    }
}
