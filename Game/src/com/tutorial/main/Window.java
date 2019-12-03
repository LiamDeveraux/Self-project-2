package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas{

	/**
	 * 
	 */
	private static final long serialVersionUID = -240840600533728354L;

	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);
		
		
		game.setPreferredSize(new Dimension(width,height));
		game.setMaximumSize(new Dimension(width,height));
		game.setMinimumSize(new Dimension(width,height));
//		
//		
//		frame.setPreferredSize(new Dimension(width, height));
//		frame.setMaximumSize(new Dimension(width, height));
//		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(game);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		game.start();
		
	}
}
