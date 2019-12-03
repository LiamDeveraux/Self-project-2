package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class HardEnemy extends GameObject {
	
	private Random r = new Random();
	public HardEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id, handler);
		velX = 5;
		velY = 5;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 32) {
			if(velY < 0)
				velY = r.nextInt(3) + 5;
			else
				velY = -(r.nextInt(3) + 5);
		}
		
		if(x <= 0 || x >= Game.WIDTH - 32) {
			if(velX < 0)
				velX = r.nextInt(3) + 5;
			else
				velX = -(r.nextInt(3) + 5);
		} 
	
		handler.addObject(new Trail(x, y, ID.Trail, Color.yellow, 32, 32, 0.015f, handler));
	}
	
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int)x, (int)y, 32, 32);
	}
}
