package enTiddies;

import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.*;

/**
 * The {@code Background} class manages the background and ground images
 * used in the game environment. It can switch between different visual
 * themes (e.g. ballroom and dirt backgrounds) and updates the background
 * image based on a frame counter.
 */
public class Background {

    private ArrayList<BufferedImage> back;
    private ArrayList<BufferedImage> ground;
    private GamePanel gp;
    private BufferedImage sprite;
    
    /** Flag to determine whether to use background images or ground images. */
    public boolean backgroundBol = true;
    
    private int index;

    /**
     * Constructs a {@code Background} instance, initializing the image lists
     * and loading all background and ground images.
     *
     * @param gp   The game panel context, used for rendering dimensions and frame control.
     * @param keyH The key handler for input (not currently used in this class).
     */
    public Background(GamePanel gp) {
        back = new ArrayList<>();
        ground = new ArrayList<>();
        try {
            // Load background images
            back.add(ImageIO.read(new File("enTiddies/img/backgrounds/ballrooms_1.jpg")));
            back.add(ImageIO.read(new File("enTiddies/img/backgrounds/ballrooms_2.jpg")));
            back.add(ImageIO.read(new File("enTiddies/img/backgrounds/ballrooms_3.jpg")));
            back.add(ImageIO.read(new File("enTiddies/img/backgrounds/ballrooms_4.jpg")));
            back.add(ImageIO.read(new File("enTiddies/img/backgrounds/ballrooms_5.jpg")));
            back.add(ImageIO.read(new File("enTiddies/img/backgrounds/ballrooms_6.jpg")));

            // Load ground images
            ground.add(ImageIO.read(new File("enTiddies/img/backgrounds/dirt_1.jpg")));
            ground.add(ImageIO.read(new File("enTiddies/img/backgrounds/dirt_2.jpg")));
            ground.add(ImageIO.read(new File("enTiddies/img/backgrounds/dirt_3.jpg")));
            ground.add(ImageIO.read(new File("enTiddies/img/backgrounds/dirt_4.jpg")));
            ground.add(ImageIO.read(new File("enTiddies/img/backgrounds/dirt_5.jpg")));
            ground.add(ImageIO.read(new File("enTiddies/img/backgrounds/dirt_6.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.gp = gp;
    }

    /**
     * Updates the background image by cycling through the available images.
     */
    public void update() {
        changeImage();
    }

    /**
     * Determines which set of images to use based on the {@code backgroundBol} flag.
     * Also resets the frame counter in the game panel.
     *
     * @return The list of images to be used for rendering.
     */
    public ArrayList<BufferedImage> backgroundType() {
        gp.frameCounter = 0;
        return backgroundBol ? back : ground;
    }

    /**
     * Changes the currently displayed image by moving to the next image
     * in the list. Loops back to the start after reaching the end.
     */
    public void changeImage() {
        index++;
        if (index >= 6) {
            index = 0;
        }
        System.out.println(index);
        sprite = backgroundType().get(index);
    }

    /**
     * Draws the current background image onto the game panel.
     *
     * @param g2 The {@code Graphics2D} context used for drawing the image.
     */
    public void draw(Graphics2D g2) {
        BufferedImage image = sprite;
        g2.drawImage(image, 0, 0, gp.screenWidth, gp.screenHeight, null);
    }
}