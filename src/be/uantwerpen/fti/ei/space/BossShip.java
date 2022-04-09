package be.uantwerpen.fti.ei.space;
// Imports used for sounds.
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
/**
 * This class is used to control the boss ship and the sound.
 * @author Bavo Lesy
 * @version 1.0
 */
public abstract class BossShip extends Entity {
    public abstract void Vis();
    public int dxB = 5; // BossShip x movement speed.
    public int dyB = 4; // BossShip y movement speed.
    public int verticalCounter = 0; // Counter used to move the BossShips down on the screen.
    // Data Oriented Design
    protected SoundComponent sound; // Contains different sounds (data).
    protected SoundSystem updateSound; // Contains the logic to play the sounds.
    public BossShip() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.sound = new SoundComponent("freezeBoss"); // Sound used when the boss freezes.
        this.updateSound = new SoundSystem(sound);
    }

    /**
     * This method is used to play the sound.
     * @return sound of the boss freezing.
     */
    public SoundSystem getUpdateSound(){
        return updateSound;
    } // Getter used to access the sound.

    /**
     * This method moves the boss to the right.
     */
    public void moveBossRight() {
        bossShipX += dxB; // Ship moves to the right with dxE pixels.
        verticalCounter++; // Moves the ships vertically after x amount of horizontal movement cycles.
        if(verticalCounter >= 50){
            verticalCounter = 0;
            moveBossY();
        }
    }
    /**
     * This method moves the boss to the left.
     */
    public void moveBossLeft(){
        bossShipX -= dxB; // Ship moves to the left with dxE pixels.
        verticalCounter++; // Moves the ships vertically after x amount of horizontal movement cycles.
        if(verticalCounter >= 50){
            verticalCounter = 0;
            moveBossY();
        }
    }
    /**
     * This method moves the boss down.
     */
    public void moveBossY(){
        bossShipY += dyB;
    } // Ship moves down vertically.

}
