package com.math.game;

import java.awt.event.MouseEvent;

public class MouseHandler {

	public static void mousePressed(MouseEvent m) {
		if(Menu.stat) Menu.click(m.getX(), m.getY());
		if(Instructions.stat) Instructions.click(m.getX(), m.getY());
		System.out.println("CLICK");
		if(Over.stat){
			System.out.println("hi");
		Over.stat = false;
		Menu.stat = true;
	}
		
	}

}
