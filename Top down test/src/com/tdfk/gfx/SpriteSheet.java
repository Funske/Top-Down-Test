package com.tdfk.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	String path;
	public final int SIZE;
	public int pixels[];
	public BufferedImage image;
	
	public static SpriteSheet tiles = new SpriteSheet("/tiles.png", 256);
	public static SpriteSheet player = new SpriteSheet("/player.png", 16);
	
	public SpriteSheet(String path, int size){
		
		this.path = path;
		SIZE = size;
		pixels = new int[SIZE*SIZE];
		loadImage();
	}
	
	private void loadImage(){
		try {
			image = ImageIO.read(getClass().getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			System.out.println("Spritsheet loaded.   Width: " + w + " Height: " + h);
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
