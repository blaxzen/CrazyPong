package com.crazypong.entity;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.crazypong.Settings;
import com.crazypong.screens.Game;

public class Wall extends GameObject {
	
	public static ArrayList<Wall> wallArray = new ArrayList<Wall>();
	public Fixture wallFixture;
	public FixtureDef wallFixtureDef;
	public PolygonShape wallShape;
	public float width,height;
	
	public Wall(Color color, float xPos, float yPos, float width, float height) {
		super(color, xPos, yPos);
		this.width = width * Settings.PTM;
		this.height = height * Settings.PTM;
		this.currentPos.x *= Settings.PTM;
		this.currentPos.y *= Settings.PTM;
		
		// Box2D Setup		
		this.wallFixtureDef = new FixtureDef();
		this.box2DBodyDef.type = BodyType.StaticBody;
		this.box2DBodyDef.position.set(this.currentPos.x, this.currentPos.y);
		this.box2DBody = Game.crazyPongPhysics.createBody(this.box2DBodyDef);
		this.wallShape = new PolygonShape();
		this.wallShape.setAsBox(this.width/2, this.height/2);
		this.wallFixtureDef.shape = this.wallShape;
		this.wallFixture = this.box2DBody.createFixture(this.wallFixtureDef);
		this.box2DBody.setUserData(this);
		this.wallShape.dispose();
		
		wallArray.add(this);
	}

}
