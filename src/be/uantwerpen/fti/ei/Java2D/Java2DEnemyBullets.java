package be.uantwerpen.fti.ei.Java2D;
import be.uantwerpen.fti.ei.space.EnemyBullets;
// Imports for graphics and sounds.
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
/**
 * This class is used to visualise the enemy bullets.
 * @author Bavo Lesy
 * @version 1.0
 */
public class Java2DEnemyBullets extends EnemyBullets {
    private final Java2DWorld g;
    public Java2DEnemyBullets(Java2DWorld gr) throws UnsupportedAudioFileException, IOException, LineUnavailableException { this.g = gr; }
    /**
     * Visualises the enemy bullets.
     */
    @Override
    public void Vis() {
        Graphics2D g2d = g.getGraphics2d();
        if (bossBulletType == 1) { // Blue bullets.
            g2d.drawImage(g.BlueBulletSprite, enemyBulletX, enemyBulletY, null);
        } else if (bossBulletType == 2) { // Boss Red bullets.
            g2d.drawImage(g.BossRedBulletSprite, enemyBulletX ,enemyBulletY, null);
        } else {
            g2d.drawImage(g.EnemyBullets1Sprite, enemyBulletX , enemyBulletY, null);
        }
    }
}
