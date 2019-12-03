package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class BasicEnemy extends GameObject {
	
	private BufferedImage enemy_image;
	public BasicEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id, handler);
		velX = 4;
		velY = 4;
		
		SpriteSheet ss = new SpriteSheet(Game.spriteSheet);
		enemy_image = ss.grabImage(2, 1, 16, 16);
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
	
//		new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.015f, handler);
	}
	
	public void render(Graphics g) {
//		g.setColor(Color.red);
//		g.fillRect((int)x, (int)y, 16, 16);
		g.drawImage(enemy_image, (int)x, (int)y, null); 
	}
}
