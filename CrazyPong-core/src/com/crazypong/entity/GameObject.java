package com.crazypong.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;

// All gameObject classes implement the following class fields and methods:

public abstract class GameObject {
	
	public Color color;
	public final Vector2 currentPos;
	public BodyDef box2DBodyDef;
	public Body box2DBody;
	
	GameObject(Color color, float xPos, float yPos) {
		this.color =  new Color(color);
		this.currentPos = new Vector2(xPos,yPos);
		this.box2DBodyDef = new BodyDef();
	}
	
	public void update(){
		
	}
	
}
