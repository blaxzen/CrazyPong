package com.crazypong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.crazypong.Settings;
import com.crazypong.crazypong;

public class MainMenu implements Screen{
	
	final crazypong game;
	OrthographicCamera camera;
	
	public MainMenu(final crazypong gam) {
		game = gam;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Settings.windowWidth, Settings.windowHeight);
	}

	@Override
	public void show() {
		// Can be used to play music or show animations when the screen is showing.
		
	}

	@Override
	public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Welcome to Crazy Pong", Settings.windowWidth/2 - 100, Settings.windowHeight/2);
        game.font.draw(game.batch, "Click to Start", Settings.windowWidth/2 - 100, Settings.windowHeight/2 - 40);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new Game(game));
            dispose();
            }
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
		// must be used to remove any created images/sounds
		
	}

}
