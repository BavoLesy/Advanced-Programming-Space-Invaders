package be.uantwerpen.fti.ei.TXT;

import be.uantwerpen.fti.ei.space.PlayerShip;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
/**
 * This class is used to visualise the player ship.
 * @author Bavo Lesy
 * @version 1.0
 */
public class TXTPlayerShip extends PlayerShip {

    public TXTPlayerShip() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    }
    public void Vis() {
        System.out.println("PlayerShip");
    }
}


