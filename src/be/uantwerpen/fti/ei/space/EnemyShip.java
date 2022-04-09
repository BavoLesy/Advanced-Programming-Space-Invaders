package be.uantwerpen.fti.ei.space;
/**
 * This class is used to control the enemy ship.
 * @author Bavo Lesy
 * @version 1.0
 */
public abstract class EnemyShip extends Entity{
    public abstract void Vis();
    public int dxE = 4; //amount of x pixels the enemy ships move.
    public int dyE = 8; //amount of y pixels the enemy ships move down.
    public int verticalCounter = 0;
    /**
     * This method moves the enemy to the right.
     */
    public void moveEnemyRight() {
            enemyShipX += dxE; // Ship moves to the right with dxE pixels.
            verticalCounter++; // Moves the ships vertically after x amount of horizontal movement cycles.
            if(verticalCounter >= 50){
                verticalCounter = 0;
                moveEnemyY();
            }
    }
    /**
     * This method moves the enemy to the left.
     */
    public void moveEnemyLeft(){
            enemyShipX -= dxE; // Ship moves to the left with dxE pixels.
            verticalCounter++; // Moves the ships vertically after x amount of horizontal movement cycles.
        if(verticalCounter >= 60){
            verticalCounter = 0;
            moveEnemyY();
        }
    }
    /**
     * This method moves the boss down.
     */
    public void moveEnemyY(){
        enemyShipY += dyE;
    } // Move the ships vertically.







}
