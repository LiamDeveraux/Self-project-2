package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject {

	private Color col;
	private Random r = new Random();
	public MenuParticle(float x, float y, ID id, Handler handler) {
		super(x, y, id, handler);
		
		velX = 2;
		if(r.nextBoolean()) {
			velX *= -1;
		}
		velY = 5;
		if(r.nextBoolean()) {
			velY = -3
					;
		}
		col = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT-16)
			velY *= -1;
		
		if(x <= 0 || x >= Game.WIDTH-16)
			velX *= -1;
		
		handler.addObject(new Trail(x, y, ID.Trail, col, 16, 16, 0.03f, handler));
	}
	
	public void render(Graphics g) {
		g.setColor(col);
		g.fillRect((int)x, (int)y, 16, 16);
	}
}
