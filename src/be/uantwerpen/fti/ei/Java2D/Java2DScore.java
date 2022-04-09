package be.uantwerpen.fti.ei.Java2D;
import be.uantwerpen.fti.ei.space.*;
// Imports for graphics and sounds.
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
/**
 * This class is used to visualise the score, lives, boss lives and level.
 * @author Bavo Lesy
 * @version 1.0
 */
public class Java2DScore extends Score {
    private final Java2DWorld g;
    JLabel info;
    public Java2DScore(Java2DWorld gr) throws UnsupportedAudioFileException, IOException, LineUnavailableException { this.g = gr; }
    /**
     * Visualises the score,lives, boss lives and level.
     */
    @Override
    public void Vis(){ Graphics2D g2d = g.getGraphics2d(); // Display different statuses on the statusbar.

        for(int i  = 0; i < lives; i++){
            g2d.drawImage(g.HearthSprite, 5 +i * 50, 715, null);
        }
        info = g.getStatusbar();
        if(level == bossLevel){
            info.setText("Score: " + score + "              Lives: " + lives +  "               Boss Lives:     " + bossLives +"          Level: " + level + "                Press ENTER to toggle pause");
            for(int i  = 0; i < bossLives; i++){
                g2d.drawImage(g.RedLineSprite, 475 + i * 20, 45, null);
            }
            g2d.drawImage(g.BossHealthBarSprite, 700, 10, null);
        }else if(scorePaused){
            info.setText("Score: " + score + "              Lives: " + lives + "                Level:      " + level + "                Press ENTER to go to the next level");
        }
        else{
            info.setText("Score: " + score + "              Lives: " + lives + "                Level:      " + level + "                Press ENTER to toggle pause");
        }


    }
}

