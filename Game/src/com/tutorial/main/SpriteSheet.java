package com.tutorial.main;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	private BufferedImage bufferedImage;
	
	public SpriteSheet(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}
	
	public BufferedImage grabImage(int col, int row, int width, int height) {
		return bufferedImage.getSubimage(col * 32 - 32, row * 32 - 32, width, height);
	}
}
