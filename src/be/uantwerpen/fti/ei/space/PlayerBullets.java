package be.uantwerpen.fti.ei.space;
// Imports used for the sounds.
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
/**
 * This class is used to control the player bullets created by the player ship and the sound.
 * @author Bavo Lesy
 * @version 1.0
 */
public abstract class PlayerBullets extends Entity{
    public abstract void Vis();
    int dyPb = 14; // Speed of the bullets shot by the player.
    protected SoundComponent sound; // Contains different sounds (data).
    protected SoundSystem updateSound; // Contains the logic to play the sounds.
    public PlayerBullets() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.sound = new SoundComponent("playerBullets");
        this.updateSound = new SoundSystem(sound);
    }
    /**
     * This method is used to play the sound.
     * @return sound of the player shooting.
     */
    public SoundSystem getUpdateSound(){
        return updateSound;
    } // Getter used to access the sound.
    public void movePlayerBullets(){
        playerBulletY -= dyPb;
    } // Move the player bullets vertically.
}
