package main;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
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
	
	//creates the player and key listner
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
 
	
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

	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // clears the screen
		Graphics2D g2 = (Graphics2D)g; // casts to Graphics2D
		g2.dispose(); // clears the graphics2D resources
		// see the player class for more
	}
	

}
