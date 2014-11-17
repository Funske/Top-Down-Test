package com.tdfk.levels;

import com.tdfk.essentials.Controller;
import com.tdfk.essentials.VarController;
import com.tdfk.gfx.Screen;
import com.tdfk.tiles.Tile;

public class Level {
	
	protected int width, height;
	protected int[] tiles;
	protected VarController vc;
	protected Controller controller;
	
	public Level(int width, int height){
		
		this.width = width;
		this.height = height;
		tiles = new int[width*height];
		generateLevel();
	}
	
	public Level(String path, VarController vc, Controller controller){
		this.vc = vc;
		this.controller = controller;
		loadLevel(path);
	}
	
	protected void generateLevel(){
		
	}
	
	protected void loadLevel(String path){
		
	}
	
	public void render(int xScroll, int yScroll, Screen screen){
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;
		
		for(int y = y0; y < y1; y++){
			for(int x = x0; x < x1; x++){
				
				getTile(x, y).render(x, y, screen);
			}
		}
	}
	
	public Tile getTile(int x, int y){
		
		if(x < 0 || x >= width || y < 0 || y >= height)return Tile.voidTile;
		
		if(tiles[x+y*width] == 0)return Tile.voidTile;
		if(tiles[x+y*width] == 1)return Tile.grassTile;
		if(tiles[x+y*width] == 2)return Tile.waterTile;
		if(tiles[x+y*width] == 3)return Tile.brickTile;
		
		return Tile.voidTile;
	}
	
}
