package com.gb.game.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Screen {

	public final int BIT_MIRROR_X = 0x01;
	public final int BIT_MIRROR_Y = 0x02;

	public int w;
	public int h;

	public int xOff;
	public int yOff;

	public Graphics2D graphics;
	public BufferedImage image;

	public Screen(int w, int h) {
		this.w = w;
		this.h = h;
		xOff = 0;
		yOff = 0;

		image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		graphics = (Graphics2D) image.getGraphics();
	}
	
	public void clear(Color c) {
		graphics.setColor(c);
		graphics.fillRect(0, 0, w, h);
	}

	public void render(BufferedImage image, int x, int y) {
		x -= xOff;
		y -= yOff;

		graphics.drawImage(image, x, y, null);
	}
	
	public void render(Image img, float x, float y, int flip) {
		render(img, (int)x, (int)y, flip);
	}

	public void render(Image img, int x, int y, int flip) {
		x -= xOff;
		y -= yOff;

		Image renderImg;
		if (flip != 0) {
			boolean flipx = ((flip & BIT_MIRROR_X) == BIT_MIRROR_X);
			boolean flipy = ((flip & BIT_MIRROR_Y) == BIT_MIRROR_Y);
			renderImg = ImageTransformer.flip(img, flipx, flipy);
		} else {
			renderImg = img;
		}

		graphics.drawImage(renderImg.image, x, y, null);
	}
	
	public void renderRect(int x, int y, int w, int h, Color color, boolean fill) {
		x -= xOff;
		y -= yOff;
		
		graphics.setColor(color);
		
		if (fill) 
			graphics.fillRect(x, y, w, h);
		else
			graphics.drawRect(x, y, w, h);
	}
	
	public void renderCircle(int x, int y, int r, Color color, boolean fill) {
		x -= xOff;
		y -= yOff;
		
		graphics.setColor(color);
		
		if (fill)
			graphics.fillOval(x - r, y - r, r * 2, r * 2);
		else
			graphics.drawOval(x - r, y - r, r * 2, r * 2);
	}
	public void renderElipse(int x, int y, int r1, int r2, Color color, boolean fill) {
		x -= xOff;
		y -= yOff;
		
		graphics.setColor(color);
		
		if(fill)
			graphics.fillOval(x - r1, y - r2, r1 * 2, r2 * 2);
		else
			graphics.drawOval(x - r1, y - r2, r1 * 2, r2 * 2);
			
	}

	public void setOffset(float x, float y) {
		this.xOff = (int)x - (w / 2);
		this.yOff = (int)y - (h / 2);
	}
	
	public void clearOffset() {
		this.xOff = 0;
		this.yOff = 0;
	}
}
