package com.tutorial.main;


import java.util.Random;

public class Spawn {
	private Handler handler;
	private HUD hud;
	private int scoreKeep = 0;
	private Random r = new Random();
	
	public Spawn (Handler handler, HUD hud){
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick() {
		scoreKeep++;
		if(Game.difficult) {
			if(scoreKeep == 200) {
				scoreKeep = 0;
				hud.setLevel(hud.getLevel() + 1);
				
				if(hud.getLevel() <= 5)
					new HardEnemy(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 32), ID.BasicEnemy, handler);
//				if(hud.getLevel() % 3 == 0) {
//					new SmartEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16), ID.SmartEnemy, handler);
//				}
//				if(hud.getLevel() % 4 == 0)
//					new FastEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16), ID.FastEnemy, handler);
//				if(hud.getLevel()==2){
//					handler.removeEnemy();
//					new HardBoss((Game.WIDTH - 90)/2, -90, ID.Boss, handler);
//				}			
			}
		}
		else {
			if(scoreKeep == 200) {
				scoreKeep = 0;
				hud.setLevel(hud.getLevel() + 1);
				
				if(hud.getLevel() <= 5)
					new BasicEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16), ID.BasicEnemy, handler);
				if(hud.getLevel() == 3) {
					new SmartEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16), ID.SmartEnemy, handler);
				}
				if(hud.getLevel() == 4)
					new FastEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16), ID.FastEnemy, handler);
				if(hud.getLevel()==6){
					handler.removeEnemy();
					new Boss((Game.WIDTH - 90)/2, -90, ID.Boss, handler);
				}			
			}
		}
	}
}
