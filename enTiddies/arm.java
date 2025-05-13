package enTiddies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;
import java.awt.geom.AffineTransform;

public class arm extends entity{
	
	GamePanel gp;
	KeyHandler keyH;
	public String direction;
	public Player player;
    private double velocity = 0;
    public double angle = 0;

		
		public arm(GamePanel gp, KeyHandler keyH, Player player) {
            this.player = player;
			this.gp = gp;
			this.keyH = keyH;
			this.direction = player.direction;
			x = player.startX;
			y = player.startY;
			getImage();
	}

	public void getImage() {
		try {
			sprite = ImageIO.read(new File("enTiddies/img/"+ direction +"ReeseArm.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void update() {
		x = direction=="left" ? player.x-10 : player.x+70;
		y = player.y+85;
		angle = direction=="left" ? -1 + 0.1*player.y > 40.8 ? -1 + 0.1*player.y : 40.8 : 1 - 0.1*player.y < -40.8 ? 1 - 0.1*player.y : -40.8;
	}
	public void draw(Graphics2D g2) {
		BufferedImage image = sprite;
        AffineTransform originalTransform = g2.getTransform();
        g2.rotate(angle, direction=="left" ? player.x+10 : player.x+90, y);
		g2.drawImage(image, x, y, 5*7, 20*7, null);
        g2.setTransform(originalTransform);
	}
}
