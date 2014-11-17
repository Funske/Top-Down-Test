package com.tdfk.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.tdfk.essentials.VarController;
import com.tdfk.gfx.Screen;
import com.tdfk.gfx.Sprite;

public abstract class Entity {
	
	protected float x, y, velX, velY;
	protected Sprite sprite;
	protected VarController vc;
	protected EntityType type;
	
	public Entity(float x, float y, Sprite sprite, VarController vc, EntityType type){
		
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		this.vc = vc;
		this.type = type;
	}
	
	public abstract void tick(LinkedList<Entity> entity);
	public abstract void renderSprite(Screen screen);
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public VarController getVc() {
		return vc;
	}

	public void setVc(VarController vc) {
		this.vc = vc;
	}

	public EntityType getType() {
		return type;
	}

	public void setType(EntityType type) {
		this.type = type;
	}
}
