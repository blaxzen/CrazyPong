package com.crazypong.logic;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.TextInputListener;
import com.crazypong.Settings;
import com.crazypong.screens.Game;

public class InputHandler implements InputProcessor{

		@Override
		public boolean keyDown(int keycode) {
			
			switch(keycode){
				case Keys.W:
				{
					Game.playerOne.input.setUP(true);
					break;
				}
				case Keys.A:
				{
					Game.playerOne.input.setLEFT(true);
					break;
				}
				case Keys.S:
				{
					Game.playerOne.input.setDOWN(true);
					break;
				}
				case Keys.D:
				{
					Game.playerOne.input.setRIGHT(true);
					break;
				}
				case Keys.UP:
				{
					Game.playerTwo.input.setUP(true);
					break;
				}
				case Keys.LEFT:
				{
					Game.playerTwo.input.setLEFT(true);
					break;
				}
				case Keys.DOWN:
				{
					Game.playerTwo.input.setDOWN(true);
					break;
				}
				case Keys.RIGHT:
				{
					Game.playerTwo.input.setRIGHT(true);
					break;
				}
				case Keys.ESCAPE:
				{
					Gdx.app.exit();
				}
				case Keys.SPACE:{
					Game.reset();

					break;
				}
				case Keys.G:{
					Settings.gravity = !Settings.gravity;
					Game.reset();
					break;
				}
				case Keys.X:{
					Game.timeSlow -= 0.05f;
					break;
				}
				case Keys.Z:{
					Game.timeSlow = 1;
					break;
				}
				case Keys.B:{
					Settings.debugRender = !Settings.debugRender; // Swaps boolean value
					break;
				}
				default:
				{
					System.out.println("Key not recognised: " + keycode);
					break;
				}
				
			}
			return false;
		}

		@Override
		public boolean keyUp(int keycode) {
			
			switch(keycode){
			case Keys.W:
			{
				Game.playerOne.input.setUP(false);
				break;
			}
			case Keys.A:
			{
				Game.playerOne.input.setLEFT(false);
				break;
			}
			case Keys.S:
			{
				Game.playerOne.input.setDOWN(false);
				break;
			}
			case Keys.D:
			{
				Game.playerOne.input.setRIGHT(false);
				break;
			}
			case Keys.UP:
			{
				Game.playerTwo.input.setUP(false);
				break;
			}
			case Keys.LEFT:
			{
				Game.playerTwo.input.setLEFT(false);
				break;
			}
			case Keys.DOWN:
			{
				Game.playerTwo.input.setDOWN(false);
				break;
			}
			case Keys.RIGHT:
			{
				Game.playerTwo.input.setRIGHT(false);
				break;
			}
			case Keys.ESCAPE:
			{
				Gdx.app.exit();
				break;
			}
		}
		return false;
		
	}
	
		// Following method is used for text input
		public static class TextInputHandler implements TextInputListener{
	
			@Override
			public void input(String text) {
				// TODO Auto-generated method stub
				
			}
	
			@Override
			public void canceled() {
				// TODO Auto-generated method stub
				
			}
			
		}

		@Override
		public boolean keyTyped(char character) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchDown(int screenX, int screenY, int pointer, int button) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchDragged(int screenX, int screenY, int pointer) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean scrolled(int amount) {
			// TODO Auto-generated method stub
			return false;
		}
		
		
}

