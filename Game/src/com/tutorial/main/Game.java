package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Game extends Canvas implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 147634613591168312L;
	
	private Thread thread;
	private boolean running = false;
	
	public static BufferedImage spriteSheet;

	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawn;
	private Menu menu;
	private Shop shop;
	
	public static int maxHealth = 100;
	public static final int WIDTH = 640, HEIGHT = WIDTH/12 * 9;
	
	public static boolean paused = false;
	public static boolean difficult = false;
	
	public enum STATE{
		Menu,
		Help,
		GameOver,
		Select,
		Shop,
		Game;
	}
	
	public static STATE gameState = STATE.Menu;
	
	public Game() {
		AudioPlayer.load();
		BufferedImageLoader loader = new BufferedImageLoader();
		spriteSheet = loader.loadImage("/Player.png");
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(handler, hud);
		spawn = new Spawn(handler, hud);
		shop = new Shop(handler, hud);
		this.addMouseListener(menu);
		this.addMouseListener(shop);
		this.addKeyListener(new KeyInput(handler));
		r = new Random();
		new Window(WIDTH, HEIGHT, "Let's Build a Game!", this);
//		for(int i = 0; i < 50; i++) {
//			handler.addObject(new Player(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.Player));			
//		}
		
		AudioPlayer.getMusic("music").loop();
//		
//		if(gameState == STATE.Game) {
//			new Player(200, 200, ID.Player, handler);
//			new Boss((Game.WIDTH - 90)/2, -90, ID.Boss, handler);			
//		}
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS : " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		if(!paused) {
			handler.tick();
			if(gameState == STATE.Game) {
				hud.tick();
				spawn.tick();		
			}
			else if(gameState == STATE.Menu || gameState == STATE.GameOver || gameState == STATE.Select) {
				menu.tick();
			}			
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if(gameState == STATE.Shop)
			shop.render(g);
		else
			handler.render(g);
		
		if(paused) {
			g.setColor(Color.red);
			g.drawString("PAUSED", 100, 100);
		}
		
		if(gameState == STATE.Game)
			hud.render(g);
		else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.GameOver || gameState == STATE.Select){
			menu.render(g);
		}
		g.dispose();
		bs.show();
	}
	
	public static int clamp(int position, int min, int max) {
		if(position <= min) {
			return min;
		}
		else if (position >= max) {
			return max;
		}
		else 
			return position;
	}
	
	public static void main(String args[]) {
		new Game();
	}
}
