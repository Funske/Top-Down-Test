package com.tdfk.essentials;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.tdfk.gfx.Screen;
import com.tdfk.gfx.Sprite;
import com.tdfk.levels.Level;
import com.tdfk.levels.LevelLoader;
import com.tdfk.objects.Block;
import com.tdfk.objects.EntityType;
import com.tdfk.objects.Player;
import com.tdfk.resources.KeyInput;
import com.tdfk.states.STATE;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	
	//For starting the game
	Thread thread;
	private boolean running = false;
	
	//For the jframe
	public static final int WIDTH = 320, HEIGHT = 240, SCALE = 2;
	
	//Image that you draw to
	private BufferedImage glowImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	private int[] data = new int[WIDTH*HEIGHT];
	
	Screen screen;
	VarController vc;
	Level level;
	KeyInput input;
	Controller controller;
	
	public void init(){
		
		screen = new Screen(WIDTH, HEIGHT);
		vc = new VarController();
		level = new LevelLoader("/level.png", vc, controller);
		controller = new Controller();
		input = new KeyInput(controller, vc);
		
		controller.addEntity(new Player(64, 64, Sprite.playerSprite, vc, EntityType.Player));
		controller.addEntity(new Block(128, 32, Sprite.stone, vc, EntityType.Block));
		
		addKeyListener(input);
	}
	
	public void tick(){
		
		if(vc.state == STATE.Game){
			controller.tick();
			input.update();
			
			
		}
	}
	
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		//Draw graphics after this point
		
		screen.clearScreen();
		
		for(int i = 0; i < controller.entity.size(); i++){
			if(controller.entity.get(i).getType() == EntityType.Player){
				if(!(vc.room == 1)){
					vc.xScroll = (int)controller.entity.get(i).getX() - WIDTH/2;
					vc.yScroll = (int)controller.entity.get(i).getY() - HEIGHT/2;
				}
				//Draws the level
				level.render(vc.xScroll, vc.yScroll, screen);
			}
		}
		
		controller.renderSprite(screen);
		
		//Don't draw to the screen after this point
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = screen.pixels[i];
		}
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		for(int i = 0; i < data.length; i++){
			data[i] = (50 << 24) | (255 << 16) | (255 << 8) | 255;
		}
		glowImage.setRGB(0, 0, WIDTH, HEIGHT, data, 0, WIDTH);
		if(vc.quickMove)g.drawImage(glowImage, 0, 0, getWidth(), getHeight(), this);
		
		controller.render(g);
		
		//Do not draw graphics after this point
		g.dispose();
		bs.show();
	}
	
	public void run(){
		//Game Loop
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
			while(running){
				long now = System.nanoTime();
				delta += (now - lastTime) / ns;
				lastTime = now;
				while(delta >= 1){
					tick();
					updates++;
					delta--;
				}
				render();
				frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;

		System.out.println("FPS: " + frames + " UPDATES: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}
	
	public static void main(String args[]){
		
		Game game = new Game();
		
		game.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		game.setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		game.setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		
		JFrame frame = new JFrame("Test");
		
		frame.add(game);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		game.start();
	}
	
	public synchronized void start(){
		if(running)
			return;
		
		running = true;
		thread = new Thread(this, "Game");
		thread.start();
	}
	
	public synchronized void stop(){
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
