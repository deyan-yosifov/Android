package com.dipiuay.bouncingballgame;

import java.util.TimerTask;

public class BallMovementTask extends TimerTask {

	private final Game game;
	
	public BallMovementTask(Game game){
		this.game = game;
	}
	
	@Override
	public void run() {
		game.onChange();
	}

}
