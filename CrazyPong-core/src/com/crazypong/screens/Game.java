package com.crazypong.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.crazypong.Settings;
import com.crazypong.crazypong;
import com.crazypong.entity.Ball;
import com.crazypong.entity.GameObject;
import com.crazypong.entity.Player;
import com.crazypong.entity.Wall;
import com.crazypong.graphics.Render;
import com.crazypong.logic.InputHandler;
import com.crazypong.util.ContactListener;

public class Game implements Screen {
	
	// Create a gravity vector, x = horizontal gravity, y = vertical gravity.
	public static Vector2 gravity = new Vector2(0,0);
	
	// Create an instance of the crazy pong game
	final crazypong game;
	
	// Create a new camera
	public OrthographicCamera camera;
	public Viewport viewPort;
    
	// FPS and Time Step for Box2D and Rendering
	public int currentFPS;
    public float stepSize = 1/45f;
    public static float timeSlow = 1;
    
    // Scores
    public int playeroneScore, playertwoScore;
    
	 // create a new box2D environment * MUST BE BEFORE OBJECTS ARE CREATED *
    public static World crazyPongPhysics = new World(gravity, true);
    public Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
    public Matrix4 debugMatrix;
	
	// Create game objects, these are auto-added to arrays (see class source)
	public static Player playerOne = new Player(Color.FIREBRICK, Settings.winLeft + Settings.playerWidth, 0, 1);
	public static Player playerTwo = new Player(Color.SKY, Settings.winRight - Settings.playerWidth, 0, 2);
	public static Ball ballOne = new Ball(Color.WHITE, 0, 0);
	public static Wall botWall = new Wall(Color.GOLDENROD, Settings.winLeft + Settings.windowWidth/2, Settings.winBot + 10, Settings.windowWidth, 10);
	public static Wall topWall = new Wall(Color.GOLDENROD, Settings.winLeft + Settings.windowWidth/2, Settings.winTop - 10, Settings.windowWidth, 10);
	//public static Wall midWall1 = new Wall(Color.CHARTREUSE, 40, 40, 1, 5);
	//public static Wall midWall2 = new Wall(Color.CHARTREUSE, 40, 10, 1, 5);
	public static ArrayList<GameObject> objectArray = new ArrayList<GameObject>();
	
	
	Game(crazypong gam) {
		Box2D.init();
		
		// Magic! cant remember what this does, check libgdx documentation
		this.game = gam;

		// Setup Input
		InputHandler inputHandler = new InputHandler();
		Gdx.input.setInputProcessor(inputHandler); // Set newly create input handler as the main input handling processor.
		
        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        viewPort = new FitViewport(Settings.windowWidth,Settings.windowHeight, camera);
        viewPort.apply();
        camera.update();
        
        // set debug matrix for box2d
        debugMatrix = new Matrix4(camera.combined);
        debugMatrix.scale(Settings.MTP, Settings.MTP, 1f);
        


        // Create shape renderer
        Render.createShapeRenderer(camera);
       
        //create collision detector.
        ContactListener collisionDetector = new ContactListener();
        crazyPongPhysics.setContactListener(collisionDetector);   
        
        
        // Setup object array and add items.
        objectArray.add(playerOne);
        objectArray.add(playerTwo);
        objectArray.add(ballOne);
        objectArray.add(topWall);
        objectArray.add(botWall);
        //objectArray.add(midWall1);
        //objectArray.add(midWall2);
        
        // Add force to ball to move it
        ballOne.box2DBody.applyForceToCenter(0.5f, 0.0f, true);
}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// Check movementType, 0 = with gravity, 1 = no gravity
		if(Settings.gravity){
				gravity.set(0, -4.0f);
				crazyPongPhysics.setGravity(gravity);
		} else {
			gravity.set(0, 0);
			crazyPongPhysics.setGravity(gravity);
		}
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
		
		// tell the camera to update its matrices.
		camera.update();
		
/*        // Begin Game object rendering
        game.batch.getProjectionMatrix().set(camera.combined);
        game.batch.begin();        

        game.batch.end();*/

        // begin HUD rendering
		game.hudBatch.getProjectionMatrix().set(camera.combined);
        game.hudBatch.begin();
        game.font.setColor(Color.WHITE);
        game.font.draw(game.hudBatch, playeroneScore + " : " + playertwoScore, Settings.windowWidth/2 - 10, Settings.windowHeight/2);
        
        game.font.setColor(Color.DARK_GRAY);
        game.font.draw(game.hudBatch, "SPACE : Reset", Settings.windowWidth/2, Settings.windowHeight/2 - 140);
        game.font.draw(game.hudBatch, "G : Gravity [" + Settings.gravity + "]" , Settings.windowWidth/2, Settings.windowHeight/2 - 160);
        game.font.draw(game.hudBatch, "B : Box2D Debug [" + Settings.debugRender + "]", Settings.windowWidth/2, Settings.windowHeight/2 - 180);
        game.font.draw(game.hudBatch, "X : Slow Time [" + Game.timeSlow + "]", Settings.windowWidth/2, Settings.windowHeight/2 - 200);
        game.font.draw(game.hudBatch, "Z : Reset Time", Settings.windowWidth/2, Settings.windowHeight/2 - 220);
        game.font.draw(game.hudBatch, currentFPS + " FPS" , Settings.windowWidth/2,Settings.windowHeight/2 - 240);
        game.hudBatch.end();
        
        // Render Objects        
        if(!Settings.debugRender)
        Render.renderObjects(objectArray);
		
        if(Settings.debugRender)
		debugRenderer.render(crazyPongPhysics, debugMatrix);

        // Update box2d physics objects and do a timestep
		for (Player i : Player.playerArray)
			i.update();
		
		for (Ball i : Ball.ballArray)
			i.update();
		
		crazyPongPhysics.step(stepSize * timeSlow, 6, 2);
		
		// check if player scored and reset game
		playerScored();

        currentFPS = Gdx.graphics.getFramesPerSecond();
        
	}

	@Override
	public void resize(int width, int height) {
		viewPort.update(width, height);
		camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		game.batch.dispose();
		
	}
	
	public static void reset(){
		ballOne.box2DBody.setTransform(0, 0, 0);
		ballOne.box2DBody.setLinearVelocity(5, 0);
		ballOne.box2DBody.setAngularVelocity(0);
		playerOne.box2DBody.setTransform(Game.playerOne.currentPos.x,Game.playerOne.currentPos.y,0);
		playerOne.box2DBody.setLinearVelocity(0, 0.05f);
		playerTwo.box2DBody.setTransform(Game.playerTwo.currentPos.x,Game.playerTwo.currentPos.y,0);
		playerTwo.box2DBody.setLinearVelocity(0, 0.05f);
	}
	
	public void playerScored(){
		Vector2 ballPos = ballOne.box2DBody.getPosition();
		ballPos.x *= Settings.MTP;
		ballPos.y *= Settings.MTP;
		if (ballPos.x < -Settings.windowWidth/2 - 250){
			playertwoScore++;
			reset();
		} else if (ballPos.x > Settings.windowWidth/2 + 250){
			playeroneScore++;
			reset();
		}
	}
}
