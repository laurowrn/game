package model;

public interface Movable {
    public void move(int step);

    public Direction getRandomDirection();

    public Direction getOppositeDirection(Direction direction);
}