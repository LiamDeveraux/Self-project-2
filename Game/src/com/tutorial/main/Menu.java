package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.tutorial.main.Game.STATE;

public class Menu extends MouseAdapter{
	
	private Random r = new Random();
	private Handler handler;
	private HUD hud;
	public Menu(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
		for(int i = 0; i < 10; i++)
			new MenuParticle(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16), ID.BasicEnemy, handler);
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height)
				return true;
		}
		return false;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(Game.gameState == STATE.Help) {
			//Back Button
			if(mouseOver(mx, my, 215, 150, 200, 64)) {
				Game.gameState = STATE.Menu;
			}
		}
		else if(Game.gameState == STATE.Menu){
			//play button
			if(mouseOver(mx, my, 215, 150, 200, 64)) {
//				Game.gameState = STATE.Game;
//				handler.removeEnemy();
//				new Player(200, 200, ID.Player, handler);
//				new BasicEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16), ID.BasicEnemy, handler);
				
				Game.gameState = STATE.Select;
				AudioPlayer.getSound("menu_sound").play();
			}
			//Help button
			else if(mouseOver(mx, my, 215, 250, 200, 64)) {
				AudioPlayer.getSound("menu_sound").play();
				Game.gameState = STATE.Help;
			}
			//Quit Button
			else if(mouseOver(mx, my, 215, 350, 200, 64)) {
				AudioPlayer.getSound("menu_sound").play();
				System.exit(1);
			}
		}
		else if(Game.gameState == STATE.GameOver) {
			if(mouseOver(mx, my, 217, 250, 200, 64)) {
				Game.gameState = STATE.Menu;
				for(int i = 0; i < 10; i++)
					new MenuParticle(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16), ID.BasicEnemy, handler);
				HUD.HEALTH = 100;
				hud.setLevel(1);
				hud.setScore(0);
				
			}
		}
		else if(Game.gameState == STATE.Select){
			//Normal button
			if(mouseOver(mx, my, 215, 150, 200, 64)) {
				Game.gameState = STATE.Game;
				handler.removeEnemy();
				new Player(200, 200, ID.Player, handler);
				new BasicEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16), ID.BasicEnemy, handler);
				AudioPlayer.getSound("menu_sound").play();
			}
			//Hard button
			else if(mouseOver(mx, my, 215, 250, 200, 64)) {
				Game.difficult = true;
				handler.removeEnemy();
				Game.gameState = STATE.Game;
				new Player(200, 200, ID.Player, handler);
				new HardEnemy(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 32), ID.BasicEnemy, handler);
				AudioPlayer.getSound("menu_sound").play();
			}
			//Back button
			else if(mouseOver(mx, my, 215, 350, 200, 64)) {
				Game.gameState = STATE.Menu;
				AudioPlayer.getSound("menu_sound").play();
			}
		}
		
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	public void render(Graphics g) {
		Font font = new Font("arial", 1, 50);
		Font font2 = new Font("arial", 1, 30);
		
		if(Game.gameState == STATE.Menu) {
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("Menu", 245, 100);
			
			
			g.setFont(font2);
			g.setColor(Color.white);
			g.drawString("Play", 285, 193);
			g.drawRect(215, 150, 200, 64);
			
			g.setFont(font2);
			g.setColor(Color.white);
			g.drawString("Help", 285, 293);
			g.drawRect(215, 250, 200, 64);
			
			g.setFont(font2);
			g.setColor(Color.white);
			g.drawString("Quit", 285, 393);
			g.drawRect(215, 350, 200, 64);			
		}
		else if(Game.gameState == STATE.Help) {
			g.setFont(font2);
			g.setColor(Color.white);
			g.drawString("Press WASD to move", 160, 100);
			
			// Back button
			g.drawString("Back", 280, 192);
			g.drawRect(215, 150, 200, 64);
			
		}
		else if (Game.gameState == STATE.GameOver) {
			g.setFont(font2);
			g.setColor(Color.white);
			g.drawString("Game Over", 240, 100);
			
			g.drawString("You lost with a score of " + hud.getScore(), 135, 192);
			
			g.drawString("Back", 282, 292);
			g.drawRect(217, 250, 200, 64);
		}
		else if(Game.gameState == STATE.Select) {
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("Select Difficulty", 132, 100);
			
			
			g.setFont(font2);
			g.setColor(Color.white);
			g.drawString("Normal", 265, 193);
			g.drawRect(215, 150, 200, 64);
			
			g.setFont(font2);
			g.setColor(Color.white);
			g.drawString("Hard", 280, 293);
			g.drawRect(215, 250, 200, 64);
			
			g.setFont(font2);
			g.setColor(Color.white);
			g.drawString("Back", 280, 393);
			g.drawRect(215, 350, 200, 64);			
		}
	}
	
	public void tick() {
		
	}
}
