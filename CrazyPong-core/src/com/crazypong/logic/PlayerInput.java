package com.crazypong.logic;

public class PlayerInput {
		
	public boolean UP;
	public boolean DOWN;
	public boolean LEFT;
	public boolean RIGHT;
	
	public PlayerInput(){
	this.UP = false;
	this.DOWN = false;
	this.LEFT = false;
	this.RIGHT = false;
	}

	public boolean isUP() {
		return UP;
	}

	public void setUP(boolean uP) {
		UP = uP;
	}

	public boolean isDOWN() {
		return DOWN;
	}

	public void setDOWN(boolean dOWN) {
		DOWN = dOWN;
	}

	public boolean isLEFT() {
		return LEFT;
	}

	public void setLEFT(boolean lEFT) {
		LEFT = lEFT;
	}

	public boolean isRIGHT() {
		return RIGHT;
	}

	public void setRIGHT(boolean rIGHT) {
		RIGHT = rIGHT;
	}




}
