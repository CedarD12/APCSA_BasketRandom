package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	public boolean upPressed, wPressed;

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
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_W) {
			upPressed = false;
			
		}

		

		if(code == KeyEvent.VK_UP) {
			wPressed = false;
			
		}
	}
	
}
