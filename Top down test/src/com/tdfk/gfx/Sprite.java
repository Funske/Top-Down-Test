package com.tdfk.gfx;

import java.awt.image.BufferedImage;

public class Sprite {
	
	//Sets the size in pixels for the sprite
	public final int SIZE;
	//Where sprite is on the sprite sheet
	private int x, y;
	//Pixels in the image
	public int pixels[];
	//Sprite sheet image can be found on
	private SpriteSheet ss;
	
	//For graphics
	private int col = 1, row = 1, width = 16, height = 16;
	
	//Load all sprites after this line
	public static Sprite voidSprite = new Sprite(16, 0);
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite water = new Sprite(16, 12, 0, SpriteSheet.tiles);
	public static Sprite brick = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite playerSprite = new Sprite(16, 0, 0, SpriteSheet.player);
	
	//Graphics sprites
	public static Sprite stone = new Sprite(2, 1, 16, 16, 16, SpriteSheet.tiles);
	
	public Sprite(int size,int x,int y, SpriteSheet ss){
		
		SIZE = size;
		this.x = x * size; 
		this.y = y * size;
		pixels = new int[SIZE*SIZE];
		this.ss = ss;
		load();
	}
	
	public Sprite(int col, int row, int width, int height, int size, SpriteSheet ss){
		SIZE = size;
		this.col = col;
		this.row = row;
		this.width = width;
		this.height = height; 
		this.ss = ss;
	}
	
	public Sprite(int size, int color){
		SIZE = size;
		pixels = new int[SIZE*SIZE];
		setColor(color);
	}
	
	private void load(){
		for(int y = 0; y < SIZE; y++){
			for(int x = 0; x < SIZE; x++){
				pixels[x+y*SIZE] = ss.pixels[(x + this.x) + (y + this.y) * ss.SIZE];
			}
		}
	}
	
	private void setColor(int color){
		for(int i = 0; i < SIZE*SIZE; i++){
			pixels[i] = color;
		}
	}
	
	public BufferedImage grabImage(){
		
		BufferedImage img = ss.image.getSubimage((col*SIZE)-SIZE, (row*SIZE)-SIZE, width, height);
		return img;
	}
}
