package com.tutorial.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import com.tutorial.main.Game.STATE;

public class KeyInput extends KeyAdapter{
	private Handler handler;
	private int key;
	private Random r = new Random();
	private boolean wP, aP, sP, dP;
	public KeyInput (Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		key = e.getKeyCode();
		if(Game.gameState == STATE.Game) {
			if(key == KeyEvent.VK_P) {
				Game.paused = !Game.paused;
			}
		}
		if(key == KeyEvent.VK_ESCAPE)
			System.exit(0);
		if(key == KeyEvent.VK_SPACE) {
			if(Game.gameState == STATE.Game) {
				Game.gameState = STATE.Shop;
				HUD.invincible = true;
			}
			else if(Game.gameState == STATE.Shop) {
				Game.gameState = STATE.Game;
				HUD.invincible = false;
			}
		}
		if(key == KeyEvent.VK_G) {
			handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16), ID.SmartEnemy, handler));
		}
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				if(key == KeyEvent.VK_W) {
					tempObject.setVelY(-handler.speed);
					wP = true;
				}
				else if (key == KeyEvent.VK_S) {
					tempObject.setVelY(handler.speed);
					sP = true;
				}
				else if(key == KeyEvent.VK_A) {
					tempObject.setVelX(-handler.speed);
					aP = true;
				}
				else if(key == KeyEvent.VK_D) {
					tempObject.setVelX(handler.speed);
					dP = true;
				}
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				if(key == KeyEvent.VK_W) {
					wP = false;
					if(sP)
						tempObject.setVelY(handler.speed);
					else
						tempObject.setVelY(0);
				}
				else if (key == KeyEvent.VK_S) {
					sP = false;
					if(wP)
						tempObject.setVelY(-handler.speed);
					else
						tempObject.setVelY(0);
				}
				else if(key == KeyEvent.VK_A) {
					aP = false;
					if(dP)
						tempObject.setVelX(handler.speed);
					else
						tempObject.setVelX(0);
				}
				else if(key == KeyEvent.VK_D) {
					dP = false;
					if(aP)
						tempObject.setVelX(-handler.speed);
					else
						tempObject.setVelX(0);
				}
			}
		}
	}
}
