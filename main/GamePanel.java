package main;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import enTiddies.Player;
import enTiddies.Background;
import enTiddies.Ball;
import enTiddies.arm;

public class GamePanel extends JPanel implements Runnable{
	
	//Statics for the game window size and FPS
	final int ogTileSize= 16;
	final int scale = 3;
	public final int tileSize = ogTileSize * scale;
	final int maxScreenCol = 48;
	final int maxScreenRow = 32;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	int FPS = 60;
	public int frameCounter = 0;
	public final int resetTImeSec = 10;

	public final int floorY = 0;
	public final int leftWall = 0;
	public final int rightWall = 2000;
	
	//creates the player and key listner
	public KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	Background background = new Background(this, keyH);
	Player player1 = new Player(this, keyH, "left", 400, 450, 1);
	Player player2 = new Player(this, keyH, "left", 700, 450, 2);
	Player player3 = new Player(this, keyH, "right", 1000, 450, 3);
	Player player4 = new Player(this, keyH, "right", 1300, 450, 4);
	arm arm1 = new arm(this, keyH, player1);
	arm arm2 = new arm(this, keyH, player2);
	arm arm3 = new arm(this, keyH, player3);
	arm arm4 = new arm(this, keyH, player4);
	Ball ball = new Ball(this, 850, 300);
 
	
	//window constructor based off our variables
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground( new Color(1, 1, 1));
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	//this starts the game loop
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	// this is the run op
	@Override
	public void run() {
		//sound.setSound(4);
		//sound.play();
		//sound.loop();
		double drawInterval = 1000000000/FPS; 
		double nextDrawTime = System.nanoTime() + drawInterval;
		// this is te game loop
		while(gameThread != null ) {
			//this creates the frame and updates the game
			update();
			//redraws everything on the window
			repaint();
			
			
			// this is the wait
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				// this  is just to remsiningTime doesn't become negative and resets to 0
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void update() {
		frameCounter++;
		player1.update();
		player2.update();
		player3.update();
		player4.update();
		arm1.update();
		arm2.update();
		arm3.update();
		arm4.update();
		ball.update(new Player[]{player1, player2, player3, player4}, new arm[]{arm1, arm2, arm3, arm4});
		if (frameCounter >= 60 * resetTImeSec) {
			background.update();
		}
		if (keyH.pPressed) {
			background.backgroundBol = !background.backgroundBol;
			background.backgroundType();
			background.update();
		}
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // clears the screen
		Graphics2D g2 = (Graphics2D)g; // casts to Graphics2D
		background.draw(g2);
		player1.draw(g2);
		player2.draw(g2);
		player3.draw(g2);
		player4.draw(g2);
		arm1.draw(g2);
		arm2.draw(g2);
		arm3.draw(g2);
		arm4.draw(g2);
		ball.draw(g2);
		g2.dispose(); // clears the graphics2D resources
		// see the player class for more
	}
	

}
