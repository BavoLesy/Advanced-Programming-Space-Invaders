package be.uantwerpen.fti.ei.Java2D;
import be.uantwerpen.fti.ei.space.BossShip;
// Imports for graphics and sounds.
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
/**
 * This class is used to visualise the boss ship.
 * @author Bavo Lesy
 * @version 1.0
 */
public class Java2DBossShip extends BossShip {
    private final Java2DWorld g;
    public Java2DBossShip(Java2DWorld gr) throws UnsupportedAudioFileException, IOException, LineUnavailableException { this.g = gr; }
    /**
     * Visualises the boss ship.
     */
    @Override
    public void Vis(){ // Visualise Boss Ship.
        Graphics2D g2d = g.getGraphics2d();
        g2d.drawImage(g.BossShipSprite, bossShipX, bossShipY, null);
    }
}




