package be.uantwerpen.fti.ei.Java2D;
import be.uantwerpen.fti.ei.space.*;
// Imports for sounds.
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
public class Java2DFact extends AFact {
    // Getters for the game entities used to visualise them in Java2D.
    Java2DWorld J2D = new Java2DWorld();

    @Override
    public PlayerShip getPlayerShip() throws UnsupportedAudioFileException, IOException, LineUnavailableException { return new Java2DPlayerShip(J2D); }
    @Override
    public PlayerBullets getPlayerBullets() throws UnsupportedAudioFileException, IOException, LineUnavailableException { return new Java2DPlayerBullets(J2D);}
    @Override
    public SpecialBullets getSpecialBullets() throws UnsupportedAudioFileException, IOException, LineUnavailableException { return new Java2DSpecialBullets(J2D);}
    @Override
    public EnemyShip getEnemyShip() { return new Java2DEnemyShip(J2D); }
    @Override
    public BossShip getBossShip() throws UnsupportedAudioFileException, IOException, LineUnavailableException {return new Java2DBossShip(J2D);}
    @Override
    public EnemyBullets getEnemyBullets() throws UnsupportedAudioFileException, IOException, LineUnavailableException { return new Java2DEnemyBullets(J2D); }
    @Override
    public Explosions getExplosions() throws UnsupportedAudioFileException, IOException, LineUnavailableException {return new Java2DExplosions(J2D);}
    @Override
    public Bonus getBonus() { return new Java2DBonus(J2D); }
    @Override
    public Victory getVictory() throws UnsupportedAudioFileException, IOException, LineUnavailableException {return new Java2DVictory(J2D);}
    @Override
    public Input getInput() { return new Java2DInput(J2D); }
    @Override
    public Score getScore() throws UnsupportedAudioFileException, IOException, LineUnavailableException { return new Java2DScore(J2D);}
    @Override
    public GameOver getGameOver() throws UnsupportedAudioFileException, IOException, LineUnavailableException { return new Java2DGameOver(J2D);}
    @Override
    public void resizeSprites(int screenWidth, int screenHeight) throws IOException { J2D.resizeSprites(screenWidth,screenHeight); }
    @Override
    public void render(){ J2D.render(); }
}
