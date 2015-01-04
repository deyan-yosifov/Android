package com.dipiuay.fallingrocks;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class AccelerometerManager implements SensorEventListener {

	public class AccelerometerEvent{
		private SensorEvent sensorEvent;
		
		public AccelerometerEvent(SensorEvent sensorEvent){
			this.sensorEvent = sensorEvent;
		}
		
		public SensorEvent getSensorEvent(){
			return this.sensorEvent;
		}
	}
	
	public interface AccelerometerListener {
		void onAccelerometerChanged(AccelerometerEvent accelerometerEvent);		
	}
	
	private final SensorManager sensorManager;
	private final AccelerometerListener listener;
	private final Sensor accelerometer;
	private final boolean isAccelerometerAvailable;
	private boolean isListening;
	
	public AccelerometerManager(AccelerometerListener listener, SensorManager sensorManager){
		this.listener = listener;
		this.sensorManager = sensorManager;
		this.accelerometer = this.sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		this.isAccelerometerAvailable = this.accelerometer != null;
		this.isListening = false;
	}
	
	public boolean isListening(){
		return this.isListening;
	}
	
	public boolean isAccelerometerAvailable(){
		return this.isAccelerometerAvailable;
	}
	
	public void StartListening(){
		this.GuardAccelerometer();
		
		if(!this.isListening){
			this.sensorManager.registerListener(this, this.accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
			this.isListening = true;
		}
	}
	
	public void StopListening(){
		this.GuardAccelerometer();
		
		if(this.isListening){
			this.sensorManager.unregisterListener(this);
			this.isListening = false;
		}
	}
	
	private void GuardAccelerometer(){
		if(!this.isAccelerometerAvailable){
			throw new IllegalStateException("No accelerometer sensor found!");
		}
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		
	}

}
