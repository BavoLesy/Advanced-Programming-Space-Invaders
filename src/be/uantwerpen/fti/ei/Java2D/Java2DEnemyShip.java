package be.uantwerpen.fti.ei.Java2D;
import be.uantwerpen.fti.ei.space.EnemyShip;
import java.awt.*;
/**
 * This class is used to visualise the enemy ships.
 * @author Bavo Lesy
 * @version 1.0
 */
public class Java2DEnemyShip extends EnemyShip {
    private final Java2DWorld g;
    public Java2DEnemyShip(Java2DWorld g) { //constructor
        this.g = g;
    }
    /**
     * Visualises the enemy ships.
     */
    @Override
    public void Vis() { // Visualise Enemy ship.
        Graphics2D g2d = g.getGraphics2d();
        g2d.drawImage(g.EnemyShipSprite, enemyShipX , enemyShipY, null);
    }
}

