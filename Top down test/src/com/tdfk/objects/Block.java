package com.tdfk.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.tdfk.essentials.Game;
import com.tdfk.essentials.VarController;
import com.tdfk.gfx.Screen;
import com.tdfk.gfx.Sprite;

public class Block extends Entity{
	
	private int newX, newY;
	
	public Block(float x, float y, Sprite sprite, VarController vc,
			EntityType type) {
		super(x, y, sprite, vc, type);
	}
	
	public void tick(LinkedList<Entity> entity) {
		newX = ((int)x-vc.xScroll)*2;
		newY = ((int)y-vc.yScroll)*2;
	}

	
	public void renderSprite(Screen screen) {
		
		
	}
	
	public void render(Graphics g){
		
		g.drawImage(sprite.grabImage(), newX, newY, 32, 32, null);
		
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setColor(Color.red);
		g2d.draw(getBounds());
	}
	
	public Rectangle getBounds() {
		
		return new Rectangle(newX, newY, sprite.SIZE*Game.SCALE, sprite.SIZE*Game.SCALE);
	}

}
