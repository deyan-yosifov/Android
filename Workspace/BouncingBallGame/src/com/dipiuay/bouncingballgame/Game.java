package com.dipiuay.bouncingballgame;

import android.graphics.RectF;

public class Game {
	private static final Logger logger = new Logger("Game class");
	private static final float actualDistanceInMeters = 20;
	public final RectF rectangle;
	public final Ball ball;
	public final Target target;
	
		
	public Game(float screenRatio){
		float width, height;
		
		if(screenRatio < 1){
			width = actualDistanceInMeters;
			height = width / screenRatio;
		}
		else{
			height = actualDistanceInMeters;
			width = height * screenRatio;
		}
		this.rectangle = new RectF(0, 0, width, height);
		this.ball = new Ball();
		this.target = new Target();
		
		logger.log("New Game with size: (" + this.width() + ", " + this.height() + ")");
		
		this.initializePlayground();
	}
	
	public float width() {
		return this.rectangle.width();	
	}
	
	public float height() {
		return this.rectangle.height();	
	}
	
	private void initializePlayground(){
		float minimalSize = Math.min(this.width(), this.height());
		
		this.ball.radius = minimalSize / 10;
		this.ball.position.x = this.width() * 0.5f;
		this.ball.position.y = this.height() * 0.8f;
		
		this.target.radius = this.ball.radius / 2;
		this.moveTargetRandomly();
	}
	
	private void moveTargetRandomly(){
		float randomX = (float)Math.random();
		float randomY = (float)Math.random();
		
		this.target.position.x = target.radius + (this.width() - 2 * target.radius) * randomX;
		this.target.position.y = target.radius + (this.height() - 2 * target.radius) * randomY;		
	}
}
