package com.tdfk.tiles;

import com.tdfk.gfx.Screen;
import com.tdfk.gfx.Sprite;

public class GrassTile extends Tile{

	public GrassTile(Sprite sprite) {
		super(sprite);
	}

	@Override
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	
	
	
}
