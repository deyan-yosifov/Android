package com.dipiuay.bouncingballgame;

import android.graphics.Color;
import android.graphics.PointF;

public class Ball implements ICircleObject {
	private static final PointF defaultSpeed = new PointF(0.3f, 0.3f);
	public PointF position;
	public float radius;
	public PointF speed;
	
	public Ball(){
		this.speed = Ball.defaultSpeed;
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
		return Color.RED;
	}
}
