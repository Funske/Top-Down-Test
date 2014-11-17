package com.tdfk.levels;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.tdfk.essentials.Controller;
import com.tdfk.essentials.VarController;

public class LevelLoader extends Level{

	private int pixel, red, green, blue;
	
	public LevelLoader(String path, VarController vc, Controller controller) {
		super(path, vc, controller);
	}
	
	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(getClass().getResource(path));
			/**TODO REMEBER THAT YOU HAVE TO USE ALREADY SET LEVEL VARIABLES FOR WIDTH AND HEIGHT
			 * DON'T EVEN THINK ABOUT CREATING YOUR OWN!!!!!!!!!!!!!!!!!!!!!!*/
			width = image.getWidth();
			height = image.getHeight();
			System.out.println("Sup it loaded correctly    Width: " + width + " Height: " + height);
			tiles = new int[width*height];
			for(int y = 0; y < height; y++){
				for(int x = 0; x < width; x++){
					pixel = image.getRGB(x, y);
					red = (pixel >> 16) & 0xff; 
					green = (pixel >> 8) & 0xff;
					blue = (pixel) & 0xff;
					
					if(x >= 0 && x <= 19 && y >= 0 && y <= 14 && vc.room == 1)levelCode(x, y);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void levelCode(int x, int y){
		
		//Draws grass tile
		if(red == 255 && green == 255 && blue == 255)tiles[x+y*width] = 1;
		
		//Draws brick tile
		if(red == 127 && green == 0 && blue == 0)tiles[x+y*width] = 3;
	}
}
