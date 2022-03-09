package view;

import javax.swing.JPanel;

import model.*;
import model.MovingEntity;
import utils.Constants;
import java.util.List;
import java.util.LinkedList;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    private List<MovingEntity> movingEntities = new LinkedList<MovingEntity>();
    private List<Entity> staticEntities = new LinkedList<Entity>();

    @Override
    public void run() {
    
    createEntites();
     while(true){
        moveAll();
        checkCollisions();
        this.repaint(); 
        try{
          Thread.sleep(Constants.refreshTime);
        }catch(Exception e){
        }
      }
      
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i = 0; i<staticEntities.size(); i++){
          staticEntities.get(i).draw(g);
        }
        for(int i = 0; i<movingEntities.size(); i++){
          movingEntities.get(i).draw(g);
        }
    }

    // Funcoes de organização --------------------------------------------
    private void createEntites(){
      this.staticEntities.add(new Battlefield(10, 10, Constants.battlefieldWidth, Constants.battlefieldHeight));
      this.staticEntities.add(new Obstacle(100, 100, "src/assets/obstacle1.jpg"));
      this.staticEntities.add(new Obstacle(800, 800, "src/assets/obstacle2.jpg"));
      this.staticEntities.add(new Obstacle(200, 500, "src/assets/obstacle3.jpg"));
      this.staticEntities.add(new Obstacle(800, 200, "src/assets/obstacle4.jpg"));
        
      this.movingEntities.add(new Player(300, 300));
      this.movingEntities.add(new Player(500, 300));
    }
    
    private void moveAll(){
        for(int i = 0; i<movingEntities.size(); i++){
          movingEntities.get(i).move();
        }
    }

    private void checkCollisions(){
        for(int i = 0; i<movingEntities.size(); i++){
            for(int j = 1; j<staticEntities.size(); j++){                                    movingEntities.get(i).checkCollisionWith((Obstacle)staticEntities.get(j));
          }
        }
      }
}