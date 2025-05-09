package enTiddies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;
import java.awt.geom.AffineTransform;

/**
 * The {@code arm} class represents a player's arm entity in the game.
 * It handles the graphical representation and movement behavior of the arm.
 * This class is dependent on the player's current direction and position.
 */
public class arm extends entity {

    GamePanel gp;
    KeyHandler keyH;
    private String direction;
    private Player player;
    private double velocity = 0;
    private double angle = 0;

    /**
     * Constructs an arm instance linked to a player, using the game panel and key handler.
     *
     * @param gp     The game panel containing the rendering context.
     * @param keyH   The key handler used for input.
     * @param player The player object to which this arm is associated.
     */
    public arm(GamePanel gp, KeyHandler keyH, Player player) {
        this.player = player;
        this.gp = gp;
        this.keyH = keyH;
        this.direction = player.direction;
        x = player.startX;
        y = player.startY;
        getImage();
    }

    /**
     * Loads the sprite image of the arm based on the player's direction.
     * The image is read from the "enTiddies/img/" directory.
     */
    public void getImage() {
        try {
            sprite = ImageIO.read(new File("enTiddies/img/" + direction + "ReeseArm.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the arm's angle based on the player's vertical position and direction.
     * The angle calculation includes a ternary expression for flipping and clamping values.
     */
    public void update() {
        angle = direction == "left" 
            ? -1 + 0.1 * player.y > 40.8 ? -1 + 0.1 * player.y : 40.8 
            : 1 - 0.1 * player.y < -40.8 ? 1 - 0.1 * player.y : -40.8;
    }

    /**
     * Draws the arm sprite on the screen with a rotation applied based on the angle.
     *
     * @param g2 The Graphics2D context used for rendering.
     */
    public void draw(Graphics2D g2) {
        BufferedImage image = sprite;
        AffineTransform originalTransform = g2.getTransform();
        g2.rotate(angle, direction == "left" ? player.x + 10 : player.x + 90, player.y + 85);
        g2.drawImage(image, direction == "left" ? player.x - 10 : player.x + 70, player.y + 85, 5 * 7, 20 * 7, null);
        g2.setTransform(originalTransform);
    }
}