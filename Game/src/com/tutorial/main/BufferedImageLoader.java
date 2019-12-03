package com.tutorial.main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {
	BufferedImage bufferedImage;
	
	public BufferedImage loadImage(String path) {
		try {
			bufferedImage = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bufferedImage;
	}
}
