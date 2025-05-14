package enTiddies;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Rectangle;

public class Net {

    public int x, y;
    public int width, height;
    private Image netImage;

    public Net(){
        x = 875;
        y = 380;
        try {
            netImage = ImageIO.read(new File("enTiddies/img/net.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        this.width = netImage.getWidth(null);
        this.height = netImage.getHeight(null);

    }

    // Method to check for ball collision
    public boolean checkCollision(Ball ball) {
        Rectangle netRect = new Rectangle(x, y, width, height);
        Rectangle ballRect = new Rectangle(ball.x, ball.y, 10, 10);

        return netRect.intersects(ballRect); // If the ball's rectangle intersects the net's rectangle, return true
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(netImage, x, y, null);
    }

}
