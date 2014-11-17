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

public class Player extends Entity{

	private int newX, newY;
	
	public Player(float x, float y, Sprite sprite, VarController vc,
			EntityType type) {
		super(x, y, sprite, vc, type);
	}

	public void tick(LinkedList<Entity> entity) {
		
		x += velX;
		y += velY;
		
		//Collision
		for(int i = 0; i < entity.size(); i++){
			if(entity.get(i).getType() == EntityType.Block){
				//Found a block
				
			}
		}
	}

	
	public void renderSprite(Screen screen) {
		
		screen.renderEntity((int)x, (int)y, sprite, "Player");
	}
	
	public void render(Graphics g){
		
		newX = ((int)x-vc.xScroll)*Game.SCALE;
		newY = ((int)y-vc.yScroll)*Game.SCALE;
		
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setColor(Color.red);
		g2d.draw(getBoundsBottom());
	}
	
	public Rectangle getBounds() {
		
		return new Rectangle(newX, newY, sprite.SIZE*Game.SCALE, sprite.SIZE*Game.SCALE);
	}
	
	public Rectangle getBoundsBottom(){
		
		return new Rectangle(newX+3, newY+16, 12*Game.SCALE, 8*Game.SCALE);
	}
}
