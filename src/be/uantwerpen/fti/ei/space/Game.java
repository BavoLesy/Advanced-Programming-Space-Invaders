package be.uantwerpen.fti.ei.space;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import javax.sound.sampled.*;
/**
 * This class contains all the logic used to run our game.
 * @author Bavo Lesy
 * @version 1.0
 */
public class Game { // Object that represents the game with a self contained list of the entities, a main loop that checks for collisions, interactions and states,
    // Used to determine the status of the game.
    public boolean gameOver = false;
    // Used for player movement.
    private boolean stop = true;
    private boolean moveRight;
    // Used for enemy creation and enemy movement.
    public ArrayList<EnemyShip> enemyShipsList = new ArrayList<>();
    public boolean enemyRight;
    public boolean bossRight;
    // Used for player bullets.
    public ArrayList<PlayerBullets> playerBulletsList = new ArrayList<>();
    public ArrayList<SpecialBullets> specialBulletsList = new ArrayList<>();
    // Used for enemy bullets.
    public ArrayList<EnemyBullets> enemyBulletsList = new ArrayList<>();
    public ArrayList<Bonus> bonusesList = new ArrayList<>();
    public ArrayList<BossShip> bossShipsList = new ArrayList<>();
    public ArrayList<Explosions> explosionsList = new ArrayList<>();
    public int bonusType;
    public boolean freezeBullets = false;
    public boolean isFrozen = false;
    public boolean superSpeed = false;
    public boolean enemiesUp = false;
    public boolean nextLevel = true;
    public boolean isRunning = true;
    public boolean isPaused = true;
    public boolean soundPlayerBullet = false;
    public boolean soundExplosion = false;
    public boolean soundBonus = false;
    public boolean soundSpecialBullet = false;
    public boolean soundFreeze = false;
    public boolean soundPlayerHit = false;
    public boolean soundEnemyBullet = false;
    public boolean soundGameOver = false;
    public boolean soundVictory = false;
    // Used for determining the end score:
    public int bulletHit = 1;
    public int bulletMiss = 1;
    public int bonusAmount = 0;
    public int time = 4000;
    // All read from the config file
    public int screenWidth;
    public int screenHeight;

    public int enemyShipWidth;
    public int enemyShipHeight;
    public int enemyShipX;
    public int enemyShipY;

    public int bossShipWidth;
    public int bossShipHeight;
    public int bossShipX;
    public int bossShipY;

    public int playerBulletX;
    public int playerBulletY;
    public int playerBulletWidth;
    public int playerBulletHeight;

    public int enemyBulletX;
    public int enemyBulletY;
    public int enemyBulletWidth;
    public int enemyBulletHeight;
    public int bossBulletWidth;
    public int bossBulletHeight;

    public int specialBulletX;
    public int specialBulletY;
    public int specialBulletWidth;
    public int specialBulletHeight;

    public int bonusX;
    public int bonusY;
    public int bonusWidth;
    public int bonusHeight;

    public int explosionsX;
    public int explosionsY;
    public int explosionsWidth;
    public int explosionsHeight;
    // Variables read from the config file used to determine the timing and location of game entities.
    public int bulletTimer;
    public int freezeBulletAmount;
    public int enemyBulletTimer;
    public int bonusMin;
    public int bonusMax;
    public int deBonusMin;
    public int deBonusMax;
    public int explosionsTimer;
    public int bonusMinX;
    public int bonusMaxX;
    public int enemySpaceX;
    public int enemySpaceY;
    public int enemiesMoveUp;
    public int accuracyPoints; // The amount of score you get when for accurate shooting
    public int bonusPoints; // The amount of score you get for picking up bonuses
    public int livesPoints; // The amount of score you get for having extra lives
    public int timePoints; // The amount of score you get for completing the game quickly

