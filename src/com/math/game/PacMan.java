package com.math.game;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class PacMan{
	public static boolean up = false;
	public static boolean down = false;
	public static boolean left = false;
	public static boolean right = false;
	public static int x = 518;
	public static int y = 685;
	public static int speed = 5;
	private static int[] checkpos1 = {0,0};
	private static int[] checkpos2 = {0,0};
	private static int[] checkpos3 = {0,0};
	public static boolean eating = false;
	public static BufferedImage image;
	public static BufferedImage[] eimages = new BufferedImage[4];
	static Random rand = new Random();
	
	static int direction;
	
	public static void setup(){
		x = 518;
		y = 685;
		try {
			//pacman closed
			image = ImageIO.read(PacMan.class.getResourceAsStream("/pac.png"));
			
			//directions of pacman open
			eimages[0] = ImageIO.read(PacMan.class.getResourceAsStream("/pacupe.png"));
			eimages[1] = ImageIO.read(PacMan.class.getResourceAsStream("/pacdowne.png"));
			eimages[2] = ImageIO.read(PacMan.class.getResourceAsStream("/paclefte.png"));
			eimages[3] = ImageIO.read(PacMan.class.getResourceAsStream("/pacrighte.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void move(double delta) throws IOException{
		boolean moved = true;
		int speed = (int) (PacMan.speed*delta);
		//x is devisible by 50px
		//CHANGE TO FOR X AND FOR Y
		//CHANGE TO FOR X AND FOR Y
		//CHANGE TO FOR X AND FOR Y
		//CHANGE TO FOR X AND FOR Y
		//CHANGE TO FOR X AND FOR Y
		//CHANGE TO FOR X AND FOR Y
		//CHANGE TO FOR X AND FOR Y
		//CHANGE TO FOR X AND FOR Y
		//CHANGE TO FOR X AND FOR Y
		//CHANGE TO FOR X AND FOR Y
		//CHANGE TO FOR X AND FOR Y
		//CHANGE TO FOR X AND FOR Y
		//CHANGE TO FOR X AND FOR Y
		//CHANGE TO FOR X AND FOR Y and in ghost too
		if(up){
			checkpos1[0] = x+25;
			checkpos1[1] = y;
//			checkpos2[0] = x+5;
//			checkpos2[1] = y; 
//			checkpos3[0] = x+45;
//			checkpos3[1] = y;
			if(y>810 || y<0){
				up = false;
			}
			for(int i=0;i<speed;i++){
				if(Normal.bg.getRGB(checkpos1[0],checkpos1[1]-i) != new Color(0,0,0).getRGB()/*
				|| Normal.bg.getRGB(checkpos2[0],checkpos2[1]-i) != new Color(0,0,0).getRGB()
				|| Normal.bg.getRGB(checkpos3[0],checkpos3[1]-i) != new Color(0,0,0).getRGB()*/){
					moved = false;
				}
			}
		}
		if(down){
			checkpos1[0] = x+25;
			checkpos1[1] = y+50;
//			checkpos2[0] = x+5;
//			checkpos2[1] = y+50;
//			checkpos3[0] = x+45;
//			checkpos3[1] = y+50;
			if(y>810 || y<0){
				down = false;
			}
			for(int i=0;i<speed;i++){
				if(Normal.bg.getRGB(checkpos1[0],checkpos1[1]+i) != new Color(0,0,0).getRGB()/*
				|| Normal.bg.getRGB(checkpos2[0],checkpos2[1]-i) != new Color(0,0,0).getRGB()
				|| Normal.bg.getRGB(checkpos3[0],checkpos3[1]-i) != new Color(0,0,0).getRGB()*/){
					moved = false;
				}
			}
		}
		if(left){
			if(x<40){
				x=1080-50;
			}
			checkpos1[0] = x;
			checkpos1[1] = y+25;
//			checkpos2[0] = x;
//			checkpos2[1] = y+7;
//			checkpos3[0] = x;
//			checkpos3[1] = y+43;
			if(x>1080 || x<0){
				left = false;
			}
			for(int i=0;i<speed;i++){
				if(Normal.bg.getRGB(checkpos1[0]-1,checkpos1[1]) != new Color(0,0,0).getRGB()/*
				|| Normal.bg.getRGB(checkpos2[0],checkpos2[1]-i) != new Color(0,0,0).getRGB()
				|| Normal.bg.getRGB(checkpos3[0],checkpos3[1]-i) != new Color(0,0,0).getRGB()*/){
					moved = false;
				}
			}
		}
		if(right){
			if(x>=1020){
				x=1;
			}
			checkpos1[0] = x+50;
			checkpos1[1] = y+25;
			if(x>1080 || x<0){
				right = false;
			}
			for(int i=0;i<speed;i++){
				if(Normal.bg.getRGB(checkpos1[0]+i,checkpos1[1]) != new Color(0,0,0).getRGB()
				//|| Normal.bg.getRGB(checkpos2[0],checkpos2[1]-i) != new Color(0,0,0).getRGB()|| Normal.bg.getRGB(checkpos3[0],checkpos3[1]-i) != new Color(0,0,0).getRGB()
						){
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
		}
	}

}
