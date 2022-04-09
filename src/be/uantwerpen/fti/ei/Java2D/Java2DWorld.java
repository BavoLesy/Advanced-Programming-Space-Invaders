package be.uantwerpen.fti.ei.Java2D;
// Imports used for drawing the sprites
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
/**
 * This class is used to load sprites, to resize them and to create, draw and render the 2d graphics screen.
 * @author Bavo Lesy
 * @version 1.0
 */
public class Java2DWorld {
    // Used to visualise the game.
    private final JFrame frame;
    private final JLabel statusbar;
    private final JPanel panel;
    private BufferedImage image;
    private Graphics2D g2d;
    // All the sprites.
    public BufferedImage BackgroundSprite;
    public BufferedImage PlayerShipSprite;
    public BufferedImage EnemyShipSprite;
    public BufferedImage PlayerBullets1Sprite;
    public BufferedImage EnemyBullets1Sprite;
    public BufferedImage GameOverSprite;
    public BufferedImage EndingSprite;
    public BufferedImage FreezeBulletsSprite;
    public BufferedImage PurpleBonusSprite;
    public BufferedImage YellowBonusSprite;
    public BufferedImage LightBlueBonusSprite;
    public BufferedImage BossShipSprite;
    public BufferedImage BossRedBulletSprite;
    public BufferedImage BlueBulletSprite;
    public BufferedImage ExplosionSprite;
    public BufferedImage GreenBonusSprite;
    public BufferedImage RedBonusSprite;
    public BufferedImage ShieldSprite;
    public BufferedImage HearthSprite;
    public BufferedImage BossHealthBarSprite;
    public BufferedImage RedLineSprite;
    public BufferedImage StartScreenSprite;
    // Read from the config file
    public int playerShipWidth;
    public int playerShipHeight;
    public int enemyShipWidth;
    public int enemyShipHeight;
    public int bossShipWidth;
    public int bossShipHeight;
    public int playerBulletWidth;
    public int playerBulletHeight;
    public int enemyBulletWidth;
    public int enemyBulletHeight;
    public int bossBulletWidth;
    public int bossBulletHeight;
    public int specialBulletWidth;
    public int specialBulletHeight;
    public int bonusWidth;
    public int bonusHeight;
    public int explosionsWidth;
    public int explosionsHeight;
    public int shieldWidth;
    public int shieldHeight;

