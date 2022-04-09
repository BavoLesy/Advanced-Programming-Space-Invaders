package be.uantwerpen.fti.ei.Java2D;
import be.uantwerpen.fti.ei.space.*;
// Imports for graphics and sounds.
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
/**
 * This class is used to visualise the player ship.
 * @author Bavo Lesy
 * @version 1.0
 */
public class Java2DPlayerShip extends PlayerShip {
    private final Java2DWorld g;
    public Java2DPlayerShip(Java2DWorld gr) throws UnsupportedAudioFileException, IOException, LineUnavailableException { this.g = gr; }
    /**
     * Visualises the player ship.
     */
    @Override
    public void Vis(){ // Visualise Player Ship.
        Graphics2D g2d = g.getGraphics2d();
        g2d.drawImage(g.BackgroundSprite,0, 0, null);
        if(startScreen){
            g2d.drawImage(g.StartScreenSprite,320, 130, null);
        }
        g2d.drawImage(g.PlayerShipSprite, playerShipX , playerShipY, null);
        if(invicibiltyPlayer){
            g2d.drawImage(g.ShieldSprite, playerShipX + playerShipWidth/2 - shieldWidth/2, playerShipY, null);
        }
    }
}
