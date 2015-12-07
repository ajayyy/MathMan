package com.math.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Over {
	public static boolean stat = false;
	public static void draw(Graphics g){
		System.out.println("OVER");
		Font defaultFont = new Font("Helvetica", Font.BOLD, 75);
		g.setFont(defaultFont);
		FontMetrics fontMetrics = g.getFontMetrics(defaultFont);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 9999, 9999);
		g.setColor(Color.YELLOW);
		int goX = 540 - fontMetrics.stringWidth("JEU TERMINÉ")/2;
		g.drawString("JEU TERMINÉ", goX, 100);
		String scrS = "Ton pointage était: " + Normal.score;
		int scrX = 540 - fontMetrics.stringWidth(scrS)/2;
		g.drawString(scrS, scrX, 500);
		defaultFont = new Font("Helvetica", Font.BOLD, 50);
		g.setFont(defaultFont);
		fontMetrics = g.getFontMetrics(defaultFont);
		String str = "Cliquer pour retourner au menu";
		int strX = 540 - fontMetrics.stringWidth(str)/2;
		g.drawString(str, strX, 650);
	}
}
