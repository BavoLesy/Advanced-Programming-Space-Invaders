package be.uantwerpen.fti.ei.Java2D;
import be.uantwerpen.fti.ei.space.Victory;
// Imports for graphics and sounds.
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
/**
 * This class is used to visualise the victory screen.
 * @author Bavo Lesy
 * @version 1.0
 */
public class Java2DVictory extends Victory {
    private final Java2DWorld g;
    JLabel info;
    public Java2DVictory(Java2DWorld gr) throws UnsupportedAudioFileException, IOException, LineUnavailableException { this.g = gr; }
    /**
     * Visualises the victory screen.
     */
    @Override
    public void Vis() { // Visualise the Victory screen and the statusbar.
        Graphics2D g2d = g.getGraphics2d();
        info = g.getStatusbar();
        info.setText("Score: " + score + "              Lives: " + lives + "               Level: " + level + "               Press ENTER to retry"); // Statusbar.
        g2d.drawImage(g.EndingSprite,0,0,null); // Ending screen.
    }
}
