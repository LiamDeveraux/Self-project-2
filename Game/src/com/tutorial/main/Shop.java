package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.tutorial.main.Game.STATE;

public class Shop extends MouseAdapter{

	private Handler handler;
	private HUD hud;
	private int[] cost;
	public Shop(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
		cost = new int [3];
		cost[0] = 1000;
		cost[1] = 1500;
		cost[2] = 2000;
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height)
				return true;
		}
		return false;
	}
	
	public void render (Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("arial", 0, 48));
		g.drawString("Shop", 270, 80);
		
		g.setFont(new Font("arial", 0, 12));
		g.drawRect(200, 110, 260, 80);
		g.drawString("Upgrade Health", 290, 140);
		g.drawString("Cost : " + cost[0], 300, 170);
		
		g.drawRect(200, 210, 260, 80);
		g.drawString("Upgrade Speed", 288, 240);
		g.drawString("Cost : " + cost[1], 300, 270);

		g.drawRect(200, 310, 260, 80);
		g.drawString("Refill Health", 300, 340);
		g.drawString("Cost : " + cost[0], 300, 370);
		
		g.drawString("Score : " + hud.getScore(), 300, 420);
		g.drawString("Press Space to go back", 265, 440);
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(Game.gameState != STATE.Shop)
			return;
		if(mouseOver(mx, my, 200, 110, 260, 80)) {
			if(hud.getScore() >= cost[0] && cost[0] <= 5000) {
				Game.maxHealth += 20;
				HUD.HEALTH += 20;
				hud.setScore(hud.getScore() - cost[0]);
				cost[0] += 1000;
			}
		}
		else if(mouseOver(mx, my, 200, 210, 260, 80)) {
			if(hud.getScore() >= cost[1] && cost[1] <= 6500) {
				handler.speed++;
				hud.setScore(hud.getScore() - cost[1]);	
			}
		}
		else if(mouseOver(mx, my, 200, 310, 260, 80)) {
			if(hud.getScore() >= cost[2]) {
				HUD.HEALTH = Game.maxHealth;
				hud.setScore(hud.getScore() - cost[2]);				
			}
		}
	}
}
