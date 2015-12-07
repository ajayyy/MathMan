package com.math.game;

import java.awt.event.KeyEvent;

public class KeyHandler {
	static int key;
	public static void keyPressed(KeyEvent e){
		if(Normal.stat){
			key = e.getKeyCode();
			if(key == KeyEvent.VK_DOWN){
				PacMan.down = true;
				PacMan.up = false;
				PacMan.right = false;
				PacMan.left = false;			
			}
			if(key == KeyEvent.VK_UP){
				PacMan.down = false;
				PacMan.up = true;
				PacMan.right = false;
				PacMan.left = false;			
			}
			if(key == KeyEvent.VK_RIGHT){
				PacMan.down = false;
				PacMan.up = false;
				PacMan.right = true;
				PacMan.left = false;			
			}
			if(key == KeyEvent.VK_LEFT){
				PacMan.down = false;
				PacMan.up = false;
				PacMan.right = false;
				PacMan.left = true;			
			}
			if(key == KeyEvent.VK_SPACE){
				PacMan.eating = true;
			}
		}	
		if(miniGame.stat){
			System.out.println("KEY: " + e.getKeyChar());
			if(e.getKeyChar() == KeyEvent.VK_ENTER) miniGame.enter();
			else if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE) miniGame.in.remove(miniGame.in.size()-1);
			else miniGame.in.add(e.getKeyChar());
		}
	}
	
	public static void keyReleased(KeyEvent e){
		key = e.getKeyCode();
		if(key == KeyEvent.VK_SPACE){
			PacMan.eating = false;
		}
	}
}
