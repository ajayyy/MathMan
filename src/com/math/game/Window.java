package com.math.game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import javax.swing.JFrame;

public class Window extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	private String title;
	private int height;
	private int width;
	BufferStrategy s;
	private JFrame window;
	double delta;
	int targetfps = 25;
	long timeperupdate = 1000000000/targetfps,last;
	
	public Window(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
		init();
		window.setVisible(true);
	}

	private void init() {
		window = new JFrame(title); 
		window.add(this);
		window.pack();
		window.setSize(new Dimension(width, height));
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		
		KeyAdapter a = new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e){
				KeyHandler.keyPressed(e);
				
			}
			
			@Override
			public void keyReleased(KeyEvent e){
				KeyHandler.keyReleased(e);
			}
		};
		this.addMouseListener(new MouseAdapter(){
	         public void mouseClicked(MouseEvent e) {
	             MouseHandler.mousePressed(e);
	          }                
	       });
		this.addKeyListener(a);
		window.addKeyListener(a);
		
		Thread thread = new Thread(this);
		thread.start();
		
	}
	
	public void run(){
		last = System.nanoTime();
		while (true){
			s = getBufferStrategy();
			if(s!=null){
				Graphics2D g = (Graphics2D) s.getDrawGraphics();
				
				long now = System.nanoTime();
				long updateLength = now - last;
			    last = now;
				delta = updateLength / ((double)timeperupdate);
				//above is delta code stuff
				
				try {
					Game.render(g, delta);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					Thread.sleep( (System.nanoTime()-last + timeperupdate)/1000000 );
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				g.dispose();
				s.show();
			}else{
				createBufferStrategy(3);
			}
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
