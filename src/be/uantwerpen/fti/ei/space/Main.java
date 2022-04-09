package be.uantwerpen.fti.ei.space;
import be.uantwerpen.fti.ei.Java2D.*;
// Imports used for the sounds.
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
/**
 * This class is used to start the game.
 * @author Bavo Lesy
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AFact a = new Java2DFact();
        Game g = new Game(a);
        g.Start();
    }

}


