package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class HardBossBullet extends GameObject {
	
	private Random r = new Random();
	public HardBossBullet(float x, float y, ID id, Handler handler) {
		super(x, y, id, handler);
		velX = r.nextInt(13) - 6;
		velY = 5;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT-16 || x <= 0 || x >= Game.WIDTH-16)
			handler.removeObject(this);
	
		handler.addObject(new Trail(x, y, ID.Trail, Color.yellow, 32, 32, 0.015f, handler));
	}
	
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int)x, (int)y, 32, 32);
	}
}
