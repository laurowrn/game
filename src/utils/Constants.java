package utils;

public class Constants {
    // WINDOW SIZE CONSTANTS
    public final static int windowWidth = 1800;
    public final static int windowHeight = 1200;

    // BATTLEFIELD SIZE CONSTANTS
    public final static int battlefieldWidth = (int) Math.floor(0.7 * Constants.windowWidth);
    public final static int battlefieldHeight = (int) Math.floor(0.9 * Constants.windowHeight);

    // PLAYER CONSTANTS
    public final static int playerStep = 1;
    public final static int playerRicochetStep = 3;
    public final static int playerEnergy = 50;

    // GAME CONSTANTS
    public final static int refreshTime = 10;
    public final static int collisioWithPlayerLoss = 1;
    public final static int collisioWithObstacleLoss = 2;
}
