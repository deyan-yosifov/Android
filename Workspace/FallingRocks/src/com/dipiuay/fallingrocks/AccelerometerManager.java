package com.dipiuay.fallingrocks;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class AccelerometerManager implements SensorEventListener {

	public class AccelerometerEvent{
		public final SensorEvent sensorEvent;
		public final Vector3D linearAcceleration;
		public final Vector3D gravityAcceleration;
		
		public AccelerometerEvent(SensorEvent sensorEvent, Vector3D linearAcceleration, Vector3D gravityAcceleration){
			this.sensorEvent = sensorEvent;
			this.linearAcceleration = linearAcceleration;
			this.gravityAcceleration = gravityAcceleration;
		}
	}
	
	public interface AccelerometerListener {
		void onAccelerometerChanged(AccelerometerEvent accelerometerEvent);		
	}
	
	private static final double gravityFilterTimeConstant = 0.8;
	private final SensorManager sensorManager;
	private final AccelerometerListener listener;
	private final Sensor accelerometer;
	private final boolean isAccelerometerAvailable;
	private final double[] gravity;
	private boolean isListening;
	private boolean isFirstListening;
	private long lastTimeStamp;
	
	public AccelerometerManager(AccelerometerListener listener, SensorManager sensorManager){
		this.listener = listener;
		this.sensorManager = sensorManager;
		this.accelerometer = this.sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		this.isAccelerometerAvailable = this.accelerometer != null;
		this.isListening = false;
		this.isFirstListening = true;
		this.gravity = new double[3];
	}
	
	public boolean isListening(){
		return this.isListening;
	}
	
	public boolean isAccelerometerAvailable(){
		return this.isAccelerometerAvailable;
	}
	
	public void startListening(){		
		if(this.isAccelerometerAvailable){		
			if(!this.isListening){
				this.isListening = true;
				this.isFirstListening = true;
				this.sensorManager.registerListener(this, this.accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
			}
		}
		else{
			AccelerometerManager.logAccelerometerNotFound();			
		}
	}
	
	public void stopListening(){
		if(this.isAccelerometerAvailable){
			if(this.isListening){
				this.sensorManager.unregisterListener(this);
				this.isListening = false;
			}			
		}
		else{
			AccelerometerManager.logAccelerometerNotFound();
		}		
	}
	
	private static void logAccelerometerNotFound(){
		android.util.Log.d("AccelerometerManager", "No accelerometer sensor found!");
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		long currentTimeStamp = event.timestamp;
		
		if(this.isFirstListening){
			this.isFirstListening = false;
			this.gravity[0] = event.values[0];
			this.gravity[1] = event.values[1];
			this.gravity[2] = event.values[2];	
		}
		else{
			// deltaTime is calculated in seconds in order to use the gravityFilterTimeConstant
			double deltaTime = (currentTimeStamp - this.lastTimeStamp) * 1E-9;
			double alpha = gravityFilterTimeConstant / (gravityFilterTimeConstant + deltaTime);
			this.gravity[0] = alpha * this.gravity[0] + (1-alpha) * event.values[0];
			this.gravity[1] = alpha * this.gravity[1] + (1-alpha) * event.values[1];
			this.gravity[2] = alpha * this.gravity[2] + (1-alpha) * event.values[2];			
		}		

		Vector3D g = new Vector3D(this.gravity[0], this.gravity[1], this.gravity[2]);
		Vector3D linearAcceleration = new Vector3D(event.values[0] - g.x, event.values[1] - g.y, event.values[2] - g.z);		
		this.listener.onAccelerometerChanged(new AccelerometerEvent(event, linearAcceleration, g));
		
		this.lastTimeStamp = currentTimeStamp;
	}

}
