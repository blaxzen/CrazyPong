package com.crazypong;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.crazypong.screens.MainMenu;

// This is the entrypoint to the game

public class crazypong extends Game {
	public SpriteBatch batch;
	public SpriteBatch hudBatch;
    public BitmapFont font;

    public void create() {
    	batch = new SpriteBatch();
    	hudBatch = new SpriteBatch();    	
        font = new BitmapFont();
        this.setScreen(new MainMenu(this));
    }

    public void render() {
        super.render(); //important!
    }

    public void dispose() {
        batch.dispose();
        hudBatch.dispose();
        font.dispose();
    }
}
