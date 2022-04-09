package be.uantwerpen.fti.ei.Java2D;
import be.uantwerpen.fti.ei.space.SpecialBullets;
// Imports for graphics and sounds.
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
/**
 * This class is used to visualise the special bullets.
 * @author Bavo Lesy
 * @version 1.0
 */
public class Java2DSpecialBullets extends SpecialBullets {
    private final Java2DWorld g;
    public Java2DSpecialBullets(Java2DWorld gr) throws UnsupportedAudioFileException, IOException, LineUnavailableException { this.g = gr;}
    /**
     * Visualises the special bullets.
     */
    @Override
    public void Vis(){ // Display the special Freezing Bullets (Room for expansion).
        Graphics2D g2d = g.getGraphics2d();
        g2d.drawImage(g.FreezeBulletsSprite, specialBulletX, specialBulletY, null);
    }
}



