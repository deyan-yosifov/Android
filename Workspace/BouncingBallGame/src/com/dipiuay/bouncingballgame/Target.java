package com.dipiuay.bouncingballgame;

import android.graphics.Color;
import android.graphics.PointF;

public class Target implements ICircleObject {
	public PointF position;
	public float radius;
	
	public Target(){
		this.position = new PointF();
		this.radius = 0;
	}
	
	@Override
	public PointF getPosition() {
		return this.position;
	}
	@Override
	public float getRadius() {
		return this.radius;
	}
	@Override
	public int getColor() {
		return Color.WHITE;
	}
}
