package com.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.input.KeyInput;
import com.ui.Window;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 800, HEIGHT = 605;
	public static final String TITLE = "PONG";
	
	private static Handler handler;
	private Thread thread;
	private boolean running = false, paused = false;
	
	public static void main(String[] args) {
		new Window(WIDTH, HEIGHT, TITLE, new Game());
	}

	public synchronized void start() {
		if (running) return;
		running = true;

		handler = new Handler(this);
		handler.createWalls();
		handler.addPaddles();
		handler.addBall();
		this.addKeyListener(new KeyInput(this));

		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop() {
		if (!running) return;
		
		thread.stop();
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;

		while (running) {
			if (!paused) {
				long now = System.nanoTime();
				delta += (now - lastTime) / ns;
				lastTime = now;
				while (delta >= 1) {
					tick();
					delta--;
				}
			}
			render();
		}
	}
	
	private void tick() {
		handler.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		handler.render(g);
		
		g.dispose();
		bs.show();
	}

	public static Handler getHandler() {
		return handler;
	}
	
	
}
