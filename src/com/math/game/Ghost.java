package com.math.game;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Ghost{
	private boolean up = false;
	private boolean down = false;
	private boolean left = false;
	private boolean right = false;
	public int x = 502;
	public int y = 352;
	private static int speed = 7;
	public BufferedImage image;
	private int[] checkpos = {0,0};
	private String name;
	
	Ghost(int x, int y, String name, int direction){
		this.name = name;
		if(direction == 0){
			up = true;
			down = false;
			left = false;
			right = false;
		}
		if(direction == 1){
			up = false;
			down = false;
			left = false;
			right = true;
		}
		if(direction == 2){
			up = false;
			down = true;
			left = false;
			right = false;
		}
		if(direction == 3){
			up = false;
			down = false;
			left = true;
			right = false;
		}
		this.x = x;
		this.y = y;
		try {
			image = ImageIO.read(Ghost.class.getResourceAsStream("/"+name+" left.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void move(double delta) throws IOException{
		int speed = (int) (Ghost.speed*delta);
		boolean moved = true;
		if(up){
			checkpos[0] = x+25;
			checkpos[1] = y;
			for(int i=0;i<speed;i++){
				if(checkpos[0] > 1080 || checkpos[0] < 0){
					down = true;
					up = false;
					return;
				}
				if(checkpos[1] > 810 || checkpos[1] < 0){
					left = true;
					right = false;
					return;
				}
				if(Normal.bg.getRGB(checkpos[0],checkpos[1]-i) != new Color(0,0,0).getRGB()){
					moved = false;
				}
			}
		}
		if(down){
			checkpos[0] = x+25;
			checkpos[1] = y+50;
			for(int i=0;i<speed;i++){
				if(checkpos[0] > 1080 || checkpos[0] < 0){
					down = false;
					up = true;
					return;
				}
				if(checkpos[1] > 810 || checkpos[1] < 0){
					left = true;
					right = false;
					return;
				}
				if(Normal.bg.getRGB(checkpos[0],checkpos[1]-i) != new Color(0,0,0).getRGB()){
					moved = false;
				}
			}
		}
		if(left){
			checkpos[0] = x;
			checkpos[1] = y+25;
			for(int i=0;i<speed;i++){
				if(checkpos[0] > 1080 || checkpos[0] < 0){
					right = true;
					left = false;
					return;
				}
				if(checkpos[1] > 810 || checkpos[1] < 0){
					right = true;
					left = false;
					return;
				}
				if(Normal.bg.getRGB(checkpos[0],checkpos[1]-i) != new Color(0,0,0).getRGB()){
					moved = false;
				}
			}
		}
		if(right){
			checkpos[0] = x+50;
			checkpos[1] = y+25;
			for(int i=0;i<speed;i++){
				if(checkpos[0] > 1080 || checkpos[0] < 0){
					left = true;
					right = false;
					return;
				}
				if(checkpos[1] > 810 || checkpos[1] < 0){
					left = true;
					right = false;
					return;
				}
				if(Normal.bg.getRGB(checkpos[0],checkpos[1]-i) != new Color(0,0,0).getRGB()){
					moved = false;
				}
			}
		}
//		//checkpos is the centermost pixel in front of PacMan's mouth
//		if(Normal.bg.getRGB(checkpos[0],checkpos[1]) == new Color(0,0,0).getRGB()){
//			//moves PacMan
//			if(up) y -= speed;
//			if(down) y += speed;
//			if(left) x -= speed;
//			if(right) x += speed;
//		}
//		else{
		if(moved){
			if(up) y -= speed;
			if(down) y += speed;
			if(left) x -= speed;
			if(right) x += speed;
		}else{
			Random random = new Random();
			int direction = random.nextInt(4);
			if(direction == 0){
				up = true;
				down = false;
				left = false;
				right = false;
			}
			if(direction == 1){
				up = false;
				down = false;
				left = false;
				right = true;
			}
			if(direction == 2){
				up = false;
				down = true;
				left = false;
				right = false;
			}
			if(direction == 3){
				up = false;
				down = false;
				left = true;
				right = false;
			}
		}
		if(y>=810){
			y=0;
		}
		if(y<0){
			y=810-50;
		}
	}
}
