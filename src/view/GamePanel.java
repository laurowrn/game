package view;

import javax.swing.JPanel;

import model.*;
import utils.Constants;
import java.util.List;
import java.util.LinkedList;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    private List<Entity> entities = new LinkedList<Entity>();

    @Override
    public void run() {
      this.entities.add(new Battlefield(10, 10, Constants.battlefieldWidth, Constants.battlefieldHeight));
      this.entities.add(new Obstacle(100, 100, "src/assets/obstacle1.jpg"));
      this.entities.add(new Obstacle(800, 800, "src/assets/obstacle2.jpg"));
      this.entities.add(new Obstacle(200, 500, "src/assets/obstacle3.jpg"));
      this.entities.add(new Obstacle(800, 200, "src/assets/obstacle4.jpg"));
      this.entities.add(new Player(300, 300));
      this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int entitiesListSize = entities.size();
        for(int i = 0; i<entitiesListSize; i++){
          entities.get(i).draw(g);
        }
      
    }

}
