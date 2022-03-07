package view;

import javax.swing.JFrame;
import utils.Constants;

public class GameFrame extends JFrame {

    public GameFrame() {
        GamePanel gamePanel = new GamePanel();
        Thread panelThread = new Thread(gamePanel);
        panelThread.start();

        this.setContentPane(gamePanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Constants.windowWidth, Constants.windowHeight);
        this.setVisible(true);
    }

}