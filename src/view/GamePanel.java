package view;

import javax.swing.JPanel;

import model.*;
import utils.Constants;
import java.util.List;
import java.util.LinkedList;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
  private LinkedList<Player> players = new LinkedList<Player>();
  private LinkedList<Bullet> bullets = new LinkedList<Bullet>();
  private LinkedList<Obstacle> obstacles = new LinkedList<Obstacle>();
  private Battlefield battlefield;
  private LinkedList<Entity> entities = new LinkedList<Entity>();

  @Override
  public void run() {

    createEntites();
    while (true) {
      moveAll();
      allPlayersShoot();
      checkCollisions();
      removeMovingEntities();
      this.repaint();

      try {
        Thread.sleep(Constants.refreshTime);
      } catch (Exception e) {
      }

    }

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (int i = 0; i < entities.size(); i++) {
      entities.get(i).draw(g);
    }
  }

  // Funcoes de organização --------------------------------------------

  private void refreshEntities() {
    LinkedList<Entity> newEntities = new LinkedList<Entity>();
    newEntities.addAll(players);
    newEntities.addAll(obstacles);
    newEntities.addAll(bullets);
    newEntities.add(battlefield);
    this.entities = newEntities;
  }

  private void createEntites() {
    this.battlefield = new Battlefield(10, 10, Constants.battlefieldWidth, Constants.battlefieldHeight);
    this.obstacles.add(new Obstacle(100, 100, "src/assets/obstacle1.jpg"));
    this.obstacles.add(new Obstacle(800, 800, "src/assets/obstacle2.jpg"));
    this.obstacles.add(new Obstacle(200, 500, "src/assets/obstacle3.jpg"));
    this.obstacles.add(new Obstacle(800, 200, "src/assets/obstacle4.jpg"));

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

  private void allPlayersShoot() {
    for (int i = 0; i < players.size(); i++) {
      players.get(i).shoot(this.bullets);
    }
  }

  private void moveAll() {
    for (int i = 0; i < players.size(); i++) {
      players.get(i).move(Constants.playerStep);
    }
    for (int i = 0; i < bullets.size(); i++) {
      bullets.get(i).move(Constants.bulletStep);
    }
    refreshEntities();
  }

  private void checkCollisions() {
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

  }

  private void removeMovingEntities() {
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