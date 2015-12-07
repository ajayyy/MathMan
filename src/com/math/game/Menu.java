package com.math.game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Menu {
	public static boolean stat = true;
	private static BufferedImage bg;
	static Clip music;
	public static boolean stop;
	
	public static void setup(){
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
			music.open(AudioSystem.getAudioInputStream(new BufferedInputStream(Normal.class.getResourceAsStream("/math man menu song.wav"))));
			music.start();
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		}
	}
	public static void draw(Graphics g) throws IOException{
		bg = ImageIO.read(Menu.class.getResourceAsStream("/menu.png"));
		g.drawImage(bg, 0, 0, null);
	}
	public static void click(int x, int y){
		Rectangle click = new Rectangle(x, y, 1, 1);
		Rectangle play = new Rectangle(443, 181, 244, 71);
		Rectangle inst = new Rectangle(359, 279, 382, 57);
		if(play.intersects(click)){
			Normal.setup(1080, 810);
			Normal.stat = true;
			Menu.stat = false;
		}
		if(inst.intersects(click)){
			Instructions.stat = true;
			Menu.stat = false;
		}
		
	}
}
