package view;

import javax.swing.JPanel;

import model.*;
import utils.Constants;
import java.util.List;
import java.util.LinkedList;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
  private List<Player> players = new LinkedList<Player>();
  private List<Obstacle> obstacles = new LinkedList<Obstacle>();
  private Battlefield battlefield;
  private List<Entity> entities = new LinkedList<Entity>();

  @Override
  public void run() {

    createEntites();
    while (true) {
      moveAll();
      checkCollisions();
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
    List<Entity> newEntities = new LinkedList<Entity>();
    newEntities.addAll(players);
    newEntities.addAll(obstacles);
    newEntities.add(battlefield);
    entities = newEntities;
  }

  private void createEntites() {
    this.battlefield = new Battlefield(10, 10, Constants.battlefieldWidth, Constants.battlefieldHeight);
    this.obstacles.add(new Obstacle(100, 100, "src/assets/obstacle1.jpg"));
    this.obstacles.add(new Obstacle(800, 800, "src/assets/obstacle2.jpg"));
    this.obstacles.add(new Obstacle(200, 500, "src/assets/obstacle3.jpg"));
    this.obstacles.add(new Obstacle(800, 200, "src/assets/obstacle4.jpg"));

    this.players.add(new Player(200, 300));
    this.players.add(new Player(300, 300));
    this.players.add(new Player(400, 300));
    this.players.add(new Player(500, 300));
    this.players.add(new Player(600, 300));

    this.players.add(new Player(200, 700));
    this.players.add(new Player(300, 700));
    this.players.add(new Player(400, 700));
    this.players.add(new Player(500, 700));
    this.players.add(new Player(600, 700));
  }

  private void moveAll() {
    for (int i = 0; i < players.size(); i++) {
      players.get(i).move(Constants.stepPlayer);
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
    }
  }

}