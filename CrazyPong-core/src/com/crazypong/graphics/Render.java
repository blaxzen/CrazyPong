package com.crazypong.graphics;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.crazypong.Settings;
import com.crazypong.entity.Ball;
import com.crazypong.entity.GameObject;
import com.crazypong.entity.Player;
import com.crazypong.entity.Wall;

public class Render {
	
	public static ShapeRenderer shapeRenderer; // Static variable to store the shape renderer
	public static float width, height, radius;
	public static Vector2 currentPos;
	
	// Initialise the shapeRenderer object and set its mode to auto shape type
	public static void createShapeRenderer(Camera gameCamera){
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setAutoShapeType(true); // If set to TRUE - this will flush an entire batch if a new shape type is met (filled, line, point)
		shapeRenderer.setProjectionMatrix(gameCamera.combined);
	}
	
	// Render a game object, checks for balls and players
	public static void drawObject(GameObject theObject){
		currentPos = theObject.box2DBody.getPosition();	
		System.out.println("CurrentPos: X:" + currentPos.x + "," + currentPos.y);
		
		if (theObject instanceof Player){
			width = ((Player) theObject).width * Settings.MTP;
			height = ((Player) theObject).height * Settings.MTP;
			currentPos.x = (currentPos.x * Settings.MTP) - width/2;
			currentPos.y = (currentPos.y * Settings.MTP) - height/2;
			shapeRenderer.rect(currentPos.x, currentPos.y, width, height);
		}
		else if (theObject instanceof Ball){
			radius = ((Ball) theObject).radius * Settings.MTP;
			currentPos.x = (currentPos.x * Settings.MTP);
			currentPos.y = (currentPos.y * Settings.MTP);
			shapeRenderer.circle(currentPos.x, currentPos.y, radius);
		}
		else if (theObject instanceof Wall){
			width = ((Wall) theObject).width * Settings.MTP;
			height = ((Wall) theObject).height * Settings.MTP;
			currentPos.x = (currentPos.x * Settings.MTP) - width/2;
			currentPos.y = (currentPos.y * Settings.MTP) - height/2;
			shapeRenderer.rect(currentPos.x, currentPos.y, width,height);
		}
		
	}
	
	// Render an array of game objects
	public static void renderObjects(ArrayList<GameObject> objectArray){
		shapeRenderer.begin(ShapeType.Filled);
		for (GameObject i : objectArray){
			shapeRenderer.setColor(i.color);
			drawObject(i);
		}
		shapeRenderer.end();
	}

}
