package com.math.game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Instructions {
	public static boolean stat = false;
	private static int page = 1;
	private static BufferedImage bg;
	public static void draw(Graphics g) throws IOException{
		System.out.println("/inst"+page+".png");
		bg = ImageIO.read(Instructions.class.getResourceAsStream("/inst"+page+".png"));
		g.drawImage(bg, 0, 0, null);
	}
	public static void click(int x, int y){
		Rectangle click = new Rectangle(x, y, 1, 1);
		Rectangle back = new Rectangle(9, 23, 196, 54);
		Rectangle next = new Rectangle(835, 35, 221, 54);
		if(back.intersects(click) && page == 1){
			Menu.stat = true;
			stat = false;
		}
		if(back.intersects(click) && page == 2) page = 1;
		if(next.intersects(click) && page == 1) page = 2;
	}
}
