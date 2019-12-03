package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.tutorial.main.Game.STATE;

public class Player extends GameObject {

//	private Random r = new Random();
	private BufferedImage player_image;
	public Player(float x, float y, ID id, Handler handler) {
		super(x, y, id, handler);
//		velX = r.nextInt(5) + 1;
//		velY = r.nextInt(5);
		SpriteSheet ss = new SpriteSheet(Game.spriteSheet);
		player_image = ss.grabImage(1, 1, 32, 32);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp((int)x, 0, Game.WIDTH - 32);
		y = Game.clamp((int)y, 0, Game.HEIGHT - 32);
		
		collision();
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);	
	}
	
	public void render(Graphics g) {		
//		g.setColor(Color.white);
//		g.fillRect((int)x, (int)y, 32, 32);
		g.drawImage(player_image, (int)x, (int)y, null); 
//		new Trail(x, y, ID.Player, Color.white, 32, 32, 0.015f, handler);
	}
	
	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.SmartEnemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					if(!HUD.invincible)
						HUD.HEALTH -= 2;
				}
			}
			else if(tempObject.getId() == ID.FastEnemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					if(!HUD.invincible)
						HUD.HEALTH += 2;
				}
			}
			else if(tempObject.getId() == ID.Boss) {
				if(getBounds().intersects(tempObject.getBounds())) {
					if(!HUD.invincible)
						HUD.HEALTH = 0;
				}
			}
		}
		
		if(HUD.HEALTH <= 0) {
			handler.object.clear();
			Game.gameState = STATE.GameOver;
		}
	}
}
