package enTiddies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class Ball extends entity {
	
	GamePanel gp;
	private int startX;
	private int startY;

    private double velocity = 0;
    private double dx = 0;
    private final int WIDTH = 100;
    private final int HEIGHT = 100;

    public Ball(GamePanel gp, int startX, int startY) {
        this.gp = gp;
        x = startX;
        y = startY;
        getImage();
    }

    public void getImage() {
        try {
            sprite = ImageIO.read(new File("enTiddies/img/ball.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update(Player[] players, arm[] arms) {

        if (y + HEIGHT >= 650) {
            y = 650 - HEIGHT;
            if (Math.abs(velocity) <= 6) {
                velocity = 0;
            } else {
                velocity = -velocity * 0.8;
            }
        } else {
            velocity += 0.5;
        }

        if (x <= 0 || x + WIDTH >= 1920) {
            dx = -dx * 0.8;
            if (x <= 0) x = 0;
            if (x + WIDTH >= 1920) x = 1920 - WIDTH;
        }

        dx *= 0.98;
        if (Math.abs(dx) < 0.1) dx = 0;

        Rectangle ballRect = new Rectangle(x, y, WIDTH, HEIGHT);
        for (Player player : players) {
            Rectangle playerRect = new Rectangle(player.x, player.y, 100, 300);
            if (ballRect.intersects(playerRect)) {
                if (x + WIDTH / 2 < player.x + 50) {
                    dx = -Math.abs(dx) - 2;
                } else {
                    dx = Math.abs(dx) + 2;
                }
                velocity = -Math.abs(velocity) * 0.8;
                break;
            }
        }
     Shape ballRect2 = new Rectangle2D.Double(x, y, WIDTH, HEIGHT);
//oh my days
for (arm arm : arms) {
    int width = 5 * 7;
    int height = 20 * 7;
    int armX = arm.direction.equals("left") ? arm.player.x - 10 : arm.player.x + 70;
    int armY = arm.player.y + 85;

    Rectangle2D baseRect = new Rectangle2D.Double(armX, armY, width, height);

    int pivotX = arm.direction.equals("left") ? arm.player.x + 10 : arm.player.x + 90;
    int pivotY = arm.player.y + 85;

    AffineTransform transform = AffineTransform.getRotateInstance(arm.angle, pivotX, pivotY);
    Shape rotatedArmRect = transform.createTransformedShape(baseRect);

    if (rotatedArmRect.intersects(ballRect2.getBounds2D())) {
        if (y + HEIGHT / 2 < arm.y + 50) {
            velocity = -Math.abs(velocity) - 5;
        } else {
            velocity = Math.abs(velocity) + 5;
        }
        dx = -Math.abs(dx) * 0.8;
        break;
    }
    if(Math.abs(velocity) > 16.5)velocity = Math.signum(velocity)*16.5;
}
		y += velocity;
        x += dx;
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = sprite;
        g2.drawImage(image, x, y, WIDTH, HEIGHT, null);
    
    }
    
}
