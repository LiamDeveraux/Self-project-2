package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Boss extends GameObject {
	private Random r = new Random();
	private int timer = 80;
	private int timer2 = 180;
	public Boss(float x, float y, ID id, Handler handler) {
		super(x, y, id, handler);
		velX = 0;
		velY = 2;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 90, 90);
	}

	public void tick() {
		x += velX;
		y += velY;
		if(timer <= 0)
			velY = 0;
		else
			timer--;
		
		if(timer2 <= 0) {
			if(velX == 0)
				velX = 3;
		}
		else
			timer2--;

		if(x <= 0 || x >= Game.WIDTH-90)
			velX *= -1;
		
		int spawn = r.nextInt(10);
		if(timer2 == 0 && timer == 0 && spawn == 5)
			handler.addObject(new BossBullet((int)x + 45, (int)y + 45, ID.BasicEnemy, handler));
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 90, 90);
	}
}
