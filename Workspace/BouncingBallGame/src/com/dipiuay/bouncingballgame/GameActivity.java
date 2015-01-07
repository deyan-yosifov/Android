package com.dipiuay.bouncingballgame;

import com.dipiuay.bouncingballgame.AccelerometerManager.AccelerometerEvent;
import com.dipiuay.bouncingballgame.AccelerometerManager.AccelerometerListener;

import android.support.v7.app.ActionBarActivity;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

public class GameActivity extends ActionBarActivity implements GameListener, AccelerometerListener {
	
	private static final Logger logger = new Logger("GameActivity");
	private static final double noise = 0.6;
	private AccelerometerManager accelerometerManager;
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
		
		SensorManager sensorManager = (SensorManager) getSystemService(this.SENSOR_SERVICE);		
		this.accelerometerManager = new AccelerometerManager(this, sensorManager);
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
		this.accelerometerManager.startListening();
	}

	@Override
	protected void onPause() {
		logger.log("onPause");
		super.onPause();
		this.game.pause();
		this.accelerometerManager.stopListening();
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

	@Override
	public void onAccelerometerChanged(AccelerometerEvent accelerometerEvent) {
		float ax = (float)accelerometerEvent.linearAcceleration.x;
		float ay = (float)accelerometerEvent.linearAcceleration.y;
		logger.log("ax: " + ax + "; ay: " + ay);
		
		if(Algebra.getLength(new PointF(ax, ay)) > noise){
			float angle = GameApplication.getRotationAngle();
			
			Matrix matrix = new Matrix();
			matrix.postRotate(angle);
			float[] point = { ax, ay };			
			matrix.mapPoints(point);
			PointF orientedAcceleration = new PointF(point[0], point[1]);
			logger.log("orientedAcceleration: " + orientedAcceleration);
			this.game.accelerate(orientedAcceleration);
		}
	}
}
