package com.math.game;

import java.awt.Graphics;
import java.io.IOException;


public class Game {
	//Call the draw functions in the game/menu/minigame classes
	public static void render(Graphics g, double delta) throws IOException{
		if(Menu.stat){
			Menu.stop = false;
			if(Menu.music !=null &&!Menu.music.isActive()){
				Menu.music.start();
			}
			Menu.draw(g);
		}else{
			if(Menu.music !=null){
				Menu.stop = true;
				Menu.music.stop();
			}
		}
		if(Normal.stat){
			Normal.stop = false;
			if(Normal.music !=null && !Normal.music.isActive()){
				Normal.music.start();
			}
			Normal.draw(g, delta);
		}else{
			if(Normal.music !=null){
				Normal.stop = true;
				Normal.music.stop();
			}
		}
		if(Instructions.stat) Instructions.draw(g);
		if(miniGame.stat){
			miniGame.stop = false;
			if(miniGame.music !=null && !miniGame.music.isActive()){
				miniGame.music.start();
			}
			miniGame.draw(g);
		}else{
			if(miniGame.music !=null){
				miniGame.stop = true;
				miniGame.music.stop();
			}
		}
		if(Over.stat) Over.draw(g);
	}
	//Dont bother using this
	public static void update(){

	}
	
	
}
