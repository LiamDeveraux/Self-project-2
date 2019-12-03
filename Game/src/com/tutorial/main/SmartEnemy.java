package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class SmartEnemy extends GameObject {

	private GameObject player;
	private BufferedImage enemy_image;
	
	public SmartEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id, handler);
		
		SpriteSheet ss = new SpriteSheet(Game.spriteSheet);
		enemy_image = ss.grabImage(4, 1, 16, 16);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Player) {
				this.player = handler.object.get(i);
			}
		}
		
		float diffX = x - player.getX() - 16;
		float diffY = y - player.getY() - 16;
		float distance = (float)Math.sqrt(Math.pow((x - player.getX()),2) + Math.pow((y - player.getY()), 2));
		
		velX = (-1/distance) * diffX;
		velY = (-1/distance) * diffY;
		
//		if(y <= 0 || y >= Game.HEIGHT-16)
//			velY *= -1;
//		
//		if(x <= 0 || x >= Game.WIDTH-16)
//			velX *= -1;
		
	}
	
	public void render(Graphics g) {
//		g.setColor(Color.green);
//		g.fillRect((int)x, (int)y, 16, 16);
		g.drawImage(enemy_image, (int)x, (int)y, null); 
//		new Trail(x, y, ID.Trail, Color.green, 16, 16, 0.015f, handler);
	}
}
