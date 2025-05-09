package enTiddies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import java.awt.Rectangle;

/**
 * The {@code Ball} class represents a physics-driven ball entity in the game.
 * It handles its own movement, collision with players and arms, and bouncing logic.
 */
public class Ball extends entity {

    private GamePanel gp;

    private double velocity = 0;
    private double dx = 0;

    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;

    /**
     * Constructs a {@code Ball} object with a given starting position.
     *
     * @param gp     The game panel in which the ball exists.
     * @param startX The initial X coordinate of the ball.
     * @param startY The initial Y coordinate of the ball.
     */
    public Ball(GamePanel gp, int startX, int startY) {
        this.gp = gp;
        x = startX;
        y = startY;
        getImage();
    }

    /**
     * Loads the ball's image from disk.
     */
    public void getImage() {
        try {
            sprite = ImageIO.read(new File("enTiddies/img/ball.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the ball's position based on physics (gravity, velocity),
     * and handles collisions with players and arms.
     *
     * @param players Array of players to check collision with.
     * @param arms    Array of arms to check collision with.
     */
    public void update(Player[] players, arm[] arms) {
        // Handle vertical ground collision
        if (y + HEIGHT >= 650) {
            y = 650 - HEIGHT;
            if (Math.abs(velocity) <= 6) {
                velocity = 0;
            } else {
                velocity = -velocity * 0.8;
            }
        } else {
            velocity += 0.5; // gravity
        }

        // Handle wall collisions
        if (x <= 0 || x + WIDTH >= 1920) {
            dx = -dx * 0.8;
            if (x <= 0) x = 0;
            if (x + WIDTH >= 1920) x = 1920 - WIDTH;
        }

        // Friction for horizontal movement
        dx *= 0.98;
        if (Math.abs(dx) < 0.1) dx = 0;

        // Collision with players
        Rectangle ballRect = new Rectangle(x, y, WIDTH, HEIGHT);
        for (Player player : players) {
            Rectangle playerRect = new Rectangle(player.x, player.y, 100, 300);
            if (ballRect.intersects(playerRect)) {
                if (x + WIDTH / 2 < player.x + 50) {
                    dx = -Math.abs(dx) - 2;
                } else {
                    dx = Math.abs(dx) + 2;
                }
                velocity = -Math.abs(velocity) * 0.8;
                break;
            }
        }

        // Collision with arms
        for (arm arm : arms) {
            Rectangle armRect = new Rectangle(arm.x, arm.y, 5 * 7, 20 * 7);
            if (ballRect.intersects(armRect)) {
                if (y + HEIGHT / 2 < arm.y + 50) {
                    velocity = -Math.abs(velocity) - 20;
                } else {
                    velocity = Math.abs(velocity) + 20;
                }
                dx = -Math.abs(dx) * 0.8;
                break;
            }
        }

        // Apply movement
        y += velocity;
        x += dx;
    }

    /**
     * Draws the ball sprite onto the screen.
     *
     * @param g2 The {@code Graphics2D} context used for rendering.
     */
    public void draw(Graphics2D g2) {
        BufferedImage image = sprite;
        g2.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }
}