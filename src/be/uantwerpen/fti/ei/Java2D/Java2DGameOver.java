package be.uantwerpen.fti.ei.Java2D;
import be.uantwerpen.fti.ei.space.*;
// Imports for graphics and sounds.
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
/**
 * This class is used to visualise the game over screen.
 * @author Bavo Lesy
 * @version 1.0
 */
public class Java2DGameOver extends GameOver {
    private final Java2DWorld g;
    JLabel info;
    public Java2DGameOver(Java2DWorld gr) throws UnsupportedAudioFileException, IOException, LineUnavailableException { this.g = gr; }
    /**
     * Visualises the game over screen.
     */
    @Override
    public void Vis() { // Visualise the GameOver screen and display the score on the statusbar.
        Graphics2D g2d = g.getGraphics2d();
        info = g.getStatusbar();
        info.setText("Score: " + score + "              Lives: " + lives + "               Level: " + level + "               Press ENTER to retry");
        g2d.drawImage(g.GameOverSprite, 0, 0, null);
    }
}
