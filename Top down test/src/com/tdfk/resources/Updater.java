package com.tdfk.resources;

public class Updater {
	
	private static String currentVersion, newVersion;
	public static int update = 0;
	
	public static void chechForUpdate(boolean isAuto){
		
		currentVersion = TextFile.readFile("./version.txt");
	}
}
