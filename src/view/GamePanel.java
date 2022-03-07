package view;

import javax.swing.JPanel;

import model.Battlefield;
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
        battlefield.draw(g);
    }

}
