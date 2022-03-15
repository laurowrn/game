package model;

public interface Collidible {
    public abstract void checkCollisionWith(Player player);

    public abstract void checkCollisionWith(Bullet bullet);

    public abstract void checkCollisionWith(Obstacle obstacle);

    public abstract void checkCollisionWith(Battlefield battlefield);
}
