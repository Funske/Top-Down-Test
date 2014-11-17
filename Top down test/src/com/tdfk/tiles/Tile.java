package com.tdfk.tiles;

import com.tdfk.gfx.Screen;
import com.tdfk.gfx.Sprite;

public class Tile {
	
	public Sprite sprite;
	
	//Put tiles here
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	public static Tile grassTile = new GrassTile(Sprite.grass);
	public static Tile waterTile = new GrassTile(Sprite.water);
	public static Tile brickTile = new GrassTile(Sprite.brick);
	
	public Tile(Sprite sprite){
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen){
		
	}
	
	public boolean solid(){
		return false;
	}
	
}
