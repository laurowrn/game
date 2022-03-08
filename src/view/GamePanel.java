package view;

import javax.swing.JPanel;

import model.Battlefield;
import model.Obstacle;
import utils.Constants;

import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    @Override
    public void run() {
        System.out.println(this.getWidth());
        // Image image = createImage(this.getWidth(), this.getHeight());
        // Battlefield battlefield = new Battlefield(10, 10, Constants.battlefieldWidth,
        // Constants.battlefieldHeight);
        // battlefield.draw(image.getGraphics());
        // System.out.println("teste");

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Battlefield battlefield = new Battlefield(10, 10, Constants.battlefieldWidth, Constants.battlefieldHeight);
        Obstacle obstacle1 = new Obstacle(100, 100, "src/assets/obstacle1.jpg");
        Obstacle obstacle2 = new Obstacle(800, 800, "src/assets/obstacle2.jpg");
        Obstacle obstacle3 = new Obstacle(200, 500, "src/assets/obstacle3.jpg");
        Obstacle obstacle4 = new Obstacle(800, 200, "src/assets/obstacle4.jpg");
        obstacle1.draw(g);
        obstacle2.draw(g);
        obstacle3.draw(g);
        obstacle4.draw(g);
        battlefield.draw(g);
    }

}
