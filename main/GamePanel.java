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

/**
 * The {@code GamePanel} class is the main canvas for the game.
 * It initializes the game components (players, arms, ball, background),
 * handles the game loop, updates game state, and renders all graphics.
 */
public class GamePanel extends JPanel implements Runnable {

    // === Game screen and tile settings ===
    final int ogTileSize = 16;
    final int scale = 3;
    public final int tileSize = ogTileSize * scale;
    final int maxScreenCol = 48;
    final int maxScreenRow = 32;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    int FPS = 60;
    public int frameCounter = 0;
    public final int resetTImeSec = 10;

    // === World boundaries ===
    public final int floorY = 0;
    public final int leftWall = 0;
    public final int rightWall = 2000;

    // === Input handler ===
    public KeyHandler keyH = new KeyHandler();

    // === Thread for game loop ===
    Thread gameThread;

    // === Game entities ===
    Background background = new Background(this);
    Player player1 = new Player(this, keyH, "left", 400, 450, 1);
    Player player2 = new Player(this, keyH, "left", 700, 450, 2);
    Player player3 = new Player(this, keyH, "right", 1000, 450, 3);
    Player player4 = new Player(this, keyH, "right", 1300, 450, 4);

    arm arm1 = new arm(this, keyH, player1);
    arm arm2 = new arm(this, keyH, player2);
    arm arm3 = new arm(this, keyH, player3);
    arm arm4 = new arm(this, keyH, player4);

    Ball ball = new Ball(this, 850, 300);

    /**
     * Constructs the {@code GamePanel}, setting up dimensions, background, and key handling.
     */
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(1, 1, 1));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    /**
     * Starts the game loop in a new thread.
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * The main game loop. Controls frame rate, updates game logic, and triggers rendering.
     */
    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();    // Update game logic
            repaint();   // Render frame

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1_000_000; // Convert to milliseconds

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Updates all game entities and handles logic such as background changes and player actions.
     */
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

        ball.update(new Player[]{player1, player2, player3, player4},
                    new arm[]{arm1, arm2, arm3, arm4});

        if (frameCounter >= 60 * resetTImeSec) {
            background.update();
        }

        if (keyH.pPressed) {
            background.backgroundBol = !background.backgroundBol;
            background.backgroundType();
            background.update();
        }
    }

    /**
     * Paints all game elements to the screen using the provided {@code Graphics} object.
     *
     * @param g The graphics context for drawing.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Clear screen
        Graphics2D g2 = (Graphics2D) g;

        // Draw background and game entities
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

        g2.dispose(); // Free graphics context
    }
}