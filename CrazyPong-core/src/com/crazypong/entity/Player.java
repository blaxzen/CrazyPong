package com.crazypong.entity;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.crazypong.Settings;
import com.crazypong.logic.PlayerInput;
import com.crazypong.screens.Game;

public class Player extends GameObject {
	
	public static ArrayList<Player> playerArray = new ArrayList<Player>();
	public PlayerInput input;
	public Fixture playerFixture;
	public FixtureDef playerFixtureDef;
	public final int playerNumber;
	public PolygonShape playerShape;
	public float width, height;
	
	public Player(Color color, float xPos, float yPos, int playerNumber) {
		super(color, xPos, yPos);

		this.playerNumber = playerNumber;
		this.width = Settings.playerWidth * Settings.PTM;
		this.height = Settings.playerHeight * Settings.PTM;
		this.currentPos.x *= Settings.PTM;
		this.currentPos.y *= Settings.PTM;
		this.input = new PlayerInput();
		
		// Box2D Setup		
		this.playerFixtureDef = new FixtureDef();
		this.box2DBodyDef.type = BodyType.DynamicBody;
		this.box2DBodyDef.position.set(this.currentPos.x, this.currentPos.y);
		this.box2DBodyDef.angle = 0;
		this.box2DBody = Game.crazyPongPhysics.createBody(this.box2DBodyDef);

		if (Settings.gravity)
			this.box2DBody.setLinearDamping(0.1f);
		else
			this.box2DBody.setLinearDamping(0.0f);

		this.playerShape = new PolygonShape();
		this.playerShape.setAsBox(this.width/2, this.height/2);
		this.playerFixtureDef.shape = this.playerShape;
		this.playerFixtureDef.density = 1.0f; // Affects the mass of the object
		this.playerFixtureDef.friction = 0.5f; // Affects the friction of the object
		this.playerFixtureDef.restitution = 0.5f; // Affects the "bounciness" of the object
		this.playerFixture = this.box2DBody.createFixture(this.playerFixtureDef);
		this.box2DBody.setUserData(this);
		this.box2DBody.setActive(true);
		this.playerShape.dispose();
		
		playerArray.add(this);
	}
	
	public void update(){
		// Stop the player rotating
		this.box2DBody.setFixedRotation(true);
		
		// Make the X coordinate fixed.
		//this.box2DBody.setTransform(this.currentPos.x, this.box2DBody.getPosition().y, 0);
		
		// Check the game movement type, physics mode(0) and direct input (1)

		if (Settings.gravity){
			if (this.input.UP == true)
				this.box2DBody.setLinearVelocity(0, this.box2DBody.getLinearVelocity().y + 0.3f);					
			else if (this.input.DOWN == true)
				this.box2DBody.setLinearVelocity(0, this.box2DBody.getLinearVelocity().y - 0.3f);
	
			if (this.box2DBody.getLinearVelocity().y > 3f && this.box2DBody.getLinearVelocity().y > 0)
				this.box2DBody.setLinearVelocity(0, 3f);
			else if (this.box2DBody.getLinearVelocity().y < -5 && this.box2DBody.getLinearVelocity().y < 0)
				this.box2DBody.setLinearVelocity(0, -5);
			} 
		else{
			if (this.input.UP == true)
				this.box2DBody.setLinearVelocity(0, 5.0f);
			else if (this.input.DOWN == true)
				this.box2DBody.setLinearVelocity(0, -5.0f);
			else
				this.box2DBody.setLinearVelocity(0,0);
		}
			
		//System.out.println("Velocity is: " + this.box2DBody.getLinearVelocity().x + "," + this.box2DBody.getLinearVelocity().y);

	}

}
