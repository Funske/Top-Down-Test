package com.tdfk.essentials;

import java.awt.Graphics;
import java.util.LinkedList;

import com.tdfk.gfx.Screen;
import com.tdfk.objects.Entity;

public class Controller {
	
	public LinkedList<Entity> entity = new LinkedList<Entity>();
	
	public void tick(){
		for(int i = 0; i < entity.size(); i++){
			entity.get(i).tick(entity);
		}
	}
	
	public void renderSprite(Screen screen){
		for(int i = 0; i < entity.size(); i++){
			entity.get(i).renderSprite(screen);
		}
	}
	
	public void render(Graphics g){
		for(int i = 0; i < entity.size(); i++){
			entity.get(i).render(g);
		}
	}
	
	public void addEntity(Entity entity){
		
		this.entity.add(entity);
	}
	
	public void removeEntity(Entity entity){
		
		this.entity.remove(entity);
	}
	
}
