package enTiddies;

import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.*;

public class Background {
    private ArrayList<BufferedImage> back;
    private ArrayList<BufferedImage> ground;
    private GamePanel gp;
    private BufferedImage sprite;
    private int frameCounter;
    private final int resetTimeSec = 10;
    private KeyHandler keyH;
    private boolean background = true;
    private int index;

    public Background(GamePanel gp, KeyHandler keyH) {
        back = new ArrayList<>();
        ground = new ArrayList<>();
        try {
            back.add(ImageIO.read(new File("enTiddies/img/backgrounds/ballrooms_1.jpg")));
            back.add(ImageIO.read(new File("enTiddies/img/backgrounds/ballrooms_2.jpg")));
            back.add(ImageIO.read(new File("enTiddies/img/backgrounds/ballrooms_3.jpg")));
            back.add(ImageIO.read(new File("enTiddies/img/backgrounds/ballrooms_4.jpg")));
            back.add(ImageIO.read(new File("enTiddies/img/backgrounds/ballrooms_5.jpg")));
            back.add(ImageIO.read(new File("enTiddies/img/backgrounds/ballrooms_6.jpg")));

            ground.add(ImageIO.read(new File("enTiddies/img/backgrounds/dirt_1.jpg")));
            ground.add(ImageIO.read(new File("enTiddies/img/backgrounds/dirt_2.jpg")));
            ground.add(ImageIO.read(new File("enTiddies/img/backgrounds/dirt_3.jpg")));
            ground.add(ImageIO.read(new File("enTiddies/img/backgrounds/dirt_4.jpg")));
            ground.add(ImageIO.read(new File("enTiddies/img/backgrounds/dirt_5.jpg")));
            ground.add(ImageIO.read(new File("enTiddies/img/backgrounds/dirt_6.jpg")));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.gp = gp;
        this.keyH = keyH;
    }

    public void update() {
        if (frameCounter >= resetTimeSec * 60) {
            frameCounter = 0;
            changeImage();
        } else frameCounter++;

        if (keyH.pPressed) {
            frameCounter = 0;
            background = !background;
        }
    }

    public void changeImage() {
        index = index >= 5 ?  0 : index++;
        sprite = background ? back.get(index) : ground.get(index);
    }

    public void draw(Graphics2D g2) {
		BufferedImage image = sprite;
		g2.drawImage(image, 0, 0, gp.screenWidth, gp.screenHeight,  null);
	}
}
