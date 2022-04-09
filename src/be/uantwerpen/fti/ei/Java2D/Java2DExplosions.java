package be.uantwerpen.fti.ei.Java2D;
import be.uantwerpen.fti.ei.space.Explosions;
// Imports for graphics and sounds.
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
/**
 * This class is used to visualise the explosions.
 * @author Bavo Lesy
 * @version 1.0
 */
public class Java2DExplosions extends Explosions {
    private final Java2DWorld g;
    public Java2DExplosions(Java2DWorld gr) throws UnsupportedAudioFileException, IOException, LineUnavailableException { this.g = gr; }
    /**
     * Visualises the explosions.
     */
    @Override
    public void Vis() { // Visualise explosion.
        Graphics2D g2d = g.getGraphics2d();
        g2d.drawImage(g.ExplosionSprite,explosionsX,explosionsY,null);
    }
}