    public int bulletBuffer = 35; // Buffer used so that player cannot 'spam' bullets.
    public int enemyBuffer = 60; // Buffer used to determine the timing of the enemy bullets.
    public int bonusBuffer = 0; // Buffer used to determine the timing of the bonus.
    public int bonusDebuffer = 0; // Buffer used to determine how long the bonus lasts;
    public int specialBuffer = 0; // Buffer used to determine the amount of special bullets
    public int explosionsBuffer = 0;
    public boolean bonusActive = false;
    AFact a;
    Game(AFact a) {
        this.a = a;
    }
    /**
     * This method runs our game.
     * Here we create and move entities like ships, bullets, bonuses and explosions.
     * Here we keep track of our lives, level, score , ...
     * Here we check for collisions between the different entities and act accordingly.
     * Here we use keyboard inputs to control our game (Shooting, moving and pausing).
     * Here we read the config file to get the necessary data to play the game.
     * @throws IOException If the sound file is not read correctly.
     * @throws UnsupportedAudioFileException If the audio file is unsupported.
     * @throws LineUnavailableException if the requested line is currently unavailable.
     */
    void Start() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Input.Inputs playerInput;
        Input move = a.getInput();
        Score s = a.getScore();
        GameOver go = a.getGameOver();
        Victory v = a.getVictory();
        PlayerShip p = a.getPlayerShip();
        loadConfig(p,s);
        long beginFrame;
        long endFrame;
        a.resizeSprites(screenWidth,screenHeight);
        while(isRunning){
            if(gameOver){  // if there is gameOver, either visualise gameOver or victory.
                if(go.lives <= 0){
                    go.Vis();
                }else{
                    v.Vis();
                }
                a.render();
                playerInput = move.getInput();
                if (playerInput == Input.Inputs.ENTER){ // Restart the game.
                    loadConfig(p,s);
                    p.startScreen = true;
                    gameOver = false;
                    isPaused = true;
                    Restart(p);
                    if (s.level == s.bossLevel) {
                        createBoss();
                    } else {
                        createEnemy(s); // Fill an arraylist with the enemies, amount of enemies depending on the level/difficulty
                    }
                }
            }else{
                // Here we time when the frame loading starts, then we also time when the frame ends, here we add a certain
                // amount of ms to ensure that we operate at a constant framerate (60 fps).
                // Here we need to make sure our fps is not too high, because then there isn't enough time to execute all code.
                beginFrame = System.currentTimeMillis();
                // Pausing the game.
                if (isPaused && move.inputAvailable()) {
                    playerInput = move.getInput();
                    if (playerInput == Input.Inputs.ENTER) {
                        isPaused = false;
                        if (nextLevel) {
                            if(!p.startScreen){
                                s.score+=50;
                            }
                            s.scorePaused = true;
                            Restart(p);
                            if (s.level == s.bossLevel) {
                                createBoss();
                            } else {
                                createEnemy(s); // Create new enemies.
                            }
                            s.scorePaused = false;
                            nextLevel = false;
                        }
                        p.startScreen = false;
                    }
                }
                // Keyboard inputs.
                if (!isPaused) {
                    if(move.inputAvailable()) {
                        playerInput = move.getInput();
                        switch (playerInput) {
                            case RIGHT:
                                moveRight = true;
                                stop = false;
                                break;
                            case LEFT:
                                moveRight = false;
                                stop = false;
                                break;
                            case SPACEBAR: // Shoot.
                                if (bulletBuffer >= bulletTimer) {
                                    if (freezeBullets) {
                                        createFreezeBullets(p);
                                        specialBuffer++;
                                        if (specialBuffer >= freezeBulletAmount) {
                                            freezeBullets = false;
                                            specialBuffer = 0;
                                        }
                                    } else {
                                        createPlayerBullets(p);
                                    }
                                    bulletBuffer = 0;
                                }
                                break;
                            case ENTER:
                                isPaused = true;
                                break;
                            case SHIFT:
                                stop = true;
                                break;
                        }
                    }
                        if (!stop) {
                            if (moveRight) { // Move player to the right.
                                p.right();
                            } else
                                p.left(); // Move player to the left.
                        }
                    enemyBuffer++; // Buffer used to time the enemy bullets.
                    bonusBuffer++; // Buffer used to time the bonuses
                    bulletBuffer++; // Buffer used so that player can't spam bullets.
                    if(time>0){
                        time--;
                    }
                    if (superSpeed) {
                        bulletBuffer++;
                    }
                    // Create bullets for both the normal enemies and the boss.
                    if (enemyBuffer >= enemyBulletTimer / ((s.level + 1) / 2)) {
                        if (s.level == s.bossLevel) {
                            createBossBullets();
                        } else {
                            createEnemyBullets();
                        }
                        enemyBuffer = 0;
                    }
                    // Create the bonus.
                    if (bonusBuffer >= (bonusMin + (int) (Math.random() * bonusMax)) && !bonusActive) {
                        createBonus(); bonusActive = true;
                        bonusBuffer = 0;
                    }
                    // Remove the bonus effect.
                    if (bonusActive) {
                        bonusDebuffer++;
                        if (bonusDebuffer >= (deBonusMin + (int) (Math.random() * deBonusMax))) {
                            removeBonus(p); bonusDebuffer = 0;
                            bonusActive = false;
                        }
                    }
                    // Remove the explosion.
                    if (!explosionsList.isEmpty()) {
                        explosionsBuffer++;
                        if (explosionsBuffer >= explosionsTimer) {
                            explosionsList.clear();
                            explosionsBuffer = 0;
                        }
                    }
                    collisionDetection(p, s, go, v); // Detect collisions between bullets and ships and act accordingly.
                    moveBullets(p); // Move the bullets, they only move if the game is not paused.
                    borderEnemy(s, go, v, p); // Check when the enemies hit which border and change direction accordingly.
                   moveEnemy(); // Move the enemies, they only move if the game is not paused.
                    if(enemiesUp) { // After moving the ships back up with the bonus.
                        enemiesUp = false;
                    }
                }Visualise(p, s);
                a.render(); // Render the game.
                endFrame = System.currentTimeMillis(); // Time the execution of the game.
                playSound(p, s, go, v);
                try {
                    Thread.sleep(18 - (endFrame - beginFrame)); // Go to sleep for the remaining time to get ~50 FPS.
                } catch (InterruptedException ex) { // Catch the exception.
                    ex.printStackTrace(); // If the game takes longer than 20 ms to execute, print this error so we can act accordingly.
                }
            }
        }
    }
    /**
     * This method is used to create the bonus.
     */
    public void createBonus(){
        Bonus b = a.getBonus();
        b.setBonusX(bonusMinX+(int)(Math.random()*bonusMaxX)); // Randomise the starting point of the bonus powerup.
        b.setBonusY(0); // Start at the top of the screen.
        b.setBonusWidth(bonusWidth);
        b.setBonusHeight(bonusHeight);
        bonusType = 1 + (int) (Math.random() * (5)); // Choose randomly from 5 different Bonuses
        b.setBonusType(bonusType);
        bonusesList.add(b); // Add the bonus to the arraylist of Bonuses.
    }
    /**
     * This method is used to remove the bonus effect.
     * @param p The player ship.
     */
    public void removeBonus(PlayerShip p){
        switch(bonusType) {
            case 1:
                p.invicibiltyPlayer = false;
                break;
            case 2:
                freezeBullets = false;
                isFrozen = false;
                break;
            case 3:
                p.normalSpeed();
                superSpeed = false;
                break;
        }
    }
    /**
     * This method is used to create the boss ship.
     * @throws IOException If the sound file is not read correctly.
     * @throws UnsupportedAudioFileException If the audio file is unsupported.
     * @throws LineUnavailableException if the requested line is currently unavailable.
     */
    public void createBoss() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        BossShip bs = a.getBossShip();
        bs.setBossShipHeight(bossShipHeight);
        bs.setBossShipWidth(bossShipWidth);
        bs.setBossShipX(bossShipX);
        bs.setBossShipY(bossShipY);
        bossShipsList.add(bs);
    }
    /**
     * This method is used to create enemy ships.
     */
    public void createEnemy(Score s){ // Create enemies, the amount is determined by the config file.
        for(int i = 0; i < s.lines; i++){ // For each line make an amount of enemies.
            for(int j = 0; j < s.amount; j++){
                EnemyShip e = a.getEnemyShip();
                e.setEnemyShipWidth(enemyShipWidth);
                e.setEnemyShipHeight(enemyShipHeight);
                e.setEnemyShipX(enemyShipX + enemySpaceX * j); // Starting x for this enemy.
                e.setEnemyShipY(enemyShipY + enemySpaceY * i); // Starting y for this enemy.
                enemyShipsList.add(e); // Add enemy to arraylist.
            }
        }
    }
    /**
     * This method is used to create explosions.
     * @param x The x-coordinate of the explosion.
     * @param y The y-coordinate of the explosion.
     * @throws IOException If the sound file is not read correctly.
     * @throws UnsupportedAudioFileException If the audio file is unsupported.
     * @throws LineUnavailableException if the requested line is currently unavailable.
     */
    public void createExplosion(int x, int y) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Explosions ep = a.getExplosions(); //
        ep.setExplosionsWidth(explosionsWidth);
        ep.setExplosionsHeight(explosionsHeight);
        ep.setExplosionsX(x); // Give x coordinate of ship.
        ep.setExplosionsY(y); // Give y coordinate of ship.
        explosionsList.add(ep); // Add bullets to arraylist.
        soundExplosion = true;
    }
    /**
     * This method is used to create player bullets.
     * @param p The player ship.
     * @throws IOException If the sound file is not read correctly.
     * @throws UnsupportedAudioFileException If the audio file is unsupported.
     * @throws LineUnavailableException if the requested line is currently unavailable.
     */
    public void createPlayerBullets(PlayerShip p) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        PlayerBullets pb = a.getPlayerBullets();
        pb.setPlayerBulletWidth(playerBulletWidth);
        pb.setPlayerBulletHeight(playerBulletHeight);
        pb.setPlayerBulletX(p.playerShipX + p.playerShipWidth/2 - pb.playerBulletWidth/2); // Starting x of the bullet.
        pb.setPlayerBulletY(p.playerShipY - p.playerShipHeight + pb.playerBulletHeight); // Starting y of the bullet.
        playerBulletsList.add(pb); // Add this bullet to the list.
        soundPlayerBullet = true;
    }
    /**
     * This method is used to create the special freeze bullets.
     * @param p The player ship.
     * @throws IOException If the sound file is not read correctly.
     * @throws UnsupportedAudioFileException If the audio file is unsupported.
     * @throws LineUnavailableException if the requested line is currently unavailable.
     */
    public void createFreezeBullets(PlayerShip p) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        SpecialBullets sb = a.getSpecialBullets();
        sb.setSpecialBulletWidth(specialBulletWidth);
        sb.setSpecialBulletHeight(specialBulletHeight);
        sb.setSpecialBulletX(p.playerShipX + p.playerShipWidth/2 - sb.specialBulletWidth/2);
        sb.setSpecialBulletY(p.playerShipY + p.playerShipHeight - sb.specialBulletHeight);
        specialBulletsList.add(sb);
        soundSpecialBullet = true;
    }
    /**
     * This method is used to create the enemy bullets.
     * @throws IOException If the sound file is not read correctly.
     * @throws UnsupportedAudioFileException If the audio file is unsupported.
     * @throws LineUnavailableException if the requested line is currently unavailable.
     */
    public void createEnemyBullets() throws UnsupportedAudioFileException, IOException, LineUnavailableException { // 3-4 random ships shoot after each buffer.
        int i = 0;
        // We use 3 random numbers between the first and last ships to determine which ships shoot when.
        int randomEnemy = 1 + (int) (Math.random() * (enemyShipsList.size()));
        int randomEnemy2 = 1 + (int) (Math.random() * (enemyShipsList.size()));
        int randomEnemy3 = 1 + (int) (Math.random() * (enemyShipsList.size()));
        int randomEnemy4 = 1 + (int) (Math.random() * (enemyShipsList.size()));
        // If statements to make sure that these random numbers are always different so that at least 3 bullets are shot every cycle.
        if(randomEnemy == randomEnemy2){ // If these are equal, either subtract or add one.
            if(randomEnemy2 > 1){
                randomEnemy2--;
            }else{
                randomEnemy2++;
            }
        }
        if(randomEnemy3 == randomEnemy2){ // If these are equal, either subtract or add one.
            if(randomEnemy3 > 1){
                randomEnemy3--;
            }else{
                randomEnemy3++;
            }
        }
        if(randomEnemy == randomEnemy3){ // If these are equal, either subtract or add one.
            if(randomEnemy > 1){
                randomEnemy--;
            }else{
                randomEnemy++;
            }
        }
        for(EnemyShip elem: enemyShipsList) {
            i++; // Go through all enemy ships.
            if(i == randomEnemy || i == randomEnemy2 || i == randomEnemy3 || i == randomEnemy4 ) { // 4 ships shoot.
                EnemyBullets eb = a.getEnemyBullets();
                elem.setEnemyShipWidth(enemyShipWidth);
                elem.setEnemyShipHeight(enemyShipHeight);
                eb.setEnemyBulletWidth(enemyBulletWidth);
                eb.setEnemyBulletHeight(enemyBulletHeight);
                eb.setBossBulletWidth(bossBulletWidth);
                eb.setBossBulletHeight(bossBulletHeight);
                eb.setEnemyBulletX(elem.enemyShipX + elem.enemyShipWidth/2 - elem.enemyBulletWidth/2); // Give x coordinate of ship.
                eb.setEnemyBulletY(elem.enemyShipY + elem.enemyShipHeight-5); // Give y coordinate of ship.
                enemyBulletsList.add(eb); // Add bullets to arraylist.
            }
        }
        soundEnemyBullet = true;
    }
    /**
     * This method is used to create the boss bullets.
     * @throws IOException If the sound file is not read correctly.
     * @throws UnsupportedAudioFileException If the audio file is unsupported.
     * @throws LineUnavailableException if the requested line is currently unavailable.
     */
    public void createBossBullets() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if(!isFrozen){
            for (BossShip elem : bossShipsList) {
                elem.setBossBulletType(1 + (int) (Math.random() * 2));
                EnemyBullets bb = a.getEnemyBullets();
                if (elem.bossBulletType == 1) { // Normal, blue bullets.
                    bb.setEnemyBulletWidth(enemyBulletWidth);
                    bb.setEnemyBulletHeight(enemyBulletHeight);
                    bb.setBossBulletType(elem.bossBulletType);
                    bb.setEnemyBulletX(elem.bossShipX + 49);
                    bb.setEnemyBulletY(elem.bossShipY + 150);
                    bb.setEnemyBulletDirection(1);

                    EnemyBullets bb2 = a.getEnemyBullets();
                    bb2.setEnemyBulletWidth(enemyBulletWidth);
                    bb2.setEnemyBulletHeight(enemyBulletHeight);
                    bb2.setBossBulletType(elem.bossBulletType);
                    bb2.setEnemyBulletX(elem.bossShipX + 137);
                    bb2.setEnemyBulletY(elem.bossShipY + 150);
                    bb2.setEnemyBulletDirection(2);
                    enemyBulletsList.add(bb2);

                    EnemyBullets bb3 = a.getEnemyBullets();
                    bb3.setEnemyBulletWidth(enemyBulletWidth);
                    bb3.setEnemyBulletHeight(enemyBulletHeight);
                    bb3.setBossBulletType(elem.bossBulletType);
                    bb3.setEnemyBulletX(elem.bossShipX + 49);
                    bb3.setEnemyBulletY(elem.bossShipY + 150);
                    bb3.setEnemyBulletDirection(2);
                    enemyBulletsList.add(bb3);

                    EnemyBullets bb4 = a.getEnemyBullets();
                    bb4.setEnemyBulletWidth(enemyBulletWidth);
                    bb4.setEnemyBulletHeight(enemyBulletHeight);
                    bb4.setBossBulletType(elem.bossBulletType);
                    bb4.setEnemyBulletX(elem.bossShipX + 137);
                    bb4.setEnemyBulletY(elem.bossShipY + 150);
                    bb4.setEnemyBulletDirection(1);
                    enemyBulletsList.add(bb4);
                }   else {       // Big Red Bullet.
                    bb.setBossBulletWidth(bossBulletWidth);
                    bb.setBossBulletHeight(bossBulletHeight);
                    bb.setBossBulletType(elem.bossBulletType);
                    bb.setEnemyBulletX(elem.bossShipX + 92);
                    bb.setEnemyBulletY(elem.bossShipY + 180);
                }
                enemyBulletsList.add(bb);
            }
            soundEnemyBullet = true;
        }
    }
    /**
     * This method is used to control that the enemies are still within the playing area and are not colliding with the player ship.
     * @param s The Score, used to edit the lives.
     * @param go The gameOver, used to end the game if necessary.
     * @param v the victory, used to win the game if necessary.
     * @param p the Player ship.
     */
    public void borderEnemy(Score s, GameOver go, Victory v, PlayerShip p){ // Check if the enemies hit the border and change direction accordingly
        for (EnemyShip elem : enemyShipsList) {
            if (elem.enemyShipX <= p.playerShipBegin) { // If enemy ship hits left border.
                enemyRight = true;
            }else if (elem.enemyShipX >= p.playerShipEnd+20) { // If enemy ship hits right border.
                enemyRight = false;
            }
            if(elem.enemyShipY >= p.playerShipY-60){
                s.lives = 0;
                gameOver(s,go,v);
            }
        }
        for(BossShip elem : bossShipsList) {
            if (elem.bossShipX <= p.playerShipBegin) {
                bossRight = true;
            } else if (elem.bossShipX >= p.playerShipEnd - 150) {
                bossRight = false;
            }
            if (elem.bossShipY >= p.playerShipY - elem.bossShipHeight) {
                s.lives = 0;
                gameOver(s,go,v);
            }
        }
    }
    /**
     * This method is used to move the enemy ships and the boss ship.
     */
    public void moveEnemy(){
        for(EnemyShip elem : enemyShipsList) { // For each enemy in the arraylist.
            if (enemyRight) {
                elem.moveEnemyRight(); // Move enemy to the right.
            } else {
                elem.moveEnemyLeft(); // Move enemy to the left.
            }
            if(enemiesUp){
                elem.setEnemyShipY(elem.enemyShipY - enemiesMoveUp);
            }
        }
        if(!isFrozen) {
            for (BossShip elem : bossShipsList) {
                if (bossRight) {
                    elem.moveBossRight();
                } else {
                    elem.moveBossLeft();
                }
                if(enemiesUp){
                    elem.setBossShipY(elem.bossShipY - enemiesMoveUp);
                }
            }
        }
    }
    /**
     * This method is used to move the player bullets, the enemy bullets, the special bullets, the boss bullets and the bonuses.
     * @param p The player ship.
     */
    public void moveBullets(PlayerShip p){
        for(PlayerBullets elem : playerBulletsList){ // For each bullet.
            elem.movePlayerBullets(); // Move player bullets up.
        }
        for(EnemyBullets elem : enemyBulletsList) { // For each bullet.
            elem.playerShipX = p.playerShipX;
            if (elem.bossBulletType == 1) {
                elem.moveBlueBullets();
            } else if (elem.bossBulletType == 2) {
                elem.moveBossBullets(); // Move enemy bullets down.
            }else{
                elem.moveEnemyBullets();
            }
        }
        for(Bonus elem: bonusesList){ // For each bonus.
            elem.moveBonus(); // Move the bonuses down.
        }
        for(SpecialBullets elem: specialBulletsList){
            elem.moveFreezeBullets();
        }
    }
    /**
     * This method is used to check for collision between all the different entities.
     * @param p The player ship.
     * @param s The Score, used to check and edit the lives and level.
     * @param go The gameOver, used to end the game if necessary.
     * @param v the victory, used to win the game if necessary.
     * @throws IOException If the sound file is not read correctly.
     * @throws UnsupportedAudioFileException If the audio file is unsupported.
     * @throws LineUnavailableException if the requested line is currently unavailable.
     */
    public void collisionDetection(PlayerShip p, Score s,GameOver go, Victory v) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
       // Iterators to go through the arraylists.
       Iterator<PlayerBullets> pb = playerBulletsList.iterator();
       Iterator<EnemyBullets> eb = enemyBulletsList.iterator();
       Iterator<EnemyShip> e = enemyShipsList.iterator();
       Iterator <Bonus> b = bonusesList.iterator();
       Iterator <SpecialBullets> sb = specialBulletsList.iterator();
       Iterator <BossShip> bs = bossShipsList.iterator();
       while(pb.hasNext()) { // While there are player bullets.
           PlayerBullets playerBullet = pb.next(); // Add this bullet into the variable playerBullet.
           if (playerBullet.playerBulletY < 0) { // Out of border --> delete the bullet.
               pb.remove(); // Remove player bullet.
               bulletMiss++;
           } else {
               // Collision detection between bossShip and PlayerBullets.
               if (s.level == s.bossLevel) {
                   while (bs.hasNext()) {
                       BossShip bossShip = bs.next();
                       int bsY = bossShip.bossShipY;
                       int bsX = bossShip.bossShipX;
                       int bsW = bossShip.bossShipWidth;
                       int bsH = bossShip.bossShipHeight;
                       int pbY = playerBullet.playerBulletY;
                       int pbX = playerBullet.playerBulletX;
                       int pbW = playerBullet.playerBulletWidth;
                       int pbH = playerBullet.playerBulletHeight;
                       if (bsX <= (pbX + pbW) && (pbX + pbW) <= (bsX + bsW + pbW) && bsY <= (pbY + pbH) && (pbY + pbH) <= (pbH + bsY + bsH)) {
                           s.bossLives--;
                           if (s.bossLives <= 0) {
                               newLevel(s, go,p,v);
                           } else {
                               pb.remove();
                               bulletHit++;
                               createExplosion(pbX,pbY-75);
                               s.scorePoints();
                           }
                       }
                   }
               } else {
                   // Collision between Enemy ships and player bullets
                   while (e.hasNext()) { // While there are enemy ships.
                       EnemyShip enemyShip = e.next();
                       int eY = enemyShip.enemyShipY;
                       int eX = enemyShip.enemyShipX;
                       int eW = enemyShip.enemyShipWidth;
                       int eH = enemyShip.enemyShipHeight;
                       int pbY = playerBullet.playerBulletY;
                       int pbX = playerBullet.playerBulletX;
                       int pbW = playerBullet.playerBulletWidth;
                       int pbH = playerBullet.playerBulletHeight;
                       if (eX <= (pbX + pbW) && (pbX + pbW) <= (eX + eW + pbW) && eY <= (pbY + pbH) && (pbY + pbH) <= (pbH + eY + eH)) {
                           // Check the hitboxes of the ships and the bullets (this is  confusing because of the heights and widths of the ships and bullets).
                           // I drew this out on paper to get a better understanding of the collision.
                           e.remove(); // Remove enemy ship.
                           pb.remove(); // Remove player bullet.
                           createExplosion(eX,eY);
                           s.scorePoints(); // Add score when killing an enemy.
                           bulletHit++;
                       }
                   }
                   if (enemyShipsList.isEmpty()) {
                       newLevel(s,go,p,v);
                   }
               }
           }
       }
       // Check collision between enemy bullets and the player.
        while(eb.hasNext()){ // While there are enemy bullets.
            EnemyBullets enemyBullet = eb.next(); // Add this bullet into the variable playerBullet.
            if (enemyBullet.enemyBulletY > 810){ // Out of bounds.
                eb.remove(); // Remove bullet that is out of bounds.
            }else{
                if(s.lives > 0) { // While player has lives.
                    int pY = p.playerShipY;
                    int pX = p.playerShipX;
                    int pW = p.playerShipWidth;
                    int pH = p.playerShipHeight;
                    int ebY = enemyBullet.enemyBulletY;
                    int ebX = enemyBullet.enemyBulletX;
                    int ebW = enemyBullet.enemyBulletWidth;
                    int ebH;
                    if(enemyBullet.bossBulletType == 2){
                        ebH = enemyBullet.bossBulletHeight;
                    }else {
                        ebH = enemyBullet.enemyBulletHeight;
                    }
                    if(pX <= (ebX + ebW) && (ebX + ebW) <= (pX + pW + ebW) && pY <= (ebY+ ebH) && (ebY+ ebH) <= (ebH + pY + pH)){
                        // Check the hitboxes of the ships and the bullets (this is  confusing because of the heights and widths of the ships and bullets).
                        // I drew this out on paper to get a better understanding of the collision.
                        if(!p.invicibiltyPlayer) {
                            s.lives--; // Decrease health with 1 life.
                            soundPlayerHit = true;
                        }
                        eb.remove(); // Remove enemy bullet.
                    }
                }else{
                    newLevel(s,go,p,v);
                }
            }
        }
        // Collision between special bullets and boss ship.
        while(sb.hasNext()) {
            SpecialBullets specialBullets = sb.next();
            if (specialBullets.specialBulletY < 0) {
                sb.remove();
            } else {
                if(s.level == s.bossLevel){
                    while (bs.hasNext()) {
                        BossShip bossShip = bs.next();
                        int bsY = bossShip.bossShipY;
                        int bsX = bossShip.bossShipX;
                        int bsW = bossShip.bossShipWidth;
                        int bsH = bossShip.bossShipHeight;
                        int sbY = specialBullets.specialBulletY;
                        int sbX = specialBullets.specialBulletX;
                        int sbW = specialBullets.specialBulletWidth;
                        int sbH = specialBullets.specialBulletHeight;
                        int counter = 0;
                        if (bsX <= (sbX + sbW) && (sbX + sbW) <= (bsX + bsW + sbW) && bsY <= (sbY + sbH) && (sbY + sbH) <= (sbH + bsY + bsH)) {
                            s.bossLives--;
                            if (s.bossLives <= 0) {
                                newLevel(s, go,p,v);
                            } else {
                                counter++;
                                s.scorePoints();
                                if (counter >= 1) {
                                    isFrozen = true; // The boss is frozen because of the freezeBeam.
                                    soundFreeze = true;
                                    sb.remove();
                                }
                            }
                        }
                    }

                }else {
                    // Collision between special bullets and enemy ships.
                    while(e.hasNext()) { // While there are enemy ships.
                        EnemyShip enemyShip = e.next();
                        int eY = enemyShip.enemyShipY;
                        int eX = enemyShip.enemyShipX;
                        int eW = enemyShip.enemyShipWidth;
                        int eH = enemyShip.enemyShipHeight;
                        int sbY = specialBullets.specialBulletY;
                        int sbX = specialBullets.specialBulletX;
                        int sbW = specialBullets.specialBulletWidth;
                        int sbH = specialBullets.specialBulletHeight;
                        if (eX <= (sbX + sbW) && (sbX + sbW) <= (eX + eW + sbW) && eY <= (sbY + sbH) && (sbY + sbH) <= (sbH + eY + eH)) {
                            // Check the hitboxes of the ships and the bullets (this is  confusing because of the heights and widths of the ships and bullets).
                            // I drew this out on paper to get a better understanding of the collision.
                            e.remove(); // Remove enemy ship.
                            createExplosion(eX,eY);
                            s.scorePoints(); // Add score when killing an enemy.
                        }
                    }
                    if(enemyShipsList.isEmpty()) {
                        sb.remove();
                        newLevel(s, go,p,v);
                    }
                }
            }
        }
        // Collision between the bonus and the player.
        while(b.hasNext()){ // If there is a bonus.
            Bonus bonus = b.next();
            if(bonus.bonusY > 810){
                b.remove();
            }else{
                int pY = p.playerShipY;
                int pX = p.playerShipX;
                int pW = p.playerShipWidth;
                int pH = p.playerShipHeight;
                int bY = bonus.bonusY;
                int bX = bonus.bonusX;
                int bW = bonus.bonusWidth;
                int bH = bonus.bonusHeight;
                if(pX <= (bX + bW) && (bX + bW) <= (pX + pW + bW) && pY <= (bY+ bH) && (bY+ bH) <= (bH + pY + pH)){
                    soundBonus = true;
                    b.remove();
                    switch(bonusType){
                        case 1 : p.invicibiltyPlayer = true;
                            break;
                        case 2 : freezeBullets = true;
                            break;
                        case 3 : p.superSpeed();
                                 superSpeed = true;
                            break;
                        case 4: s.lives++;
                            break;
                        case 5: enemiesUp = true;
                            break;
                    }
                }
            }
        }
    }
    /**
     * This method visualises all the different entities
     * @param p The player ship.
     * @param s The Score.
     */
    public void Visualise(PlayerShip p, Score s){
        p.Vis();                                                            // Visualise playership.
        if(!p.startScreen) {                                                // Do not visualise these things on the startScreen.
            for (PlayerBullets elem : playerBulletsList) { elem.Vis(); }    // Visualise player bullets.
            for (EnemyBullets elem : enemyBulletsList) { elem.Vis(); }      // Visualise enemy bullets.
            for (EnemyShip elem : enemyShipsList) { elem.Vis(); }           // Visualise enemy ships.
            for (SpecialBullets elem : specialBulletsList) { elem.Vis(); }  // Visualise special bullets.
            for (Bonus elem : bonusesList) { elem.Vis(); }                  // Visualise bonuses.
            for (BossShip elem : bossShipsList) { elem.Vis(); }             // Visualise boss ship.
            for (Explosions elem : explosionsList) { elem.Vis(); }          // Visualise explosions.
        }
        s.Vis();                                                            // Visualise scoreboard, lives and level.
    }
    /**
     * This method is used to play the different sounds that every entity has.
     * @param p The player ship.
     * @param s The Score.
     * @param go The gameOver.
     * @param v the victory.
     * @throws IOException If the sound file is not read correctly.
     * @throws UnsupportedAudioFileException If the audio file is unsupported.
     * @throws LineUnavailableException if the requested line is currently unavailable.
     */
    public void playSound(PlayerShip p, Score s, GameOver go, Victory v) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        for(PlayerBullets elem: playerBulletsList){
            if(soundPlayerBullet) { elem.getUpdateSound().queueSound();soundPlayerBullet = false; }
        }
        for(Explosions elem: explosionsList){
            if(soundExplosion) { elem.getUpdateSound().queueSound();soundExplosion = false; }
        }
        for(SpecialBullets elem: specialBulletsList){
            if(soundSpecialBullet) { elem.getUpdateSound().queueSound();soundSpecialBullet = false; }
        }
        if(soundBonus){ p.getUpdateSound().queueSound();soundBonus = false;
        }
        for(BossShip elem: bossShipsList){
            if(soundFreeze){ elem.getUpdateSound().queueSound();soundFreeze = false; }
        }
        for(EnemyBullets elem: enemyBulletsList){
            if(soundEnemyBullet){ elem.getUpdateSound().queueSound();soundEnemyBullet = false; }
        }
        if(soundPlayerHit){ s.getUpdateSound().queueSound();soundPlayerHit = false; }
        if(soundGameOver){ go.getUpdateSound().queueSound();soundGameOver = false; }
        if(soundVictory){ v.getUpdateSound().queueSound();soundVictory = false; }
    }

    /**
     * This method is used to start a new level.
     * @param s The Score, used to check and edit the lives and level.
     * @param go The gameOver, used to end the game if necessary.
     * @param p The player ship.
     * @param v the victory, used to win the game if necessary.
     */
    public void newLevel(Score s, GameOver go,PlayerShip p, Victory v) {
        if (s.level < s.bossLevel && s.lives > 0) {
            nextLevel = true;
            isPaused = true;
            s.scorePaused = true;
            s.level++;
            s.amount += 2;
            s.lines += 1;
            Restart(p);
        }else {
            gameOver(s, go, v);
        }
    }
    /**
     * This method is used to end the game.
     * @param s The Score, used to check and edit the lives and level.
     * @param go The gameOver, used to end the game if necessary.
     * @param v the victory, used to win the game if necessary.
     */
    public void gameOver(Score s, GameOver go,Victory v){
        int endScore = calculateScore(s);
        go.setLives(s.lives);   // Used to display the lives even when its game over.
        go.setScore(endScore);  // Used to display the score even when its game over.
        go.setLevel(s.level);   // Used to display the level even when its game over.
        v.setLives(s.lives);    // Used to display the lives even when its game over.
        v.setScore(endScore);   // Used to display the score even when its game over.
        v.setLevel(s.level);    // Used to display the level even when its game over.
        gameOver = true;        // Game over is true when lives are gone or all enemies are dead.
        if(s.lives <= 0){
            soundGameOver = true;
        }else{
            soundVictory = true;
        }
    }
    /**
     * This method is used to calculate the score at the end of the game
     * @param s The Score, used to check the lives and score.
     * @return The final calculated score.
     */
    public int calculateScore(Score s){
        int endScore = s.score;
        if(s.lives > 0) {
            endScore += s.lives * livesPoints;
            endScore += bonusAmount * bonusPoints;
            double bulletAccuracy = (double) bulletHit / (double) (bulletMiss + bulletHit);
            endScore += bulletAccuracy * accuracyPoints;
            endScore += time * timePoints;
        }
        return endScore;
    }
    /**
     * This method is used to restart and refresh at every new level
     * @param p The player ship.
     */
    public void Restart(PlayerShip p){
        enemyShipsList.clear();     // Remove all remaining enemy ships.
        enemyBulletsList.clear();   // Remove all remaining enemy bullets.
        playerBulletsList.clear();  // Remove all remaining player bullets.
        bonusesList.clear();
        specialBulletsList.clear();
        bossShipsList.clear();
        explosionsList.clear();
        p.setPlayerShipX(680);  // Set the playership back in the middle.
        stop = true;            // Stop the movement of the playership.
        bulletBuffer = 35;      // Buffer used so that player cannot 'spam' bullets.
        enemyBuffer = 60;       // Buffer used to determine the timing of the enemy bullets.
        bonusBuffer = 0;        // Buffer used to determine the timing of the bonus.
        bonusDebuffer = 0;      // Buffer used to determine how long the bonus lasts;
        specialBuffer = 0;      // Buffer used to determine the amount of special bullets
        explosionsBuffer = 0;   // Buffer used to determine how long the explosions last.
        bonusActive = false;
        p.invicibiltyPlayer = false;
        freezeBullets = false;
        superSpeed = false;
        p.normalSpeed();
    }

    /**
     * This method is used to load the config file for the game.
     * @param p The player ship.
     * @param s The Score.
     * @throws IOException If the config file is not read correctly.
     */
    public void loadConfig(PlayerShip p, Score s) throws IOException {
        Properties properties = new Properties();
        String configFile = "src/be/uantwerpen/fti/ei/sprites/config";
        InputStream inputStream;
        inputStream = new FileInputStream(configFile);
        properties.load(inputStream);
        screenWidth = (Integer.parseInt(properties.getProperty("screenWidth")));
        screenHeight = (Integer.parseInt(properties.getProperty("screenHeight")));
        // PlayerShip
        p.setPlayerShipX(Integer.parseInt(properties.getProperty("playerShipX")));
        p.setPlayerShipY(Integer.parseInt(properties.getProperty("playerShipY")));
        p.setPlayerShipWidth(Integer.parseInt(properties.getProperty("playerShipWidth")));
        p.setPlayerShipHeight(Integer.parseInt(properties.getProperty("playerShipHeight")));
        p.setPlayerShipBegin(Integer.parseInt(properties.getProperty("playerShipBegin")));
        p.setPlayerShipEnd(Integer.parseInt(properties.getProperty("playerShipEnd")));
        // Enemy Ships
        enemyShipX = (Integer.parseInt(properties.getProperty("enemyShipX")));
        enemyShipY = (Integer.parseInt(properties.getProperty("enemyShipY")));
        enemyShipWidth = (Integer.parseInt(properties.getProperty("enemyShipWidth")));
        enemyShipHeight = (Integer.parseInt(properties.getProperty("enemyShipHeight")));
        // Boss Ship
        bossShipX = (Integer.parseInt(properties.getProperty("bossShipX")));
        bossShipY = (Integer.parseInt(properties.getProperty("bossShipY")));
        bossShipWidth = (Integer.parseInt(properties.getProperty("bossShipWidth")));
        bossShipHeight = (Integer.parseInt(properties.getProperty("bossShipHeight")));
        // Player Bullets
        playerBulletX = (Integer.parseInt(properties.getProperty("playerBulletX")));
        playerBulletY = (Integer.parseInt(properties.getProperty("playerBulletY")));
        playerBulletWidth = (Integer.parseInt(properties.getProperty("playerBulletWidth")));
        playerBulletHeight = (Integer.parseInt(properties.getProperty("playerBulletHeight")));
        // Enemy Bullets
        enemyBulletX = (Integer.parseInt(properties.getProperty("enemyBulletX")));
        enemyBulletY = (Integer.parseInt(properties.getProperty("enemyBulletY")));
        enemyBulletWidth = (Integer.parseInt(properties.getProperty("enemyBulletWidth")));
        enemyBulletHeight = (Integer.parseInt(properties.getProperty("enemyBulletHeight")));
        bossBulletWidth = (Integer.parseInt(properties.getProperty("bossBulletWidth")));
        bossBulletHeight = (Integer.parseInt(properties.getProperty("bossBulletHeight")));
        // Special Bullets
        specialBulletX = (Integer.parseInt(properties.getProperty("specialBulletX")));
        specialBulletY = (Integer.parseInt(properties.getProperty("specialBulletY")));
        specialBulletWidth = (Integer.parseInt(properties.getProperty("specialBulletWidth")));
        specialBulletHeight = (Integer.parseInt(properties.getProperty("specialBulletHeight")));
        // Bonus
        bonusX = (Integer.parseInt(properties.getProperty("bonusX")));
        bonusY = (Integer.parseInt(properties.getProperty("bonusY")));
        bonusWidth = (Integer.parseInt(properties.getProperty("bonusWidth")));
        bonusHeight = (Integer.parseInt(properties.getProperty("bonusHeight")));
        // Score
        s.setLives(Integer.parseInt(properties.getProperty("lives")));                          // Used for choosing the amount of lives.
        s.setScore(Integer.parseInt(properties.getProperty("score")));                          // Used for choosing the starting score
        s.setLevel(Integer.parseInt(properties.getProperty("level")));                          // Used for choosing the starting level.
        s.setBossLevel(Integer.parseInt(properties.getProperty("bossLevel")));                  // Used for choosing the amount of levels.
        s.setAmount(Integer.parseInt(properties.getProperty("amount")));                        // Used for choosing the amount of enemies.
        s.setLines(Integer.parseInt(properties.getProperty("lines")));                          // Used for choosing the amount of enemies.
        s.setBossLives(Integer.parseInt(properties.getProperty("bossLives")));
        // explosions
        explosionsX = (Integer.parseInt(properties.getProperty("explosionsX")));
        explosionsY = (Integer.parseInt(properties.getProperty("explosionsY")));
        explosionsWidth = (Integer.parseInt(properties.getProperty("explosionsWidth")));
        explosionsHeight = (Integer.parseInt(properties.getProperty("explosionsHeight")));
        // Shield
        p.setShieldWidth(Integer.parseInt(properties.getProperty("shieldWidth")));
        p.setShieldHeight(Integer.parseInt(properties.getProperty("shieldHeight")));
        // Timing Buffers
        bulletTimer = (Integer.parseInt(properties.getProperty("bulletTimer")));                // Used for timing player bullets.
        freezeBulletAmount = (Integer.parseInt(properties.getProperty("freezeBulletAmount")));  // Used for setting the amount of freeze bullets.
        enemyBulletTimer = (Integer.parseInt(properties.getProperty("enemyBulletTimer")));      // Used for timing enemy bullets.
        bonusMin = (Integer.parseInt(properties.getProperty("bonusMin")));                      // Used for timing bonuses.
        bonusMax = (Integer.parseInt(properties.getProperty("bonusMax")));                      // Used for timing bonuses.
        deBonusMin = (Integer.parseInt(properties.getProperty("deBonusMin")));                  // Used for timing bonuses.
        deBonusMax = (Integer.parseInt(properties.getProperty("deBonusMax")));                  // Used for timing bonuses.
        explosionsTimer = (Integer.parseInt(properties.getProperty("explosionsTimer")));        // Used for timing explosions.
        bonusMinX = (Integer.parseInt(properties.getProperty("bonusMinX")));                    // Used for placing bonuses.
        bonusMaxX =  (Integer.parseInt(properties.getProperty("bonusMaxX")));                   // Used for placing bonuses.
        enemySpaceX = (Integer.parseInt(properties.getProperty("enemySpaceX")));                // Used for placing enemies (X space between two enemies).
        enemySpaceY = (Integer.parseInt(properties.getProperty("enemySpaceY")));                // Used for placing enemies (Y space between two enemies).
        enemiesMoveUp = (Integer.parseInt(properties.getProperty("enemiesMoveUp")));            // Used for moving enemies up.
        // Score
        accuracyPoints = (Integer.parseInt(properties.getProperty("accuracyPoints")));          // Used for calculating score.
        bonusPoints = (Integer.parseInt(properties.getProperty("bonusPoints")));                // Used for calculating score.
        livesPoints = (Integer.parseInt(properties.getProperty("livesPoints")));                // Used for calculating score.
        timePoints = (Integer.parseInt(properties.getProperty("timePoints")));                  // Used for calculating score.

    }
}