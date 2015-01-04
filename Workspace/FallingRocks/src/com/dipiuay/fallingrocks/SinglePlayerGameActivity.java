package com.dipiuay.fallingrocks;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SinglePlayerGameActivity extends Activity implements SensorEventListener {
	
	private enum AccelerationDirection {
		X,
		Y,
		Z,
		Length,
		Gx,
		Gy,
		Gz,
		Glength,
		Lx,
		Ly,
		Lz,
		Llength
	}

	private static final double gravityFilterTimeConstant = 0.8;
	private static final Logger logger = new Logger("SinglePlayerGameActivity");
	private TextView xCoordTextView;
	private TextView yCoordTextView;
	private TextView zCoordTextView;
	private TextView lengthTextView;

	private TextView gravityXTextView;
	private TextView gravityYTextView;
	private TextView gravityZTextView;
	private TextView gravityLengthTextView;
	private TextView minGravityLengthTextView;
	private TextView maxGravityLengthTextView;
	private TextView averageGravityLengthTextView;
	
	private TextView linearAccelerationXTextView;
	private TextView linearAccelerationYTextView;
	private TextView linearAccelerationZTextView;
	private TextView linearAccelerationLengthTextView;
	private TextView minLinearLengthTextView;
	private TextView maxLinearLengthTextView;
	private TextView averageLinearLengthTextView;
	
	private TextView currentTimeTextView;
	private TextView currentEventTimeTextView;
	private TextView currentDeltaTimeTextView;
	private TextView currentEventDeltaTimeTextView;
	private TextView averageDeltaTimeTextView;
	private TextView minDeltaTimeTextView;
	private TextView maxDeltaTimeTextView;
	
	private final double[] gravity = new double[3];
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private int countOfSensorMeasurements;
	private Date firstTime;
	private long firstEventTime;
	private Date lastTime;
	private long lastEventTime;
	private double minDeltaTime;
	private double maxDeltaTime;
	private double minGravityLength;
	private double maxGravityLength;
	private double sumGravityLength;
	private double minLinearLength;
	private double maxLinearLength;
	private double sumLinearLength;
	
	
	private SensorManager sensorManager;
	private Sensor acceleromatorSensor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		logger.Log("OnCreate");
		setContentView(R.layout.activity_single_player_game);
		this.xCoordTextView = (TextView)this.findViewById(R.id.accelerometer_x_coordinate_textView);
		this.yCoordTextView = (TextView)this.findViewById(R.id.accelerometer_y_coordinate_textView);
		this.zCoordTextView = (TextView)this.findViewById(R.id.accelerometer_z_coordinate_textView);
		this.lengthTextView = (TextView)this.findViewById(R.id.accelerometer_length_textView);
		
		this.gravityXTextView = (TextView)this.findViewById(R.id.gravity_x_coordinate_textView);
		this.gravityYTextView = (TextView)this.findViewById(R.id.gravity_y_coordinate_textView);
		this.gravityZTextView = (TextView)this.findViewById(R.id.gravity_z_coordinate_textView);
		this.gravityLengthTextView = (TextView)this.findViewById(R.id.gravity_length_coordinate_textView);
		this.minGravityLengthTextView = (TextView)this.findViewById(R.id.min_gravity_length_textView);
		this.maxGravityLengthTextView = (TextView)this.findViewById(R.id.max_gravity_length_textView);
		this.averageGravityLengthTextView = (TextView)this.findViewById(R.id.average_gravity_length_textView);
		
		this.linearAccelerationXTextView = (TextView)this.findViewById(R.id.linearAcceleration_x_coordinate_textView);
		this.linearAccelerationYTextView = (TextView)this.findViewById(R.id.linearAcceleration_y_coordinate_textView);
		this.linearAccelerationZTextView = (TextView)this.findViewById(R.id.linearAcceleration_z_coordinate_textView);
		this.linearAccelerationLengthTextView = (TextView)this.findViewById(R.id.linearAcceleration_length__textView);
		this.minLinearLengthTextView = (TextView)this.findViewById(R.id.min_linearAcceleration_length__textView);
		this.maxLinearLengthTextView = (TextView)this.findViewById(R.id.max_linearAcceleration_length__textView);
		this.averageLinearLengthTextView = (TextView)this.findViewById(R.id.average_linearAcceleration_length__textView);
		
		this.currentTimeTextView = (TextView)this.findViewById(R.id.currentTime_textView);
		this.currentEventTimeTextView = (TextView)this.findViewById(R.id.currentEventTime_textView);
		this.currentDeltaTimeTextView = (TextView)this.findViewById(R.id.currentDeltaTime_textView);
		this.currentEventDeltaTimeTextView = (TextView)this.findViewById(R.id.currentEventDeltaTime_textView);
		this.averageDeltaTimeTextView = (TextView)this.findViewById(R.id.averageDeltaTime_textView);
		this.minDeltaTimeTextView = (TextView)this.findViewById(R.id.minDeltaTime_textView);
		this.maxDeltaTimeTextView = (TextView)this.findViewById(R.id.maxDeltaTime_textView);
		
		this.sensorManager = (SensorManager) getSystemService(this.SENSOR_SERVICE);		
		this.acceleromatorSensor = this.sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	}
	
	@Override
	protected void onPause() {
		logger.Log("OnPause");
		super.onPause();
		this.unregisterSensors();
	}

	@Override
	protected void onResume() {
		logger.Log("OnResume");
		super.onResume();
		this.registerSensors();
	}
	
	private void registerSensors(){
		if(this.acceleromatorSensor != null){
			this.sensorManager.registerListener(this, this.acceleromatorSensor, SensorManager.SENSOR_DELAY_NORMAL);
		}
		else{
			MessageBoxHelper.Alert(this, "No acceleromator sensor found!");
		}
	}
	
	private void unregisterSensors(){
		this.sensorManager.unregisterListener(this);
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
			case Length:
				this.lengthTextView.setText("acceleration length = " + acceleration);
				break;
			case Gx:
				this.gravityXTextView.setText("gravity x = " + acceleration);
				break;
			case Gy:
				this.gravityYTextView.setText("gravity y = " + acceleration);
				break;
			case Gz:
				this.gravityZTextView.setText("gravity z = " + acceleration);
				break;
			case Glength:
				this.gravityLengthTextView.setText("gravity length = " + acceleration);
				break;
			case Lx:
				this.linearAccelerationXTextView.setText("linear x = " + acceleration);
				break;
			case Ly:
				this.linearAccelerationYTextView.setText("linear y = " + acceleration);
				break;
			case Lz:
				this.linearAccelerationZTextView.setText("linear z = " + acceleration);
				break;
			case Llength:
				this.linearAccelerationLengthTextView.setText("linear length = " + acceleration);
				break;
			default:
//				throw new Exception();
			break;
		}
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		Date currentTime = new Date();	
		long currentEventTime = event.timestamp;
		Date currentEventDate = new Date(currentEventTime);
		double[] acceleration = new double[3];
		acceleration[0] = event.values[0];
		acceleration[1] = event.values[1];
		acceleration[2] = event.values[2];		
		
		if(this.firstTime == null){
			this.firstTime = currentTime;
			this.firstEventTime = currentEventTime;
			this.gravity[0] = event.values[0];
			this.gravity[1] = event.values[1];
			this.gravity[2] = event.values[2];
			this.sumGravityLength = this.sumLinearLength = this.countOfSensorMeasurements = 0;
			this.minDeltaTime = this.minGravityLength = this.minLinearLength = Double.MAX_VALUE;
			this.maxDeltaTime = this.maxGravityLength = this.maxLinearLength = -Double.MAX_VALUE;			
		}
		else{				
			this.countOfSensorMeasurements += 1;
			double deltaTime = (currentTime.getTime() - this.lastTime.getTime()) / 1000.0;
			double deltaEventTime = (currentEventTime - this.lastEventTime) / 1E+9;
			double deltaStartTime = (currentTime.getTime() - this.firstTime.getTime()) / 1000;	
			double averageDeltaTime = deltaStartTime / this.countOfSensorMeasurements;			
			this.minDeltaTime = Math.min(this.minDeltaTime, deltaTime);
			this.maxDeltaTime = Math.max(this.maxDeltaTime, deltaTime);			
			
			double alpha = gravityFilterTimeConstant / (gravityFilterTimeConstant + deltaTime);
			this.gravity[0] = alpha * this.gravity[0] + (1-alpha) * event.values[0];
			this.gravity[1] = alpha * this.gravity[1] + (1-alpha) * event.values[1];
			this.gravity[2] = alpha * this.gravity[2] + (1-alpha) * event.values[2];
			double gravityLength = this.CalculateLength(this.gravity);
			this.sumGravityLength += gravityLength;
			this.minGravityLength = Math.min(this.minGravityLength, gravityLength);
			this.maxGravityLength = Math.max(this.maxGravityLength, gravityLength);
			
			this.SetAccelerationText(AccelerationDirection.Gx, this.gravity[0]);
			this.SetAccelerationText(AccelerationDirection.Gy, this.gravity[1]);
			this.SetAccelerationText(AccelerationDirection.Gz, this.gravity[2]);
			this.SetAccelerationText(AccelerationDirection.Glength, gravityLength);	
			this.averageGravityLengthTextView.setText("average gravity: " + (this.sumGravityLength / this.countOfSensorMeasurements));
			this.minGravityLengthTextView.setText("min gravity: " + this.minGravityLength);
			this.maxGravityLengthTextView.setText("max gravity: " + this.maxGravityLength);
			
			this.SetAccelerationText(AccelerationDirection.X, acceleration[0]);
			this.SetAccelerationText(AccelerationDirection.Y, acceleration[1]);
			this.SetAccelerationText(AccelerationDirection.Z, acceleration[2]);	
			this.SetAccelerationText(AccelerationDirection.Length, this.CalculateLength(acceleration));
			
			double[] linearAcceleration = new double[3];
			linearAcceleration[0] = acceleration[0] - this.gravity[0];
			linearAcceleration[1] = acceleration[1] - this.gravity[1];
			linearAcceleration[2] = acceleration[2] - this.gravity[2];
			double linearLength = this.CalculateLength(linearAcceleration);
			this.sumLinearLength += linearLength;
			this.minLinearLength = Math.min(this.minLinearLength, linearLength);
			this.maxLinearLength = Math.max(this.maxLinearLength, linearLength);
			
			this.SetAccelerationText(AccelerationDirection.Lx, linearAcceleration[0]);
			this.SetAccelerationText(AccelerationDirection.Ly, linearAcceleration[1]);
			this.SetAccelerationText(AccelerationDirection.Lz, linearAcceleration[2]);	
			this.SetAccelerationText(AccelerationDirection.Llength, linearLength);
			this.averageLinearLengthTextView.setText("average linear: " + (this.sumLinearLength / this.countOfSensorMeasurements));
			this.minLinearLengthTextView.setText("min linear: " + this.minLinearLength);
			this.maxLinearLengthTextView.setText("max linear: " + this.maxLinearLength);

			this.currentTimeTextView.setText("Current time: " + dateFormat.format(currentTime));
			this.currentEventTimeTextView.setText("Curent event time: " + dateFormat.format(currentEventDate));
			this.currentDeltaTimeTextView.setText("Current dt: " + deltaTime);
			this.currentEventDeltaTimeTextView.setText("Current event dt: " +  deltaEventTime);
			this.averageDeltaTimeTextView.setText("Average dt: " + averageDeltaTime);
			this.minDeltaTimeTextView.setText("Min dt: " + this.minDeltaTime);
			this.maxDeltaTimeTextView.setText("Max dt: " + this.maxDeltaTime);			
		}		

		this.lastTime = currentTime;
		this.lastEventTime = currentEventTime;
	}
	
	private double CalculateLength(double[] vector) {
		return Math.sqrt(vector[0] * vector[0] + vector[1] * vector[1] + vector[2] * vector[2]);
	}
}
