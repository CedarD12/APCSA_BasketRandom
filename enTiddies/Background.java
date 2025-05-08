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
    private boolean background;
    private int index;

    public Background(GamePanel gp, KeyHandler keyH) {
        try {
            back.add(ImageIO.read(new File("enTiddies/img/backgrounds/ballroom_1")));
            back.add(ImageIO.read(new File("enTiddies/img/backgrounds/ballroom_2")));
            back.add(ImageIO.read(new File("enTiddies/img/backgrounds/ballroom_3")));
            back.add(ImageIO.read(new File("enTiddies/img/backgrounds/ballroom_4")));
            back.add(ImageIO.read(new File("enTiddies/img/backgrounds/ballroom_5")));
            back.add(ImageIO.read(new File("enTiddies/img/backgrounds/ballroom_6")));

            ground.add(ImageIO.read(new File("enTiddies/img/backgrounds/dirt_1")));
            ground.add(ImageIO.read(new File("enTiddies/img/backgrounds/dirt_2")));
            ground.add(ImageIO.read(new File("enTiddies/img/backgrounds/dirt_3")));
            ground.add(ImageIO.read(new File("enTiddies/img/backgrounds/dirt_4")));
            ground.add(ImageIO.read(new File("enTiddies/img/backgrounds/dirt_5")));
            ground.add(ImageIO.read(new File("enTiddies/img/backgrounds/dirt_6")));
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
