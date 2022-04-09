package be.uantwerpen.fti.ei.space;
// Imports used for the sounds.
import javax.sound.sampled.*;
import java.io.IOException;
/**
 * This class is used to play the sound in a data oriented way.
 * In the System (updater) all the logic to play the sound is stored.
 * @author Bavo Lesy
 * @version  1.0
 */
public class SoundSystem {
    private final SoundComponent sound;
    public SoundSystem(SoundComponent sound) {
        this.sound = sound;
    }
    /**
     * This method plays the sound.
     * @throws IOException If the sound file is not read correctly.
     * @throws UnsupportedAudioFileException If the audio file is unsupported.
     * @throws LineUnavailableException if the requested line is currently unavailable.
     */
    public void queueSound() throws IOException, UnsupportedAudioFileException, LineUnavailableException {      // Updates the sound when necessary
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(sound.getSoundFile());   // Make a new audiostream with the soundFile.
        Clip clipPlayerBullets = AudioSystem.getClip(); // Make a new clip.
        clipPlayerBullets.open(audioStream); // Open the audiostream with the clip.
        FloatControl gainControl = (FloatControl) // Control the volume.
        clipPlayerBullets.getControl(FloatControl.Type.MASTER_GAIN); // Control the volume gain.
        gainControl.setValue(-10.0f); // Reduce volume by 10 decibels.
        clipPlayerBullets.start();  // Start the opened clip.
    }


}
