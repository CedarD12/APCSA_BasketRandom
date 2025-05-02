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


public class Player extends entity{
	
	GamePanel gp;
	KeyHandler keyH;
	public String direction;
	public int startX;
	public int startY;
	private double angle;
	vector Vector = new vector(3*Math.PI/2, 0);
	vector jump = new vector(3*Math.PI/2, 2);
	vector gravity = new vector(Math.PI/2, 1);


		
		public Player(GamePanel gp, KeyHandler keyH, String direction, int startX, int startY) {
			this.gp = gp;
			this.keyH = keyH;
			this.direction = direction;
			x = startX;
			y = startY;
			angle = 5;
			getPlayerImage();
	}

	public void getPlayerImage() {
		try {
			sprite = ImageIO.read(new File("enTiddies/img/"+ direction +"Reese.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void update() {
		//Vector.setAngle(Vector.getAngle()+0.01);
	
		if(direction == "left" ? keyH.wPressed : keyH.upPressed){
			Vector.addVector(jump);
		}
		if(y<450){
			Vector.addVector(gravity);
			gravity.setVelocity(gravity.getVelocity()+0.05);
		}else if(y>450){
			gravity.setVelocity(1);
			y = 450;
			if(Vector.getYVelocity() > 0){
				Vector.setVelocity(-0.3*Vector.getVelocity());
				if(Math.abs(Vector.getVelocity()) <= 2){
					Vector.setVelocity(0);
				}
			}
			
		}
		if(Math.abs(Vector.getVelocity()) >= 20){
			Vector.setVelocity(Math.signum(Vector.getVelocity())*20);
		}
		
		x += Vector.getXVelocity();
		y += Vector.getYVelocity();
		System.out.println(Vector.getYVelocity() + " " + y);
		
	}
	public void draw(Graphics2D g2) {
		BufferedImage image = sprite;
        AffineTransform originalTransform = g2.getTransform();
		//g2.rotate(Vector.getAngle(), x, y+300);
		g2.drawImage(image, x, y, 100, 300, null);
		g2.setTransform(originalTransform);
	}
}
