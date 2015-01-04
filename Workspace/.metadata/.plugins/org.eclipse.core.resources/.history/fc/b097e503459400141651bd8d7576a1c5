package com.dipiuay.bouncingballgame;

import android.app.Application;

public class GameApplication extends Application {

	private static GameApplication instance;
	private static final Logger logger = new Logger("GameApplication");
	private Game game;
	
	public GameApplication(){
		logger.log("Constructor call");
		this.game = new Game();
		GameApplication.instance = this;
	}
	
	@Override
	public void onCreate() {
		logger.log("OnCreate");
		super.onCreate();
	}
	
	public static Game getGame()
	{
		return GameApplication.instance.game;
	}
}
