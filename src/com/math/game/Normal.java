package com.math.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
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

public class Normal{
	public static boolean stat = false;
	public static BufferedImage bg;
	private static Ghost inky;
	private static Ghost blinky;
	private static Ghost pinky;
	private static Ghost clyde;
	public static ArrayList<Pellet> pellets = new ArrayList<Pellet>();
	static int operation = 0;
	static Random rand = new Random();
	static int score = 0;
	static long last;
	static Clip music;
	static boolean stop;
	public static void setup(int width, int height){
		miniGame.startTime = 30;
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
			music.open(AudioSystem.getAudioInputStream(new BufferedInputStream(Normal.class.getResourceAsStream("/game song.wav"))));
			music.start();
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		}
		score = 0;
		inky = new Ghost(515,224,"inky", 1);
		blinky = new Ghost(515,224,"blinky", 0);
		pinky = new Ghost(515,224,"pinky", 2);
		clyde = new Ghost(515,224,"clyde", 3);
		try {
			bg = ImageIO.read(Normal.class.getResourceAsStream("/bg.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		int x = 0;
		int y = 0;
		PacMan.setup();
		boolean placed = false;
		//have we placed a pellet yet?
		System.out.println("started");
		while(true){
			System.out.println(x + " " + y);
			while(bg.getRGB(x, y) != Color.black.getRGB() || bg.getRGB(x+40, y) != Color.black.getRGB() ||
					bg.getRGB(x, y+40) != Color.black.getRGB() || bg.getRGB(x+40, y+40) != Color.black.getRGB()){
				System.out.println(x + " " + y);
				x++;
				if(x>=width){
					if(placed){
						y+=55;
					}else{
						y++;
					}
					x=0;
					placed = false;
				}
				if(y>=height){
					last = System.nanoTime();
					return;
				}
				if(y>0 && bg.getRGB(x, y-1) == Color.black.getRGB()){
					int oldy = y;
					while(bg.getRGB(x, y) != Color.black.getRGB() || bg.getRGB(x+40, y) != Color.black.getRGB() ||
							bg.getRGB(x, y+40) != Color.black.getRGB() || bg.getRGB(x+40, y+40) != Color.black.getRGB()){
						y--;
						if(y>=height){
							return;
						}
						if(y<=0){
							break;
						}
						if(oldy-y >= 30){
							y = oldy;
							break;
						}
					}
					if(oldy-y >= 30){
						y = oldy;
						continue;
					}
					if(y<=0){
						y = oldy;
						continue;
					}
					while(bg.getRGB(x, y) == Color.black.getRGB() && bg.getRGB(x+40, y) == Color.black.getRGB() &&
							bg.getRGB(x, y+40) == Color.black.getRGB() && bg.getRGB(x+40, y+40) == Color.black.getRGB()){
						pellets.add(new Pellet(x,y));
						x+=60;
						if(x>=width){
							y+=55;
							x=0;
							if(y>=height){
								last = System.nanoTime();
								return;
							}
							placed = false;
							break;
						}
						if(y>=height){
							last = System.nanoTime();
							return;
						}
						if(oldy <= y){
							continue;
						}
					}
					placed = true;
				}
			}
			//YES
			while(bg.getRGB(x, y) == Color.black.getRGB() && bg.getRGB(x+40, y) == Color.black.getRGB() &&
					bg.getRGB(x, y+40) == Color.black.getRGB() && bg.getRGB(x+40, y+40) == Color.black.getRGB()){
				pellets.add(new Pellet(x,y));
				x+=60;
				if(x>=width){
					y+=55;
					x=0;
					if(y>=height){
						last = System.nanoTime();
						return;
					}
					placed = false;
					break;
				}
				if(y>=height){
					last = System.nanoTime();
					return;
				}
			}
			
			if(x>=width){
				continue;
			}
			
			placed = true;
		}
		
	}
	public static void draw(Graphics g, double delta) throws IOException{
		g.drawImage(bg, 0, 0, null);
		if(System.nanoTime() - last >= 10000000000d){
			operation = rand.nextInt(4);
			last = System.nanoTime();
		}
		for(int i=0; i<pellets.size(); i++){
			Rectangle pelletRect = new Rectangle(pellets.get(i).x, pellets.get(i).y, 40, 40);
			Rectangle pacRect = new Rectangle(PacMan.x, PacMan.y, 50, 50);
			if(pelletRect.intersects(pacRect) && PacMan.eating){
				switch(operation){
				case 0:
					score += pellets.get(i).num;
					break;
				case 1:
					score -= pellets.get(i).num;
					break;
				case 2:
					score *= pellets.get(i).num;
					break;
				case 3:
					if(pellets.get(i).num == 0) score = 0;
					else score /= pellets.get(i).num;
				}
				pellets.remove(i);
				continue;
			}
			g.setColor(pellets.get(i).bg);
			System.out.println(pellets.get(i).bg);
			g.fillRect(pellets.get(i).x, pellets.get(i).y, 40, 40);
			g.setColor(Color.WHITE);
			g.setFont(g.getFont().deriveFont(Font.PLAIN, 30));
			g.drawString(pellets.get(i).num+"", pellets.get(i).x, pellets.get(i).y+g.getFontMetrics().getHeight());
			System.out.println(pellets.size());
		}
		g.setColor(Color.white);
		PacMan.move(delta);
		if(!PacMan.eating){
			g.drawImage(PacMan.image, PacMan.x, PacMan.y, null);
		}else{
			if(PacMan.up){
				g.drawImage(PacMan.eimages[0], PacMan.x, PacMan.y, null);
			}
			if(PacMan.down){
				g.drawImage(PacMan.eimages[1], PacMan.x, PacMan.y, null);
			}
			if(PacMan.left){
				g.drawImage(PacMan.eimages[2], PacMan.x, PacMan.y, null);
			}
			if(PacMan.right){
				g.drawImage(PacMan.eimages[3], PacMan.x, PacMan.y, null);
			}
		}
		inky.move(delta);
		g.drawImage(inky.image, inky.x, inky.y, null);
		blinky.move(delta);
		g.drawImage(blinky.image, blinky.x, blinky.y, null);
		pinky.move(delta);
		g.drawImage(pinky.image, pinky.x, pinky.y, null);
		clyde.move(delta);
		g.drawImage(clyde.image, clyde.x, clyde.y, null);
		collisionCheck();
		g.setFont(g.getFont().deriveFont(Font.PLAIN, 50));
		g.setColor(Color.YELLOW);
		g.drawString("Score: " + score, bg.getWidth()/2-g.getFontMetrics().stringWidth("Score: " + score)/2, g.getFontMetrics().getHeight()/4*3);
		switch(operation){
		case 0:
			g.drawString("+", 0, g.getFontMetrics().getHeight()/4*2);
			break;
		case 1:
			g.drawString("-", 0, g.getFontMetrics().getHeight()/4*2);
			break;
		case 2:
			g.drawString("*", 0, g.getFontMetrics().getHeight()/4*3);
			break;
		case 3:
			g.drawString("÷", 0, g.getFontMetrics().getHeight()/4*2);
		}
	}
	
	public static void collisionCheck(){
		Rectangle pacRect = new Rectangle(PacMan.x, PacMan.y, 50, 50);
		Rectangle inkyRect = new Rectangle(inky.x, inky.y, 50, 50);
		Rectangle blinkyRect = new Rectangle(blinky.x, blinky.y, 50, 50);
		Rectangle pinkyRect = new Rectangle(pinky.x, pinky.y, 50, 50);
		Rectangle clydeRect = new Rectangle(clyde.x, clyde.y, 50, 50);
		int startx = 515;
		int starty = 224;
		if(clydeRect.intersects(pacRect)){
			clyde.x = startx;
			clyde.y = starty;
			miniGame.setup('+');
			miniGame.stat = true;
			Normal.stat = false;
		}		
		if(pinkyRect.intersects(pacRect)){
			pinky.x = startx;
			pinky.y = starty;
			miniGame.setup('-');
			miniGame.stat = true;
			Normal.stat = false;
		}		
		if(inkyRect.intersects(pacRect)){
			inky.x = startx;
			inky.y = starty;
			miniGame.setup('x');
			miniGame.stat = true;
			Normal.stat = false;
		}		
		if(blinkyRect.intersects(pacRect)){
			blinky.x = startx;
			blinky.y = starty;
			miniGame.setup('/');
			miniGame.stat = true;
			
			Normal.stat = false;

		}
	}
}
