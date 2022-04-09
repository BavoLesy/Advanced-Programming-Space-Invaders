package be.uantwerpen.fti.ei.space;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
/**
 * This class is used to control the enemy bullets created by enemy ships and the sound.
 * @author Bavo Lesy
 * @version 1.0
 */
public abstract class EnemyBullets extends Entity{
    public abstract void Vis();
    int dyEb = 7; // Speed of the bullets shot by the enemy.
    int dyBb = 7; // Speed of the Big Boss Bullet.
    int dyBlueB = 7; // Speed of the blue Boss bullets.
    int dxBlueB = 6;
    int dxBb = 4; // Horizontal speed of the Boss Bullet.
    protected SoundComponent sound; // Contains different sounds (data).
    protected SoundSystem updateSound; // Contains the logic to play the sounds.
    public EnemyBullets() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.sound = new SoundComponent("enemyBullets");
        this.updateSound = new SoundSystem(sound);
    }
    /**
     * This method is used to play the sound.
     * @return sound of the enemy shooting.
     */
    public SoundSystem getUpdateSound(){
        return updateSound;
    } // Getter used to access the sound.
    /**
     * This method moves the bullets down.
     */
    public void moveEnemyBullets(){
        enemyBulletY += dyEb;
    } // Move the normal enemy bullets.
    /**
     * This method moves the big boss bullets.
     */
    public void moveBossBullets(){  // Move the Red Boss bullets.
        enemyBulletY += dyBb;
        if(enemyBulletX <= playerShipX+playerShipWidth/2 - enemyBulletWidth/2){ // Follow the middle of the Playership like a heatseeking missile.
            enemyBulletX+= dxBb;
        }else {
            enemyBulletX -= dxBb;
        }
    }
    /**
     * This method moves the blue bullets.
     */
    public void moveBlueBullets(){ // Move the blue bullets.
        enemyBulletY += dyBlueB;
        if(enemyBulletDirection == 1 && enemyBulletX < 1440){   // If the bullet is not out of bounds and moving right: keep moving right.
            enemyBulletX += dxBlueB;
        }else if (enemyBulletDirection == 2 && enemyBulletX > 0){ // If the bullet is not out of bounds and moving left: keep moving left.
            enemyBulletX -= dxBlueB;
        }else if(enemyBulletDirection == 1){ // If the bullet is out of bounds and moving right: start moving left.
            enemyBulletX -= dxBlueB;
            enemyBulletDirection = 2;
        }else{      // If the bullet is out of bounds and moving left: start moving right.
            enemyBulletX += dxBlueB;
            enemyBulletDirection = 1;
        }
    }
}
