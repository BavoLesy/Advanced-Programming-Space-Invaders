package be.uantwerpen.fti.ei.space;

/**
 * This class is used to store most of the variables that deal with
 * positioning, types and status of entities.
 * @author Bavo Lesy
 * @version 1.0
 */
public class Entity {
    public int playerShipX; // Starting point of playershipx.         //DONE
    public int playerShipY; // Starting point of playershipy.         //DONE
    public int playerShipEnd; // Upper limit: 1360 pixels.           //DONE
    public int playerShipBegin; // Lower limit: 0 pixels.               //DONE
    public int playerShipWidth; // Width of playership.                //DONE
    public int playerShipHeight; // Height of playership.              //DONE
    public boolean invicibiltyPlayer = false;
    public void setPlayerShipX(int playerShipX) { this.playerShipX = playerShipX; }
    public void setPlayerShipY(int playerShipY) { this.playerShipY = playerShipY; }
    public void setPlayerShipEnd(int playerShipEnd) { this.playerShipEnd = playerShipEnd; }
    public void setPlayerShipBegin(int playerShipBegin) { this.playerShipBegin = playerShipBegin; }
    public void setPlayerShipWidth(int playerShipWidth) { this.playerShipWidth = playerShipWidth; }
    public void setPlayerShipHeight(int playerShipHeight) { this.playerShipHeight = playerShipHeight; }

    // Enemy ships start at the top (0,0).
    public int enemyShipY;
    public int enemyShipX;
    public int enemyShipWidth; // Width of enemy ship.
    public int enemyShipHeight; //Height of enemy ship.
    public void setEnemyShipX(int enemyShipX) { this.enemyShipX = enemyShipX; }
    public void setEnemyShipY(int enemyShipY) { this.enemyShipY = enemyShipY; }
    public void setEnemyShipWidth(int enemyShipWidth) { this.enemyShipWidth = enemyShipWidth; }
    public void setEnemyShipHeight(int enemyShipHeight) { this.enemyShipHeight = enemyShipHeight; }

    public int bossShipX;
    public int bossShipY;
    public int bossShipWidth;
    public int bossShipHeight;
    public int bossBulletType = 0; // NOT DONE
    public void setBossShipX(int bossShipX) { this.bossShipX = bossShipX; }
    public void setBossShipY(int bossShipY) { this.bossShipY = bossShipY; }
    public void setBossShipWidth(int bossShipWidth) { this.bossShipWidth = bossShipWidth; }
    public void setBossShipHeight(int bossShipHeight) { this.bossShipHeight = bossShipHeight; }
    public void setBossBulletType(int bossBulletType) { this.bossBulletType = bossBulletType; }

    public int playerBulletX;
    public int playerBulletY;
    public int playerBulletWidth; // Width of playerbullets.
    public int playerBulletHeight; // Height of playerbullets.
    public void setPlayerBulletX(int playerBulletX){this.playerBulletX = playerBulletX;}
    public void setPlayerBulletY(int playerBulletY){this.playerBulletY = playerBulletY;}
    public void setPlayerBulletWidth(int playerBulletWidth) { this.playerBulletWidth = playerBulletWidth; }
    public void setPlayerBulletHeight(int playerBulletHeight) { this.playerBulletHeight = playerBulletHeight; }

    public int enemyBulletX;
    public int enemyBulletY;
    public int enemyBulletWidth; // Width of playerbullets.
    public int enemyBulletHeight; // Height of playerbullets.
    public int enemyBulletDirection = 0;
    public int bossBulletWidth;
    public int bossBulletHeight;
    public void setEnemyBulletX(int enemyBulletX){ this.enemyBulletX = enemyBulletX;}
    public void setEnemyBulletY(int enemyBulletY){this.enemyBulletY = enemyBulletY;}
    public void setEnemyBulletWidth(int enemyBulletWidth) { this.enemyBulletWidth = enemyBulletWidth; }
    public void setEnemyBulletHeight(int enemyBulletHeight) { this.enemyBulletHeight = enemyBulletHeight; }
    public void setEnemyBulletDirection(int enemyBulletDirection) { this.enemyBulletDirection = enemyBulletDirection; }

    public void setBossBulletWidth(int bossBulletWidth) {
        this.bossBulletWidth = bossBulletWidth;
    }

    public void setBossBulletHeight(int bossBulletHeight) {
        this.bossBulletHeight = bossBulletHeight;
    }

    public int specialBulletX;
    public int specialBulletY;

    public int specialBulletWidth;
    public int specialBulletHeight;
    public void setSpecialBulletX(int specialBulletX) { this.specialBulletX = specialBulletX; }
    public void setSpecialBulletY(int specialBulletY) { this.specialBulletY = specialBulletY; }
    public void setSpecialBulletWidth(int specialBulletWidth) { this.specialBulletWidth = specialBulletWidth; }
    public void setSpecialBulletHeight(int specialBulletHeight) { this.specialBulletHeight = specialBulletHeight; }

    public int bonusX;
    public int bonusY;
    public int bonusWidth;
    public int bonusHeight;
    public int bonusType;
    public void setBonusX(int bonusX){this.bonusX = bonusX;}
    public void setBonusY(int bonusY){this.bonusY = bonusY;}
    public void setBonusType(int bonusType) { this.bonusType = bonusType; }
    public void setBonusWidth(int bonusWidth) { this.bonusWidth = bonusWidth; }
    public void setBonusHeight(int bonusHeight) { this.bonusHeight = bonusHeight; }

    public int lives;
    public int score;
    public int level;
    public int bossLevel;
    public int amount;
    public int lines;
    public int bossLives;
    public void setLives(int lives){ this.lives = lives;}
    public void setScore(int score){ this.score = score;}
    public void setLevel(int level){this.level = level;}
    public void setBossLevel(int bossLevel) { this.bossLevel = bossLevel; }
    public void setAmount(int amount) { this.amount = amount; }
    public void setLines(int lines) { this.lines = lines; }
    public void setBossLives(int bossLives) { this.bossLives = bossLives; }

    public boolean scorePaused = false;
    public boolean startScreen = true;

    public int explosionsX;
    public int explosionsY;
    public int explosionsWidth;
    public int explosionsHeight;
    public int shieldWidth = 100;
    public int shieldHeight = 100;
    public void setExplosionsX(int explosionsX) {
        this.explosionsX = explosionsX;
    }

    public void setExplosionsY(int explosionsY) {
        this.explosionsY = explosionsY;
    }

    public void setExplosionsWidth(int explosionsWidth) {
        this.explosionsWidth = explosionsWidth;
    }

    public void setExplosionsHeight(int explosionsHeight) {
        this.explosionsHeight = explosionsHeight;
    }

    public void setShieldWidth(int shieldWidth) {
        this.shieldWidth = shieldWidth;
    }

    public void setShieldHeight(int shieldHeight) {
        this.shieldHeight = shieldHeight;
    }

}
