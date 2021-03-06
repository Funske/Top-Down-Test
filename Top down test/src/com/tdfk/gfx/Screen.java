package com.tdfk.gfx;

import java.util.Random;

import com.tdfk.tiles.Tile;

public class Screen {
	
	public int width, height;
	
	public final int MAP_SIZE = 64;
	public final int MAP_MASK = MAP_SIZE -1;
	
	public int xOffset, yOffset;
	public int ya, xa;
	
	public int[] pixels;
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
	
	Random rand = new Random();
	
	public Screen(int width, int height){
		
		this.width = width;
		this.height = height;
		pixels = new int[width*height];
	}
	
	public void clearScreen(){
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = 0;
		}
	}
	
	public void renderTile(int xp, int yp, Tile tile){
		xp -= xOffset;
		yp -= yOffset;
		for(int y = 0; y < tile.sprite.SIZE; y++){
			ya = y+yp;
			for(int x = 0; x < tile.sprite.SIZE; x++){
				xa = x+xp;
				if(xa < - tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height)break;
				if(xa < 0) xa = 0;
				pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
			}
		}
	}
	
	public void renderEntity(int xp, int yp, Sprite sprite, String message){
		xp -= xOffset;
		yp -= yOffset;
		for(int y = 0; y < sprite.SIZE; y++){
			ya = y+yp;
			for(int x = 0; x < sprite.SIZE; x++){
				xa = x+xp;
				if(xa < - sprite.SIZE || xa >= width || ya < 0 || ya >= height)break;
				if(xa < 0) xa = 0;
				pixels[xa+ya*width] = sprite.pixels[x + y * sprite.SIZE];
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset){
		
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
}
