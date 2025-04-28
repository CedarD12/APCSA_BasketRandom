package enTiddies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;


public class Player extends entity{
	
	GamePanel gp;
	KeyHandler keyH;
	private String direction;
	private int startX;
	private int startY;
    private double velocity = 0;

		
		public Player(GamePanel gp, KeyHandler keyH, String direction, int startX, int startY) {
			this.gp = gp;
			this.keyH = keyH;
			this.direction = direction;
			x = startX;
			y = startY;
			getPlayerImage();
	}

	public void getPlayerImage() {
		try {
			sprite = ImageIO.read(new File("enTiddies/img/" + direction +"Head.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void update() {
		if (direction == "left"){
			if(keyH.wPressed){
				y-- ;
			}else if(y<450){
				y++;
			}
		}else{
			if(keyH.upPressed){
				y-- ;
			}else if(y<450){
				y++;
			}
		}
		if(!keyH.wPressed && !keyH.upPressed)
		if (y >= 450) {
            y = 450;
			if(Math.abs(velocity) <= 6){
				velocity = 0;
			}else{
				velocity = -velocity * 0.8;
			}
		}else{
			velocity += 0.5;
		}
		y += velocity;
	}
	public void draw(Graphics2D g2) {
		BufferedImage image = sprite;
		g2.drawImage(image, x, y, 200, 200, null);
	}
}
