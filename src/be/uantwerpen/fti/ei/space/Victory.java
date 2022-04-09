package be.uantwerpen.fti.ei.space;
// Imports used for the sounds.
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
/**
 * This class is used to control the victory screen and the sound.
 * @author Bavo Lesy
 * @version 1.0
 */
public abstract class Victory extends Entity{
    public abstract void Vis();
    protected SoundComponent sound; // Contains different sounds (data).
    protected SoundSystem updateSound; // Contains the logic to play the sounds.
    public Victory() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.sound = new SoundComponent("victory");
        this.updateSound = new SoundSystem(sound);
    }
    /**
     * This method is used to play the sound.
     * @return sound of the game being won.
     */
    public SoundSystem getUpdateSound(){
        return updateSound;
    } // Getter used to access the sound.
}
