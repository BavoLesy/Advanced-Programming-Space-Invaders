package be.uantwerpen.fti.ei.TXT;


import be.uantwerpen.fti.ei.space.*;

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
public class TXTFact extends AFact {

    public PlayerShip getPlayerShip() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        return new TXTPlayerShip();
    }

    public EnemyShip getEnemyShip() {
        return new TXTEnemyShip();
    }

    @Override
    public void resizeSprites(int screenWidth, int screenHeight) { }
    @Override
    public void render() { }
    public Input getInput() {return null;}
    @Override
    public EnemyBullets getEnemyBullets() {
        return null;
    }
    @Override
    public PlayerBullets getPlayerBullets() {
        return null;
    }
    @Override
    public Bonus getBonus() {
        return null;
    }
    @Override
    public Score getScore(){
        return null;
    }
    @Override
    public GameOver getGameOver(){
        return null;
    }
    @Override
    public SpecialBullets getSpecialBullets(){return null;}
    @Override
    public BossShip getBossShip(){return null;}
    @Override
    public Explosions getExplosions(){return null;}
    @Override
    public Victory getVictory(){return null;}
}