    public Java2DWorld(){
        frame = new JFrame();
        statusbar = new JLabel();
        panel = new JPanel(true) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Draw(g);
            }
        };
        frame.setFocusable(true); // Make frame focusable.
        frame.add(panel); // Add panel inside the frame.
        frame.setResizable(false); // Make the frame unable to be resized.
        frame.setTitle("Space Invaders Bavo Lesy"); // Title of the frame.
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(this.statusbar, "South"); // Put the statusbar on the bottom of the frame.
    }
    // Getters
    public JFrame getFrame() {
        return frame;
    }
    public Graphics2D getGraphics2d() { //used to get the sprites and draw them in their 2D classes.
        return g2d;
    }
    public JLabel getStatusbar(){ return statusbar;}
    public void render() { panel.repaint(); }   // Constantly keeps repainting the panel, so that it is constantly working.
    /**
     * This method loads the different sprites into our project.
     */
    public void loadSprites() { // Used to load the sprites from their source.
        try {
            PlayerShipSprite = ImageIO.read(new File("src/be/uantwerpen/fti/ei/sprites/PlayerShip.png"));
            PlayerBullets1Sprite = ImageIO.read(new File("src/be/uantwerpen/fti/ei/sprites/GreenBullet2.png"));
            EnemyShipSprite = ImageIO.read(new File("src/be/uantwerpen/fti/ei/sprites/EnemyShip1.png"));
            EnemyBullets1Sprite = ImageIO.read(new File("src/be/uantwerpen/fti/ei/sprites/RedBullet2.png"));
            BackgroundSprite = ImageIO.read(new File("src/be/uantwerpen/fti/ei/sprites/Background.jpg"));
            GameOverSprite = ImageIO.read(new File("src/be/uantwerpen/fti/ei/sprites/GameOver.jpg"));
            EndingSprite = ImageIO.read(new File("src/be/uantwerpen/fti/ei/sprites/ending.png"));
            FreezeBulletsSprite = ImageIO.read(new File("src/be/uantwerpen/fti/ei/sprites/UltimateLaserBeam.png"));
            PurpleBonusSprite = ImageIO.read(new File("src/be/uantwerpen/fti/ei/sprites/PurpleBonus.png"));
            YellowBonusSprite = ImageIO.read(new File("src/be/uantwerpen/fti/ei/sprites/YellowBonus.png"));
            LightBlueBonusSprite = ImageIO.read(new File("src/be/uantwerpen/fti/ei/sprites/LightBlueBonus.png"));
            BossShipSprite = ImageIO.read(new File("src/be/uantwerpen/fti/ei/sprites/BossShip.png"));
            BossRedBulletSprite = ImageIO.read(new File("src/be/uantwerpen/fti/ei/sprites/BossRedBullet.png"));
            BlueBulletSprite = ImageIO.read(new File("src/be/uantwerpen/fti/ei/sprites/BlueBullet.png"));
            ExplosionSprite = ImageIO.read(new File("src/be/uantwerpen/fti/ei/sprites/Explosion.png"));
            GreenBonusSprite = ImageIO.read(new File("src/be/uantwerpen/fti/ei/sprites/GreenBonus.png"));
            RedBonusSprite = ImageIO.read(new File("src/be/uantwerpen/fti/ei/sprites/RedBonus.png"));
            ShieldSprite = ImageIO.read(new File("src/be/uantwerpen/fti/ei/sprites/ShieldHit.png"));
            HearthSprite = ImageIO.read(new File("src/be/uantwerpen/fti/ei/sprites/Hearth.png"));
            BossHealthBarSprite = ImageIO.read(new File("src/be/uantwerpen/fti/ei/sprites/BossHealthBar.png"));
            RedLineSprite = ImageIO.read(new File("src/be/uantwerpen/fti/ei/sprites/RedLine.png"));
            StartScreenSprite = ImageIO.read(new File("src/be/uantwerpen/fti/ei/sprites/StartScreen.png"));
        }
        catch(IOException e){ // Checks for errors with loading the sprites.
            System.out.println("Error!, Sprite did not load: Please check your source files");
        }
    }
    /**
     * This method resizes the image to visualise our screen correctly.
     * @param inputImage The image that is being resized.
     * @param targetWidth The width of the new image.
     * @param targetHeight The height of the new image.
     * @return outputImage The resized image.
     */
    public BufferedImage resizeImage(BufferedImage inputImage, int targetWidth, int targetHeight){ // Used to rescale sprites.
        Image scaledImage = inputImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT); // Scale originalimage to new scaledimage.
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_4BYTE_ABGR_PRE); // Make new buffered image variable.
        outputImage.createGraphics().drawImage(scaledImage, 0, 0, null); // Draw the scaled image into the bufferedImage output.
        return outputImage;
    }
    /**
     * This method draws the image on the screen.
     * @param g Graphics object that contains information needed to draw.
     */
    public void Draw(Graphics g) {  // Draw the images on the screen using G2D.
        Graphics2D graphics2D = (Graphics2D) g; // New variable of graphics g.
        //Toolkit.getDefaultToolkit().sync(); // Sync so that everything is colored at once (also double buffering in java).
        graphics2D.drawImage(image, 0, 0, null);   // Draw the buffered image.
        graphics2D.dispose(); // Empties the garbage collector.
    }
    /**
     * This method sets the frame size and resizes the different sprites to visualise our screen correctly.
     * @param screenWidth The width of the resized sprites.
     * @param screenHeight The height of the resized sprites.
     * @throws IOException If the image is not resized correctly.
     */
    public void resizeSprites(int screenWidth, int screenHeight) throws IOException { // Resize the sprites to get the desired sizes for the game.
        frame.setLocation(200,100); // Set the location of the frame on the screen.
        frame.setSize(screenWidth, screenHeight); // Set the size of the frame.
        loadSprites(); // Load in all the sprites used.
        loadSpriteSizes();
        try {
            BackgroundSprite = resizeImage(BackgroundSprite, frame.getWidth(), frame.getHeight());
            PlayerShipSprite = resizeImage(PlayerShipSprite, playerShipWidth, playerShipHeight);
            PlayerBullets1Sprite = resizeImage(PlayerBullets1Sprite, playerBulletWidth,playerBulletHeight);
            EnemyShipSprite = resizeImage(EnemyShipSprite, enemyShipWidth, enemyShipHeight);
            EnemyBullets1Sprite = resizeImage(EnemyBullets1Sprite,enemyBulletWidth,enemyBulletHeight);
            GameOverSprite = resizeImage(GameOverSprite, frame.getWidth(), frame.getHeight());
            EndingSprite = resizeImage(EndingSprite, frame.getWidth(), frame.getHeight());
            FreezeBulletsSprite = resizeImage(FreezeBulletsSprite, specialBulletWidth,specialBulletHeight);
            PurpleBonusSprite = resizeImage(PurpleBonusSprite, bonusWidth,bonusHeight);
            YellowBonusSprite = resizeImage(YellowBonusSprite, bonusWidth ,bonusHeight);
            LightBlueBonusSprite = resizeImage(LightBlueBonusSprite, bonusWidth,bonusHeight);
            GreenBonusSprite = resizeImage(GreenBonusSprite, bonusWidth,bonusHeight);
            RedBonusSprite = resizeImage(RedBonusSprite,bonusWidth,bonusHeight);
            BossShipSprite = resizeImage(BossShipSprite, bossShipWidth,bossShipHeight);
            BossRedBulletSprite = resizeImage(BossRedBulletSprite, bossBulletWidth,bossBulletHeight);
            BlueBulletSprite = resizeImage(BlueBulletSprite, enemyBulletWidth,enemyBulletHeight);
            ExplosionSprite = resizeImage(ExplosionSprite, explosionsWidth,explosionsHeight);
            ShieldSprite = resizeImage(ShieldSprite,shieldWidth,shieldHeight);
            // UI elements
            HearthSprite = resizeImage(HearthSprite, 35,35);
            BossHealthBarSprite = resizeImage(BossHealthBarSprite,50,50);
            RedLineSprite = resizeImage(RedLineSprite,20,15);
            StartScreenSprite = resizeImage(StartScreenSprite,800,350);

        } catch(Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        image = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_4BYTE_ABGR_PRE);
        g2d = image.createGraphics();

    }
    /**
     * This methods loads the different sprite sizes from the config file to ensure our visualisation is correct.
     * @throws IOException If the config file is not read correctly.
     */
    public void loadSpriteSizes() throws IOException {
        Properties properties = new Properties();
        String configFile = "src/be/uantwerpen/fti/ei/sprites/config";
        InputStream sizeStream;
        sizeStream = new FileInputStream(configFile);
        properties.load(sizeStream);
        playerShipWidth = (Integer.parseInt(properties.getProperty("playerShipWidth")));
        playerShipHeight = (Integer.parseInt(properties.getProperty("playerShipHeight")));
        enemyShipWidth = (Integer.parseInt(properties.getProperty("enemyShipWidth")));
        enemyShipHeight = (Integer.parseInt(properties.getProperty("enemyShipHeight")));
        bossShipWidth = (Integer.parseInt(properties.getProperty("bossShipWidth")));
        bossShipHeight = (Integer.parseInt(properties.getProperty("bossShipHeight")));
        playerBulletWidth = (Integer.parseInt(properties.getProperty("playerBulletWidth")));
        playerBulletHeight = (Integer.parseInt(properties.getProperty("playerBulletHeight")));
        enemyBulletWidth = (Integer.parseInt(properties.getProperty("enemyBulletWidth")));
        enemyBulletHeight = (Integer.parseInt(properties.getProperty("enemyBulletHeight")));
        bossBulletWidth = (Integer.parseInt(properties.getProperty("bossBulletWidth")));
        bossBulletHeight = (Integer.parseInt(properties.getProperty("bossBulletHeight")));
        specialBulletWidth = (Integer.parseInt(properties.getProperty("specialBulletWidth")));
        specialBulletHeight = (Integer.parseInt(properties.getProperty("specialBulletHeight")));
        bonusWidth = (Integer.parseInt(properties.getProperty("bonusWidth")));
        bonusHeight = (Integer.parseInt(properties.getProperty("bonusHeight")));
        explosionsWidth = (Integer.parseInt(properties.getProperty("explosionsWidth")));
        explosionsHeight = (Integer.parseInt(properties.getProperty("explosionsHeight")));
        shieldWidth = (Integer.parseInt(properties.getProperty("shieldWidth")));
        shieldHeight = (Integer.parseInt(properties.getProperty("shieldHeight")));

    }
}

