package com.gb.game.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {
	
	public BufferedImage image;
	public int[] pixels;
	
	public int width;
	public int height;
	
	public Image(String path) {
		try {
			image = ImageIO.read(Image.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		width = image.getWidth();
		height = image.getHeight();
		
		pixels = image.getRGB(0, 0, width, height, null, 0, width);
	}

	public Image(BufferedImage i) {
		image = i;

		width = image.getWidth();
		height = image.getHeight();

		pixels = image.getRGB(0, 0, width, height, null, 0, width);
	}
}
