package com.gb.game.graphics;

import java.awt.image.BufferedImage;

import com.gb.game.Game;

public class Font {
	private static final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz{}|~!#%')+-/\"$&(*,.0123456789;=?:<>[] ";
	private static Image font = new Image("/font/font.gb");
	private static Image d = null;

	public static void colorB(int color, Image i) {
		for (int x = 0; x < i.height; x++) {
			for (int y = 0; y < i.height; y++) {
				if (i.image.getRGB(x, y) != font.image.getRGB(4, 1)) {
					i.image.setRGB(x, y, color);
				}
			}
		}
	}
	public static void colorF(int color, Image i) {
		for (int x = 0; x < i.height; x++) {
			for (int y = 0; y < i.height; y++) {
				if (i.image.getRGB(x, y) == font.image.getRGB(4, 1)) {
					i.image.setRGB(x, y, color);
				}
			}
		}
	}

	public static void render(String msg, int x, int y, double scale, int color) {

		for (int i = 0; i < msg.length(); i++) {
			int index = (chars.indexOf(msg.charAt(i)));
			BufferedImage h = font.image.getSubimage(index * 10, 0, 10, 10);
			h = ImageTransformer.scale(new Image(h), scale).image;
			d = new Image(h);
			colorB(color, d);
			if (index >= 0) {
				Game.instance.screen.render(d, (int) (x + ((10 * scale) * i)), y, 0);
			}
		}
	}
	public static void render(String msg, int x, int y, double scale, int color, int fcolor) {

		for (int i = 0; i < msg.length(); i++) {
			int index = (chars.indexOf(msg.charAt(i)));
			BufferedImage h = font.image.getSubimage(index * 10, 0, 10, 10);
			h = ImageTransformer.scale(new Image(h), scale).image;
			d = new Image(h);
			colorB(color, d);
			colorF(fcolor, d);
			if (index >= 0) {
				Game.instance.screen.render(d, (int) (x + ((10 * scale) * i)), y, 0);
			}
		}
	}

	public static void render(String msg, int x, int y, double scale) {

		for (int i = 0; i < msg.length(); i++) {
			int index = (chars.indexOf(msg.charAt(i)));
			BufferedImage h = font.image.getSubimage(index * 10, 0, 10, 10);
			h = ImageTransformer.scale(new Image(h), scale).image;
			d = new Image(h);
			if (index >= 0) {
				Game.instance.screen.render(d, (int) (x + ((10 * scale) * i)), y, 0);
			}
		}
	}
}