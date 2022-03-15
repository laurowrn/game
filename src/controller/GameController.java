package controller;

import java.util.LinkedList;

import model.MovingEntity;
import model.StaticEntity;
import model.Battlefield;
import model.Bullet;
import model.Entity;
import model.Obstacle;
import model.Player;

public class GameController {

    // private LinkedList<Entity> entities = new LinkedList<Entity>();

    private LinkedList<MovingEntity> movingEntities = new LinkedList<MovingEntity>();
    private LinkedList<StaticEntity> staticEntities = new LinkedList<StaticEntity>();

    public LinkedList<Entity> getEntities() {
        LinkedList<Entity> entities = new LinkedList<Entity>();
        entities.addAll(staticEntities);
        entities.addAll(movingEntities);
        return entities;
    }

    public void createEntites() {
        this.staticEntities.add(new Battlefield(10, 10));
        this.staticEntities.add(new Obstacle(100, 100));
        this.staticEntities.add(new Obstacle(600, 100));
        this.staticEntities.add(new Obstacle(100, 500));
        this.staticEntities.add(new Obstacle(600, 500));
        this.staticEntities.add(new Obstacle(350, 350));

        this.movingEntities.add(new Player(250, 150, 0));
        this.movingEntities.add(new Player(250, 220, 1));
        this.movingEntities.add(new Player(250, 290, 2));
        this.movingEntities.add(new Player(250, 360, 3));
        this.movingEntities.add(new Player(250, 430, 4));

        this.movingEntities.add(new Player(480, 150, 5));
        this.movingEntities.add(new Player(480, 220, 6));
        this.movingEntities.add(new Player(480, 290, 7));
        this.movingEntities.add(new Player(480, 360, 8));
        this.movingEntities.add(new Player(480, 430, 9));

    }

    public void allPlayersShoot() {
        for (int i = 0; i < movingEntities.size(); i++) {
            Class<? extends Entity> EntityClass = movingEntities.get(i).getClass();
            if (EntityClass == Player.class) {
                ((Player) movingEntities.get(i)).shoot(this.movingEntities);
            }
        }
    }

    public void moveAll() {
        for (int i = 0; i < movingEntities.size(); i++) {
            movingEntities.get(i).move();
        }
    }

    public void checkCollisions() {
        for (int i = 0; i < movingEntities.size(); i++) {
            for (int j = 0; j < staticEntities.size(); j++) {
                Class<? extends Entity> EntityClass = staticEntities.get(j).getClass();
                if (EntityClass == Battlefield.class) {
                    movingEntities.get(i).checkCollisionWith((Battlefield) staticEntities.get(j));
                } else if (EntityClass == Obstacle.class) {
                    movingEntities.get(i).checkCollisionWith((Obstacle) staticEntities.get(j));
                }
            }

            for (int j = i + 1; j < movingEntities.size(); j++) {
                Class<? extends Entity> EntityClass = movingEntities.get(j).getClass();
                if (EntityClass == Player.class) {
                    movingEntities.get(i).checkCollisionWith((Player) movingEntities.get(j));
                } else if (EntityClass == Bullet.class) {
                    movingEntities.get(i).checkCollisionWith((Bullet) movingEntities.get(j));
                }
            }
        }
    }

    public void removeDeadMovingEntities() {

        for (int i = 0; i < movingEntities.size(); i++) {
            if (movingEntities.get(i).getEnergy() <= 0) {
                movingEntities.remove(i);
            }
        }
    }
}
