package com.dipiuay.bouncingballgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.Surface;
import android.view.View;

public class PlaygroundView extends View {

	private static final Logger logger = new Logger("PlaygroundView");
	private final Paint paint;
	private int width;
	private int height;
	private float scale;
	private Canvas canvas;
	
	public PlaygroundView(Context context) {
		super(context);
		
		this.paint = new Paint();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		logger.log("onDraw");

		this.canvas = canvas;
		this.width = this.canvas.getWidth();
		this.height = this.canvas.getHeight();
		float centerX = this.width / 2.0f;
		float centerY = this.height / 2.0f;
		logger.log("Canvas size: (" + this.width + "; " + this.height + ")");
		
		int rotation = GameApplication.getRotation();
		Game game = GameApplication.getGame();
		float gameCenterX = game.rectangle.centerX();
		float gameCenterY = game.rectangle.centerY();
		float angle = 0;
		
		switch(rotation){
			case Surface.ROTATION_0:
				angle = 0;
				this.scale = Math.min(this.width / game.width(), this.height / game.height());
				break;
			case Surface.ROTATION_90:
				angle = 90;
				this.scale = Math.min(this.width / game.height(), this.height / game.width());
				break;
			case Surface.ROTATION_180:
				angle = 180;
				this.scale = Math.min(this.width / game.width(), this.height / game.height());
				break;
			case Surface.ROTATION_270:
				angle = 270;
				this.scale = Math.min(this.width / game.height(), this.height / game.width());
				break;
		}
				
		logger.log("Is canvas matrix identity: " + (this.canvas.getMatrix().isIdentity() ? "yes" : "no"));
		this.canvas.translate(centerX - gameCenterX, centerY - gameCenterY);
		this.canvas.rotate(angle, gameCenterX, gameCenterY);
		this.canvas.scale(scale, scale, gameCenterX, gameCenterY);

		//canvas.drawColor(Color.CYAN);
		this.drawRectangleBounds(game.rectangle);		
		this.drawCircle(game.ball);
		this.drawCircle(game.target);
		
		//this.invalidate();
	}
	
	private void setColor(int color){
		this.paint.setColor(color);
	}
	
	private void setStyle(Paint.Style style){
		this.paint.setStyle(style);
	}
	
	private void setStrokeWidth(float width){
		this.paint.setStrokeWidth(width / this.scale);
	}
	
	private void drawCircle(ICircleObject circle){
		RectF rect = new RectF();
	}
	
	private void drawRectangleBounds(RectF rect){
		
		this.setColor(Color.MAGENTA);
		this.setStyle(Paint.Style.STROKE);
		this.setStrokeWidth(50);
		this.canvas.drawRect(rect, this.paint);
		
		this.setColor(Color.RED);
		this.setStyle(Paint.Style.FILL);
		this.setStrokeWidth(0);
		this.canvas.drawOval(rect,  this.paint);		
	}

	
}
