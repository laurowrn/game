package view;

import javax.swing.JPanel;
import controller.GameController;
import utils.Constants;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
  private GameController gameController = new GameController();

  @Override
  public void run() {
    gameController.createEntites();
    while (true) {
      gameController.moveAll();
      gameController.allPlayersShoot();
      gameController.checkCollisions();
      gameController.removeDeadMovingEntities();
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
    g.setColor(Color.gray);
    g.fillRect(0, 0, this.getWidth(), this.getHeight());
    for (int i = 0; i < gameController.getEntities().size(); i++) {
      gameController.getEntities().get(i).draw((Graphics2D) g, this);
    }
  }

}