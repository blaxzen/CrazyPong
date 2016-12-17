package com.crazypong.entity;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.crazypong.Settings;
import com.crazypong.screens.Game;

public class Ball extends GameObject{
	
	public static ArrayList<Ball> ballArray = new ArrayList<Ball>();
	public Fixture ballFixture;
	public FixtureDef ballFixtureDef;
	public CircleShape ballShape;
	public float radius;
	
	public Ball(Color color, float xPos, float yPos) {
		super(color, xPos, yPos);
		
		this.radius = Settings.ballRadius * Settings.PTM;
		this.currentPos.x *= Settings.PTM;
		this.currentPos.y *= Settings.PTM;
		
		// Box2D setup
		this.ballFixtureDef = new FixtureDef();
		this.box2DBodyDef.type = BodyType.DynamicBody;
		this.box2DBodyDef.position.set(this.currentPos.x, this.currentPos.y);
		this.box2DBodyDef.angle = 0;
		this.box2DBody = Game.crazyPongPhysics.createBody(this.box2DBodyDef);
		this.box2DBody.setAngularDamping(0.5f);
		this.ballShape = new CircleShape();
		this.ballShape.setRadius(this.radius);
		this.ballFixtureDef.shape = this.ballShape;
		this.ballFixtureDef.density = 0.1f; // Affects the mass of the object
		this.ballFixtureDef.friction = 0.5f; // Affects the friction of the object
		this.ballFixtureDef.restitution = 1.0f; // Affects the "bounciness" of the object
		this.ballFixture = this.box2DBody.createFixture(this.ballFixtureDef);
		this.box2DBody.setUserData(this);
		this.ballShape.dispose();
		
		ballArray.add(this);
	}
		
	public void update(){
		Vector2 velocity = this.box2DBody.getLinearVelocity();
		System.out.println("Ball Velocity is: " + velocity.x + "," + velocity.y);
		
		 
		// if the ball isnt moving left or right then
		if(velocity.x < 5 && velocity.x > 0)
			this.box2DBody.applyForceToCenter(0.02f, 0, true);
		else if(velocity.x > -5 && velocity.x < 0)
			this.box2DBody.applyForceToCenter(-0.02f, 0, true);

		/*
		
		// if the ball is moving up and down too fast
		if (velocity.y > 4.0)
			this.box2DBody.setLinearVelocity(this.box2DBody.getLinearVelocity().x, 4.0f);
		else if (velocity.y < -4.0f)
			this.box2DBody.setLinearVelocity(this.box2DBody.getLinearVelocity().x, -4.0f);
		
		// if the ball is moving left and right too fast
		if(velocity.x > 4.0)
			this.box2DBody.setLinearVelocity(4.0f, this.box2DBody.getLinearVelocity().y);
		else if(velocity.x < -4.0f)
			this.box2DBody.setLinearVelocity(-4.0f, this.box2DBody.getLinearVelocity().y);*/
		
		//System.out.println("Velocity is: " + this.box2DBody.getLinearVelocity().x + "," + this.box2DBody.getLinearVelocity().y);
	
	}

}
