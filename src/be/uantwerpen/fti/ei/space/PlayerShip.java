package be.uantwerpen.fti.ei.space;
// Imports used for the sounds.
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
/**
 * This class is used to control the player ship and the sound.
 * @author Bavo Lesy
 * @version 1.0
 */
public abstract class PlayerShip extends Entity{
    public abstract void Vis();
    public int dxP = 7; // Speed of the player.
    protected SoundComponent sound; // Contains different sounds (data).
    protected SoundSystem updateSound; // Contains the logic to play the sounds.
    public PlayerShip() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.sound = new SoundComponent("bonus"); // Sound used when the player picks up a bonus.
        this.updateSound = new SoundSystem(sound);
    }
    /**
     * This method is used to play the sound.
     * @return sound of the player picking up a bonus.
     */
    public SoundSystem getUpdateSound(){
        return updateSound;
    } // Getter used to access the sound.
    /**
     * This method moves the player to the right.
     */
    public void right() {
        if (playerShipX < playerShipEnd){ // If the ship is not out of bounds and moving right: keep moving right.
            playerShipX += dxP;
        }
    }
    /**
     * This method moves the player to the left.
     */
    public void left() {
        if (playerShipX > playerShipBegin){ // If the ship is not out of bounds and moving left: keep moving left.
            playerShipX -= dxP;
        }
    }
    /**
     * This method increases the player's speed.
     */
    public void superSpeed(){ dxP = 13; }
    /**
     * This method decreases the player's speed.
     */
    public void normalSpeed(){ dxP = 7; }
}
