package com.crazypong;

// Global settings for the game

public class Settings {
	
	public static int windowWidth = 1920/2;
	public static int windowHeight = 1080/2;
    public static int winLeft = - windowWidth/2;
    public static int winRight = windowWidth/2;
    public static int winTop = windowHeight/2;
    public static int winBot = -windowHeight/2;
    
	public static float playerWidth = 20f;
	public static float playerHeight = 80f;
	public static float ballRadius = 8f;
	
	public static boolean gravity = false; // This affects the type of player movement, 0 is for physics based, 1 is for instant movement
	
	//pixels to meters
	public static final float PTM = 1/100f; // 1 pixel = 0.01 meters (10cm)
	public static final float MTP = 100f; // 1 metre = 100 pixels;
	
	public static boolean debugRender = false;
	

}
