package com.math.game;

import java.awt.Color;
import java.util.Random;

public class Pellet{
	public int num;
	public Color bg;
	public int x;
	public int y;
	boolean dead;
	Pellet(int x,int y){
		this.x = x;
		this.y = y;
		//pellet placement code
		num();
	}
	private void num(){
		Random random = new Random();
		bg = Color.RED;
		boolean negative = random.nextBoolean();
		num = random.nextInt(11);
		if(negative){
			num = -num;
			bg = Color.BLUE;
		}
	}
}
