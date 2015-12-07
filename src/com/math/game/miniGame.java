package com.math.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class miniGame {
	public static int startTime = 30;
	private static char op;
	private static String equasion;
	public static boolean stat = false;
	private static int ans;
	public static ArrayList<Character> in = new ArrayList<Character>();
	private static int score;
	private static Random ranOp = new Random();
	static long last;
	static int sec;
	static boolean wrong;
	static long wronglast;
	static BufferedImage logo;
	static Clip music;
	static boolean stop;
	
	public static void setup(char operator){
		try {
			music = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));
			music.addLineListener(new LineListener(){
	            @Override
	            public void update(LineEvent event)
	            {
	                if (event.getType() == LineEvent.Type.STOP){
	                	if(!stop){
		                	music.stop();
		                	music.setFramePosition(0);
	                	}
	                }
	            }
	        });
			music.open(AudioSystem.getAudioInputStream(new BufferedInputStream(Normal.class.getResourceAsStream("/math man mini game song.wav"))));
			music.start();
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		}
		int toOp = ranOp.nextInt(3)+1;
		if(toOp == 1) op = '+';
		if(toOp == 2) op = '-';
		if(toOp == 3) op = 'x';
		newEquasion();
		score = 0;
		sec = startTime;
		try {
			logo = ImageIO.read(Ghost.class.getResourceAsStream("/logo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void newEquasion(){
		in.clear();
		Random random = new Random();
		//num1
		int num1 = random.nextInt(10);
		boolean neg = random.nextBoolean();
		if(neg) num1 = -num1;
		//num2
		int num2 = random.nextInt(10);
		boolean neg2 = random.nextBoolean();
		if(neg2) num2 = -num2;
		//creating string
		equasion = num1+" "+op+" "+num2;
		System.out.println(equasion);
		//creating answer
		if(op == '+') ans = num1 + num2;
		if(op == '-') ans = num1 - num2;
		if(op == 'x') ans = num1 * num2;
		System.out.println(in);
	}
	public static void draw(Graphics g){
		if(System.nanoTime() - last >= 1000000000){
			sec--;
			if(sec <= 0) over("time");
			last = System.nanoTime();
		}
		if(score >= 5) over("score");
		Font defaultFont = new Font("Helvetica", Font.BOLD, 75);
		g.setFont(defaultFont);
		FontMetrics fontMetrics = g.getFontMetrics(defaultFont);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 9999, 9999);
		String out = equasion + " = " + in.toString();
		out = out.replace(", ", "");
		out = out.replace("[", "");
		out = out.replace("]", "");
		String timeS = sec+" secondes qui restent";
		String scoremessage;
		if(score == 1){
			scoremessage = score + " questions corectes sur 5";
		}else{
			scoremessage = score + " questions corectes sur 5";
		}
		int x = 1080/2 - fontMetrics.stringWidth(out)/2;
		int sx = 1080/2 - fontMetrics.stringWidth(scoremessage)/2;
		int tx = 1080/2 - fontMetrics.stringWidth(timeS)/2;
		int y = 500;
		g.setColor(Color.YELLOW);
		g.drawString(out, x, y);
		g.drawString(timeS, tx, 300);
		g.drawString(scoremessage, sx, y+200);
		if(wrong){
			g.setColor(Color.red);
			g.setFont(g.getFont().deriveFont(Font.PLAIN, 200));
			g.drawString("INCORRECT!", 1080/2-g.getFontMetrics().stringWidth("INCORRECT!")/2, 810/2-g.getFontMetrics().getHeight()/2);
			if(System.nanoTime() - wronglast >= 100000000){
				wrong = false;
			}
		}
		g.drawImage(logo, 1080/2-logo.getWidth()/2, 0, null);
	}
	public static void enter(){
		String inNum = in.toString();
		inNum = inNum.replace(", ", "");
		inNum = inNum.replace("[", "");
		inNum = inNum.replace("]", "");
		if(Integer.parseInt(inNum)==ans){
			score++;
			newEquasion();
		}else{
			sec-=5;
			wrong = true;
			newEquasion();
			wronglast = System.nanoTime();
		}
	}
	
	private static void over(String reason){
		if(reason == "time"){
			System.out.println("time's up!");
			Over.stat = true;
			miniGame.stat = false;
		}
		if(reason == "score"){
			System.out.println("YOU WIN!");
			Normal.stat = true;
			stat = false;
			startTime -= 5;
		}
	}
}
