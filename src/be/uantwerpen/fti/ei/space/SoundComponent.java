package be.uantwerpen.fti.ei.space;
// Imports used for the sounds.
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
/**
 * This class is used to play the sound in a data oriented way.
 * In the component all the data is stored (sound sources).
 * @author Bavo Lesy
 * @version  1.0
 */
public class SoundComponent {
    private File soundFile;
    public SoundComponent(String type) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        switch(type){
            case "playerBullets": this.soundFile = new File("src/be/uantwerpen/fti/ei/sounds/shoot.wav");
                break;
            case "playerHit": this.soundFile = new File("src/be/uantwerpen/fti/ei/sounds/invaderkilled.wav");
                break;
            case "explosion": this.soundFile = new File("src/be/uantwerpen/fti/ei/sounds/explosion.wav");
                break;
            case "bonus": this.soundFile = new File("src/be/uantwerpen/fti/ei/sounds/Powerup.wav");
                break;
            case "freezeBeam": this.soundFile = new File("src/be/uantwerpen/fti/ei/sounds/LaserCannon.wav");
                break;
            case "freezeBoss": this.soundFile = new File("src/be/uantwerpen/fti/ei/sounds/Freezing.wav");
                break;
            case "victory": this.soundFile = new File("src/be/uantwerpen/fti/ei/sounds/victory.wav");
                break;
            case "gameOver": this.soundFile = new File("src/be/uantwerpen/fti/ei/sounds/GameOver.wav");
                break;
            case "enemyBullets": this.soundFile = new File("src/be/uantwerpen/fti/ei/sounds/enemyShoot.wav");
                break;
        }
    }

    /**
     * This methods returns the sound file.
     * @return the sound file
     */
    public File getSoundFile(){
        return this.soundFile;
    }
}
