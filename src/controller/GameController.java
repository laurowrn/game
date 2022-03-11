package controller;

import java.util.LinkedList;

import model.Battlefield;
import model.Bullet;
import model.Entity;
import model.Obstacle;
import model.Player;
import utils.Constants;

public class GameController {
    private LinkedList<Player> players = new LinkedList<Player>();
    private LinkedList<Bullet> bullets = new LinkedList<Bullet>();
    private LinkedList<Obstacle> obstacles = new LinkedList<Obstacle>();
    private Battlefield battlefield;
    private LinkedList<Entity> entities = new LinkedList<Entity>();

    public LinkedList<Entity> getEntities() {
        return this.entities;
    }

    public void refreshEntities() {
        LinkedList<Entity> newEntities = new LinkedList<Entity>();
        newEntities.add(battlefield);
        newEntities.addAll(players);
        newEntities.addAll(obstacles);
        newEntities.addAll(bullets);
        this.entities = newEntities;
    }

    public void createEntites() {

        this.battlefield = new Battlefield(10, 10, Constants.battlefieldWidth, Constants.battlefieldHeight);
        this.obstacles.add(new Obstacle(100, 100));
        this.obstacles.add(new Obstacle(800, 800));
        this.obstacles.add(new Obstacle(200, 500));
        this.obstacles.add(new Obstacle(800, 200));

        this.players.add(new Player(200, 300, 0));
        this.players.add(new Player(300, 300, 1));
        this.players.add(new Player(400, 300, 2));
        this.players.add(new Player(500, 300, 3));
        this.players.add(new Player(600, 300, 4));

        this.players.add(new Player(200, 700, 5));
        this.players.add(new Player(300, 700, 6));
        this.players.add(new Player(400, 700, 7));
        this.players.add(new Player(500, 700, 8));
        this.players.add(new Player(600, 700, 9));

    }

    public void allPlayersShoot() {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).shoot(this.bullets);
        }
    }

    public void moveAll() {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).move(Constants.playerStep);
        }
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).move(Constants.bulletStep);
        }
        refreshEntities();
    }

    public void checkCollisions() {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).checkCollisionWith(battlefield);
            for (int j = 0; j < obstacles.size(); j++) {
                players.get(i).checkCollisionWith(obstacles.get(j));
            }
            for (int j = 0; j < players.size(); j++) {
                if (j != i) {
                    players.get(i).checkCollisionWith(players.get(j));
                }
            }
            for (int j = 0; j < bullets.size(); j++) {
                players.get(i).checkCollisionWith(bullets.get(j));
            }
        }
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).checkCollisionWith(battlefield);
            for (int j = 0; j < obstacles.size(); j++) {
                bullets.get(i).checkCollisionWith(obstacles.get(j));
            }
        }

    }

    public void removeDeadMovingEntities() {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getEnergy() <= 0) {
                players.remove(i);
            }
        }
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).getEnergy() <= 0) {
                bullets.remove(i);
            }
        }
    }
}
