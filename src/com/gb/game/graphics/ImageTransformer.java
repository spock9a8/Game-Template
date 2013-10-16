package com.gb.game.graphics;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class ImageTransformer {

	public static Image flip(Image i, boolean flipx, boolean flipy) {
		Image img = new Image(new BufferedImage(i.width, i.height, BufferedImage.TYPE_INT_ARGB));
		if (flipx) {
			AffineTransform tx = AffineTransform.getScaleInstance(-1D, 1D);
			tx.translate(-i.width, 0D);
			AffineTransformOp op = new AffineTransformOp(tx, 1);
			img.image = op.filter(i.image, null);
		}

		if (flipy) {
			AffineTransform tx = AffineTransform.getScaleInstance(1D, -1D);
			tx.translate(0D, -i.height);
			AffineTransformOp op = new AffineTransformOp(tx, 1);
			if (flipx) {
				img.image = op.filter(img.image, null);
			} else {
				img.image = op.filter(i.image, null);
			}
		}

		return img;
	}

	public static Image scale(Image i, double scale) {
		Image img = new Image(new BufferedImage(i.width, i.height, BufferedImage.TYPE_INT_ARGB));
		AffineTransform tx = AffineTransform.getScaleInstance(scale, scale);
		tx.setToScale(scale, scale);
		AffineTransformOp op = new AffineTransformOp(tx, 1);
		img.image = op.filter(i.image, null);
		img.width = img.image.getWidth();
		img.height = img.image.getHeight();
		return img;
	}

	public static Image rotate(Image i, double radians) {
		Image img = new Image(new BufferedImage(i.width, i.height, BufferedImage.TYPE_INT_ARGB));
		AffineTransform tx = AffineTransform.getRotateInstance(radians, i.width / 2, i.height / 2);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		img.image = op.filter(i.image, null);
		return img;
	}

	public static double getAngle(int x, int y, int x1, int y1) {
		double dx = x1 - x;
		double dy = y1 - y;
		double inRads = Math.atan2(dy, dx);
		return inRads;
	}

	public static int getDistance(int x, int y, int x1, int y1) {
		int distance = (int) Math.sqrt((x1 - x) * (x1 - x) + (y1 - y) * (y1 - y));
		return distance;
	}

	public static Image tintImage(Image loadImg, int color) {
		Image gImage = loadImg;
		Graphics2D g = gImage.image.createGraphics();
		Image image = new Image(new BufferedImage(loadImg.width, loadImg.height, BufferedImage.TYPE_INT_ARGB));
		for (int x = 0; x < loadImg.width; x++) {
			for (int y = 0; y < loadImg.height; y++) {
				if (loadImg.image.getRGB(x, y) >> 24 != 0x00) {
					image.image.setRGB(x, y, color);
				}
			}
		}
		g.drawImage(image.image, 0, 0, null);
		g.dispose();

		return gImage;
	}
}
