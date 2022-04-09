package be.uantwerpen.fti.ei.space;

/**
 * This class is used to control the bonuses.
 * @author Bavo Lesy
 * @version 1.0
 */
public abstract class Bonus extends Entity{
    public abstract void Vis();
    int dyB = 6; // Movement speed of the bonus.

    /**
     * This method moves the bonus towards the player.
     */
    public void moveBonus(){ bonusY += dyB; }
}
