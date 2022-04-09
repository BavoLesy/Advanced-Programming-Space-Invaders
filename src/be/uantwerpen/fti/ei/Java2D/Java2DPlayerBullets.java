package be.uantwerpen.fti.ei.Java2D;
import be.uantwerpen.fti.ei.space.PlayerBullets;
// Imports for graphics and sounds.
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
/**
 * This class is used to visualise the player bullets.
 * @author Bavo Lesy
 * @version 1.0
 */
public class Java2DPlayerBullets extends PlayerBullets {
    private final Java2DWorld g;
    public Java2DPlayerBullets(Java2DWorld gr) throws UnsupportedAudioFileException, IOException, LineUnavailableException { this.g = gr; }
    /**
     * Visualises the player bullets.
     */
    @Override
    public void Vis(){ // Visualise Player Bullets.
        Graphics2D g2d = g.getGraphics2d();
        g2d.drawImage(g.PlayerBullets1Sprite, playerBulletX , playerBulletY, null);
    }
}
