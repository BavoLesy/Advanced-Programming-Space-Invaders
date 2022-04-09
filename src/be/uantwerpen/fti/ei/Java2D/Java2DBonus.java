package be.uantwerpen.fti.ei.Java2D;
import be.uantwerpen.fti.ei.space.Bonus;
import java.awt.*; // Used for graphics.

/**
 * This class is used to visualise the different bonuses.
 * @author Bavo Lesy
 * @version 1.0
 */
public class Java2DBonus extends Bonus {
    private final Java2DWorld g;
    public Java2DBonus(Java2DWorld gr){ this.g = gr;}
    /**
     * Visualises the bonus corresponding to the bonus type.
     */
    @Override
    public void Vis(){ // Implement Visualise() function Here
       Graphics2D g2d = g.getGraphics2d();

       switch(bonusType){ // Different sprites for different bonuses;
           case 1: g2d.drawImage(g.PurpleBonusSprite, bonusX ,bonusY, null);    // 1 is invincibility.
       break;
           case 2: g2d.drawImage(g.LightBlueBonusSprite, bonusX ,bonusY, null); // 2 is freezeBullets.
       break;
           case 3: g2d.drawImage(g.YellowBonusSprite,bonusX, bonusY, null);     // 3 is superSpeed.
       break;
           case 4: g2d.drawImage(g.GreenBonusSprite,bonusX, bonusY, null); // 4 is extra life.
               break;
           case 5: g2d.drawImage(g.RedBonusSprite,bonusX,bonusY,null); // 5 is one less life.
               break;
       }

    }
}



