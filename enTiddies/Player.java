package enTiddies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;
import enTiddies.vector;
import java.awt.geom.AffineTransform;


/**
 * The {@code Player} class represents a player character in the game.
 * It handles movement, direction, jumping, gravity, and interaction with key input.
 * The player uses vector-based motion to simulate physics-like behavior.
 */
public class Player extends entity {

    GamePanel gp;
    KeyHandler keyH;

    /** Player's facing direction ("left" or "right"). */
    public String direction;

    /** Initial X and Y positions of the player. */
    public int startX;
    public int startY;

    /** Internal ID or reference to differentiate players. */
    private int name;

    private double angle;
    private int playerNum = 0;
    private boolean bPressed = false;

    // Vectors for movement control
    vector Vector = new vector(3 * Math.PI / 2, 0);
    vector jump = new vector(3 * Math.PI / 2, 2);
    vector gravity = new vector(Math.PI / 2, 1);
    vector right = new vector(0, 2);
    vector left = new vector(Math.PI, 2);

    /**
     * Constructs a new {@code Player} object.
     *
     * @param gp        The game panel reference.
     * @param keyH      The key handler for input detection.
     * @param direction The initial facing direction ("left" or "right").
     * @param startX    Initial X position.
     * @param startY    Initial Y position.
     * @param name      Unique identifier for the player.
     */
    public Player(GamePanel gp, KeyHandler keyH, String direction, int startX, int startY, int name) {
        this.gp = gp;
        this.keyH = keyH;
        this.direction = direction;
        this.name = name;
        x = startX;
        y = startY;
        angle = 5;
        getPlayerImage();
    }

    /**
     * Loads the player's sprite image based on the current direction.
     */
    public void getPlayerImage() {
        try {
            sprite = ImageIO.read(new File("enTiddies/img/" + direction + "Reese.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the player's position and vector based on input,
     * gravity, jumping, and environmental collisions.
     */
    public void update() {
        // Toggle between two states (used for shared keyboard handling?)
        if ((direction.equals("left") ? keyH.sPressed : keyH.downPressed) && !bPressed) {
            playerNum = (playerNum == 0) ? 1 : 0;
            bPressed = true;
        }
        if ((direction.equals("left") ? !keyH.sPressed : !keyH.downPressed)) {
            bPressed = false;
        }

        // Simulated friction/resistance vector
        Vector.addVector(new vector(
            Vector.getXVelocity() > 0 ? Math.PI : 0,
            Vector.getXVelocity() > 0 ? 0.1 * Vector.getXVelocity() : -0.1 * Vector.getXVelocity()
        ));

        // Movement input
        if ((direction.equals("left") ? keyH.aPressed : keyH.leftPressed) && name % 2 == playerNum) {
            Vector.addVector(y < 450 ? new vector(Math.PI, 1) : left);
        }
        if ((direction.equals("left") ? keyH.dPressed : keyH.rightPressed) && name % 2 == playerNum) {
            Vector.addVector(y < 450 ? new vector(0, 1) : right);
        }

        // Wall bounce
        if (x <= 0 || x >= 1830) {
            Vector.addVector(new vector(
                Vector.getXVelocity() > 0 ? Math.PI : 0,
                Vector.getXVelocity() > 0 ? 2.7 * Vector.getXVelocity() : -2.7 * Vector.getXVelocity()
            ));
        }

        // Jump input
        if ((direction.equals("left") ? keyH.wPressed : keyH.upPressed)
                && (Vector.getYVelocity() < 0 || y >= 450)
                && name % 2 == playerNum) {
            Vector.addVector(jump);
        }

        // Gravity logic
        if (y < 450) {
            Vector.addVector(gravity);
            gravity.setVelocity(gravity.getVelocity() + 0.05);
        } else if (y > 450) {
            gravity.setVelocity(1);
            y = 450;
            if (Vector.getYVelocity() > 0) {
                Vector.addVector(new vector(3 * Math.PI / 2, 1.3 * Vector.getYVelocity()));
                if (Math.abs(Vector.getVelocity()) <= 2) {
                    Vector.setVelocity(0);
                }
            }
        }

        // Cap velocity
        if (Math.abs(Vector.getVelocity()) >= 20) {
            Vector.setVelocity(Math.signum(Vector.getVelocity()) * 20);
        }

        // Apply movement
        x += Vector.getXVelocity();
        y += Vector.getYVelocity();
    }

    /**
     * Draws the player sprite on the screen.
     *
     * @param g2 The {@code Graphics2D} object used for rendering.
     */
    public void draw(Graphics2D g2) {
        BufferedImage image = sprite;
        AffineTransform originalTransform = g2.getTransform();
        g2.drawImage(image, x, y, 100, 300, null);
        g2.setTransform(originalTransform);
    }
}