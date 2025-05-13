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
	private int name;
	private double angle;
	private int playerNum = 0;
	private boolean bPressed = false;
	vector Vector = new vector(3*Math.PI/2, 0);
	vector jump = new vector(3*Math.PI/2, 2);
	vector gravity = new vector(Math.PI/2, 1);
	vector right = new vector(0, 2);
	vector left = new vector(Math.PI, 2);



		
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

	public void getPlayerImage() {
		try {
			sprite = ImageIO.read(new File("enTiddies/img/"+ direction +"Reese.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void update() {
		if((direction == "left" ? keyH.sPressed : keyH.downPressed) && !bPressed){
			if(playerNum == 0)playerNum = 1; else playerNum = 0;
			bPressed = true;
		}
		if((direction == "left" ? !keyH.sPressed : !keyH.downPressed)){
			bPressed = false;
		}

		//System.out.println(x);
		//Vector.setAngle(Vector.getAngle()+0.01);
		Vector.addVector(new vector(Vector.getXVelocity() > 0 ? Math.PI : 0, Vector.getXVelocity() > 0 ? 0.1*Vector.getXVelocity() : -0.1*Vector.getXVelocity()));
		if((direction == "left" ? keyH.aPressed : keyH.leftPressed) && name%2 == playerNum){
			Vector.addVector(y<450 ? new vector(Math.PI, 1): left);
		}
		if((direction == "left" ? keyH.dPressed : keyH.rightPressed) && name%2 == playerNum){
			Vector.addVector(y<450 ? new vector(0, 1): right);
		}


		if(x <= 0 || x >= 1830){
			Vector.addVector(new vector(Vector.getXVelocity() > 0 ? Math.PI : 0, Vector.getXVelocity() > 0 ? 2.7*Vector.getXVelocity() : -2.7*Vector.getXVelocity()));
		}

		
		if((direction == "left" ? keyH.wPressed : keyH.upPressed)  && (Vector.getYVelocity() < 0 || y>=450)  && name%2 == playerNum){
			Vector.addVector(jump);
		}


		if(y<450){
			Vector.addVector(gravity);
			gravity.setVelocity(gravity.getVelocity()+0.05);
		}else if(y>450){
			gravity.setVelocity(1);
			y = 450;
			if(Vector.getYVelocity() > 0){
				Vector.addVector(new vector(3*Math.PI/2, 1.3*Vector.getYVelocity()));
				//Vector.setVelocity(-0.3*Vector.getVelocity());
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
		
	}
	public void draw(Graphics2D g2) {
		BufferedImage image = sprite;
        AffineTransform originalTransform = g2.getTransform();
		//g2.rotate(Vector.getAngle(), x, y+300);
		g2.drawImage(image, x, y, 100, 300, null);
		g2.setTransform(originalTransform);
	}
}
