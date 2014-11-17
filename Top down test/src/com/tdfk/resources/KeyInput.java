package com.tdfk.resources;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.tdfk.essentials.Controller;
import com.tdfk.essentials.VarController;
import com.tdfk.objects.EntityType;

public class KeyInput implements KeyListener{

	
	public static boolean move = true;
	
	public boolean up, down, left, right;
	
	Controller controller;
	VarController vc;
	
	private int speed = 3, timer = 0, coolDown = 0, moveTimer = 0;;
	private boolean qHit = false, startCoolDown = false;
	
	private boolean[] keys = new boolean[200];
	
	public KeyInput(Controller controller, VarController vc){
		this.controller = controller;
		this.vc = vc;
	}
	
	public void update(){
		//Updates 60 times a second
		
		if(keys[KeyEvent.VK_Q] && !qHit && !startCoolDown)qHit = true;
		
		if(qHit){
			timer++;
			if(timer >= 5){
				speed = 3;
				timer = 0;
				qHit = false;
				startCoolDown = true;
			}else{
				speed = 18;
				vc.quickMove = true;
			}
		}
		
		if(startCoolDown){
			moveTimer++;
			if(moveTimer >= 10){
				vc.quickMove = false;
				moveTimer = 0;
			}
			if(!keys[KeyEvent.VK_Q]){
				coolDown++;
				if(coolDown >= 25){
					coolDown = 0;
					startCoolDown = false;
				}
			}
		}
		
		for(int i = 0; i < controller.entity.size(); i++){
			if(controller.entity.get(i).getType() == EntityType.Player){
				if(keys[KeyEvent.VK_UP])controller.entity.get(i).setVelY(-speed);
				if(keys[KeyEvent.VK_DOWN])controller.entity.get(i).setVelY(speed);
				if(keys[KeyEvent.VK_LEFT])controller.entity.get(i).setVelX(-speed);
				if(keys[KeyEvent.VK_RIGHT])controller.entity.get(i).setVelX(speed);
			}
		}
		
	}
	
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		keys[key] = true;
		
		if(key == KeyEvent.VK_ESCAPE)
			System.exit(1);
		
		for(int i = 0; i < controller.entity.size(); i++){
			if(controller.entity.get(i).getType() == EntityType.Block){
				if(key == KeyEvent.VK_P){
					System.out.println("Block X: " + (controller.entity.get(i).getX()-vc.xScroll));
					System.out.println("Block Y: " + (controller.entity.get(i).getY()-vc.yScroll));
				}
			}
		}
		
		if(move){
			if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W)up = true;
			if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S)down = true;
			if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D)right = true;
			if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A)left = true;
		}
	}

	
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		keys[key] = false;
		
		if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W)up = false;
		if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S)down = false;
		if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D)right = false;
		if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A)left = false;
		
		if(key == KeyEvent.VK_Q)speed = 3;
		
		for(int i = 0; i < controller.entity.size(); i++){
			if(controller.entity.get(i).getType() == EntityType.Player){
				if(key == KeyEvent.VK_UP)controller.entity.get(i).setVelY(0);
				if(key == KeyEvent.VK_DOWN)controller.entity.get(i).setVelY(0);
				if(key == KeyEvent.VK_LEFT)controller.entity.get(i).setVelX(0);
				if(key == KeyEvent.VK_RIGHT)controller.entity.get(i).setVelX(0);
			}
		}
	}

	
	public void keyTyped(KeyEvent e) {
		
		
	}

}
