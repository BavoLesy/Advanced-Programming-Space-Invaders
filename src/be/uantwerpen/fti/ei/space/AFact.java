package be.uantwerpen.fti.ei.space;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
/**
 * This class is used to dynamically generate other classes.
 * A factory is a class that makes other classes, a kind of 'template'.
 * This way you can make different variations of the same product (TXT visualisation and J2D visualisation).
 * {@link} https://refactoring.guru/design-patterns/abstract-factory.
 * @author Bavo Lesy
 * @version 1.0
 */
public abstract class AFact {
    // Different getters used to create the game entities used.
    public abstract PlayerShip getPlayerShip() throws UnsupportedAudioFileException, IOException, LineUnavailableException;
    public abstract PlayerBullets getPlayerBullets() throws UnsupportedAudioFileException, IOException, LineUnavailableException;
    public abstract SpecialBullets getSpecialBullets() throws UnsupportedAudioFileException, IOException, LineUnavailableException;
    public abstract EnemyShip getEnemyShip();
    public abstract BossShip getBossShip() throws UnsupportedAudioFileException, IOException, LineUnavailableException;
    public abstract EnemyBullets getEnemyBullets() throws UnsupportedAudioFileException, IOException, LineUnavailableException;
    public abstract Explosions getExplosions() throws UnsupportedAudioFileException, IOException, LineUnavailableException;
    public abstract Bonus getBonus();
    public abstract Score getScore() throws UnsupportedAudioFileException, IOException, LineUnavailableException;
    public abstract GameOver getGameOver() throws UnsupportedAudioFileException, IOException, LineUnavailableException;
    public abstract Victory getVictory() throws UnsupportedAudioFileException, IOException, LineUnavailableException;
    public abstract Input getInput();
    public abstract void resizeSprites(int screenWidth, int screenHeight) throws IOException;
    public abstract void render();

}
