package com.dipiuay.fallingrocks;

import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SinglePlayerGameActivity extends Activity {
	
	private enum AccelerationDirection {
		X,
		Y,
		Z,
		V		
	}
	
	private static final Logger logger = new Logger("SinglePlayerGameActivity");
	private TextView xCoordTextView;
	private TextView yCoordTextView;
	private TextView zCoordTextView;
	private TextView vCoordTextView;
	private SensorManager sensorManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		logger.Log("OnCreate");
		setContentView(R.layout.activity_single_player_game);
		this.xCoordTextView = (TextView)this.findViewById(R.id.accelerometer_x_coordinate_textView);
		this.yCoordTextView = (TextView)this.findViewById(R.id.accelerometer_y_coordinate_textView);
		this.zCoordTextView = (TextView)this.findViewById(R.id.accelerometer_z_coordinate_textView);
		this.vCoordTextView = (TextView)this.findViewById(R.id.accelerometer_v_coordinate_textView);

		this.xCoordTextView.setText("custom x coordinate");
		this.yCoordTextView.setText("custom y coordinate");
		this.zCoordTextView.setText("custom z coordinate");
		this.vCoordTextView.setText("custom v coordinate");
		
		this.sensorManager = (SensorManager) getSystemService(this.SENSOR_SERVICE);
		List<Sensor> allSensors = this.sensorManager.getSensorList(Sensor.TYPE_ALL);
		logger.Log("Sensors count = " + allSensors.size());
		for(Iterator<Sensor> i = allSensors.iterator(); i.hasNext(); ) {
		    Sensor sensor = i.next();
		    logger.Log(sensor.getName() + " --- " + sensor.getType());
		}
		
		Sensor acceleromator = this.sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		logger.Log((acceleromator != null ? "Acceleromator found!" : "No accereromator :("));
		logger.Log(acceleromator.getName() + "---" + acceleromator.getType());
	}
	
	private void SetAccelerationText(AccelerationDirection direction, double acceleration){
		switch(direction){
			case X:
				this.xCoordTextView.setText("acceleration x = " + acceleration);
			break;
			case Y:
				this.yCoordTextView.setText("acceleration y = " + acceleration);
				break;
			case Z:
				this.zCoordTextView.setText("acceleration z = " + acceleration);
				break;
			case V:
				this.vCoordTextView.setText("acceleration v = " + acceleration);
				break;
			default:
//				throw new Exception();
			break;
		}
	}
}
