package com.dipiuay.fallingrocks;

import android.app.Activity;
import android.os.Bundle;
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
	
	private TextView xCoordTextView;
	private TextView yCoordTextView;
	private TextView zCoordTextView;
	private TextView vCoordTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_player_game);
		this.xCoordTextView = (TextView)this.findViewById(R.id.accelerometer_x_coordinate_textView);
		this.yCoordTextView = (TextView)this.findViewById(R.id.accelerometer_y_coordinate_textView);
		this.zCoordTextView = (TextView)this.findViewById(R.id.accelerometer_z_coordinate_textView);
		this.vCoordTextView = (TextView)this.findViewById(R.id.accelerometer_v_coordinate_textView);
		
		this.yCoordTextView.setText("custom y coordinate");
		this.zCoordTextView.setText("custom z coordinate");
		this.vCoordTextView.setText("custom v coordinate");
	}
	
	private void SetXAccelerationText(double acceleration)
	{
		this.xCoordTextView.setText("acceleration x = " + acceleration);		
	}
	
	private void SetAccelerationText(AccelerationDirection direction, double acceleration){
		switch(direction){
			case X:
				
			break;
			case Y:
				
				break;
			case Z:
				
				break;
			case V:
				
				break;
			default:
//				throw new Exception();
			break;
		}
	}
}
