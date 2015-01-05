package com.dipiuay.bouncingballgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.Surface;
import android.view.View;

public class PlaygroundView extends View {

	private static final Logger logger = new Logger("PlaygroundView");
	private static final float PlaygroundStrokeWidth = 50;
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
		logger.log("Canvas size: (" + this.width + "; " + this.height + ")");

		Game game = GameApplication.getGame();
		this.applyPlaygroundTransformation(game);

		//canvas.drawColor(Color.CYAN);
		this.drawPlaygroundBounds(game);	
		this.drawCircle(game.target);	
		this.drawCircle(game.ball);
		
		this.invalidate();
	}
	
	private void applyPlaygroundTransformation(Game game){
		int rotation = GameApplication.getRotation();
		float gameCenterX = game.rectangle.centerX();
		float gameCenterY = game.rectangle.centerY();
		float availableWidth = this.width - 2 * PlaygroundStrokeWidth;
		float availableHeight = this.height - 2 * PlaygroundStrokeWidth;
		float gameWidth = game.width();
		float gameHeight = game.height();
		float angle = 0;
		
		switch(rotation){
			case Surface.ROTATION_0:
				angle = 0;
				this.scale = Math.min(availableWidth / gameWidth, availableHeight / gameHeight);
				break;
			case Surface.ROTATION_90:
				angle = 90;
				this.scale = Math.min(availableWidth / gameHeight, availableHeight / gameWidth);
				break;
			case Surface.ROTATION_180:
				angle = 180;
				this.scale = Math.min(availableWidth / gameWidth, availableHeight / gameHeight);
				break;
			case Surface.ROTATION_270:
				angle = 270;
				this.scale = Math.min(availableWidth / gameHeight, availableHeight / gameWidth);
				break;
		}
				
		//logger.log("Is canvas matrix identity: " + (this.canvas.getMatrix().isIdentity() ? "yes" : "no"));
		this.canvas.translate(this.width / 2.0f - gameCenterX, this.height / 2.0f - gameCenterY);
		this.canvas.rotate(-angle, gameCenterX, gameCenterY);
		this.canvas.scale(scale, scale, gameCenterX, gameCenterY);
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
		PointF position = circle.getPosition();
		float radius = circle.getRadius();
		
		this.setColor(circle.getColor());
		this.setStyle(Paint.Style.FILL);
		this.setStrokeWidth(0);
		this.canvas.drawCircle(position.x, position.y, radius, this.paint);
	}
	
	private void drawPlaygroundBounds(Game game){
		RectF rect = game.rectangle;
		float w = PlaygroundStrokeWidth / this.scale / 2.0f;
		RectF enlargedRect = new RectF(rect.left - w, rect.top - w, rect.right + w, rect.bottom + w);
		
		this.setColor(Color.BLUE);
		this.setStyle(Paint.Style.STROKE);
		this.setStrokeWidth(PlaygroundStrokeWidth);
		this.canvas.drawRect(enlargedRect, this.paint);
	}	
}
