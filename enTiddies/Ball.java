package enTiddies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;


public class Ball extends entity{
	
	GamePanel gp;
	private int startX;
	private int startY;

    private double velocity = 0;
		
		public Ball(GamePanel gp, int startX, int startY) {
			this.gp = gp;
			x = startX;
			y = startY;
			getImage();
	}

	public void getImage() {
		try {
			sprite = ImageIO.read(new File("enTiddies/img/ball.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void update() {
		

        if (y >= 550) {
            y = 550;
			if(Math.abs(velocity) <= 6){
				velocity = 0;
			}else{
				velocity = -velocity * 0.8;
			}
		}else{
			velocity += 0.5;
		}
		y += velocity;

		System.out.println(velocity + "   " + y);
	}
	public void draw(Graphics2D g2) {
		BufferedImage image = sprite;
		g2.drawImage(image, x, y, 100, 100, null);
	}
}
