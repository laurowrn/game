package model;

public abstract class MovingEntity extends Entity implements Movable {
    private Direction direction;

    @Override
    public void move() {
        switch (direction) {
            case UP:
                System.out.println("UP");
                break;
            case RIGHT:
                System.out.println("RIGHT");
                break;
            case DOWN:
                System.out.println("DOWN");
                break;
            case LEFT:
                System.out.println("LEFT");
                break;
        }
    }

    @Override
    public void changeDirection() {

    }
}
