package com.dipiuay.bouncingballgame;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

public class GameActivity extends ActionBarActivity {
	
	private static final Logger logger = new Logger("GameActivity");
	
	public GameActivity(){
		logger.log("constructor call");
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		logger.log("OnCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		ViewGroup viewGroup = (ViewGroup) this.findViewById(R.id.playground_owner);
		PlaygroundView playground = new PlaygroundView(this);
		viewGroup.addView(playground);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
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
}
