package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	public boolean upPressed, wPressed, rightPressed, leftPressed, aPressed, dPressed, sPressed, downPressed, pPressed;

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
    
    //checks which WASD keys are being pressed
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_W) {
			wPressed = true;
			
		}


		if(code == KeyEvent.VK_UP) {
			upPressed = true;
			
		}

		if(code == KeyEvent.VK_RIGHT) {
			rightPressed = true;
			
		}
		
		if(code == KeyEvent.VK_LEFT) {
			leftPressed = true;
			
		}

		if(code == KeyEvent.VK_DOWN) {
			downPressed = true;
			
		}

		if(code == KeyEvent.VK_A) {
			aPressed = true;
			
		}

		if(code == KeyEvent.VK_D) {
			dPressed = true;
			
		}

		if(code == KeyEvent.VK_S) {
			sPressed = true;
			
		}

		if(code == KeyEvent.VK_P) {
			pPressed = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_W) {
			wPressed = false;
			
		}

		

		if(code == KeyEvent.VK_UP) {
			upPressed = false;
			
		}

		if(code == KeyEvent.VK_RIGHT) {
			rightPressed = false;
			
		}
		
		if(code == KeyEvent.VK_LEFT) {
			leftPressed = false;
			
		}

		if(code == KeyEvent.VK_DOWN) {
			downPressed = false;
			
		}

		if(code == KeyEvent.VK_A) {
			aPressed = false;
			
		}

		if(code == KeyEvent.VK_D) {
			dPressed = false;
			
		}
		if(code == KeyEvent.VK_S) {
			sPressed = false;
			
		}

		if(code == KeyEvent.VK_P) {
			pPressed = false;
		}
	}
	
}
