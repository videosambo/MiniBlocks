package fi.videosambo.shooter.client;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import fi.videosambo.shooter.client.display.Display;
import fi.videosambo.shooter.client.gfx.ImageLoader;

public class Game implements Runnable {
	
	private Display display;

	private String title;
	private int width, height;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
	}

	private void init() {
		display = new Display(title, width, height);
		
	}
	
	private void tick() {
		
	}
	
	private int i = 0, j = 0;
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy(); 	
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//
		
		//
		bs.show();
		g.dispose();
	}

	public void run() {
		init();
		
		while(running) {
			tick();
			render();
		}
		
		stop();
	}
	
	public synchronized void start() {
		if(running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
