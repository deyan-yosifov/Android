package com.dipiuay.bouncingballgame;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

public class GameActivity extends ActionBarActivity implements GameListener {
	
	private static final Logger logger = new Logger("GameActivity");
	private PlaygroundView playground;
	private Game game;
	
	public GameActivity(){
		logger.log("constructor call");
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		logger.log("OnCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		ViewGroup viewGroup = (ViewGroup) this.findViewById(R.id.playground_owner);
		this.playground = new PlaygroundView(this);
		viewGroup.addView(this.playground);
		
		this.game = GameApplication.getGame();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

	@Override
	protected void onResume() {
		logger.log("onResume");
		super.onResume();
		this.game.play(this);
	}

	@Override
	protected void onPause() {
		logger.log("onPause");
		super.onPause();
		this.game.pause();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void OnGameChanged(GameInfo gameInfo) {
		this.playground.OnGameChanged(gameInfo);		
	}
}
