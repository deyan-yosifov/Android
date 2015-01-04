package com.dipiuay.bouncingballgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class PlaygroundView extends View {

	private static final Logger logger = new Logger("PlaygroundView");
	private final Paint paint;
	private int width;
	private int heigth;
	private Matrix matrix;
	
	public PlaygroundView(Context context) {
		super(context);
		
		this.paint = new Paint();
		this.matrix = new Matrix();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		logger.log("onDraw");

		Game game = GameApplication.getGame();
		this.width = canvas.getWidth();
		this.heigth = canvas.getHeight();
		float centerX = this.width / 2.0f;
		float centerY = this.heigth / 2.0f;
		
		float scale = Math.max(this.width / game.width, this.heigth / game.height);
		
		this.matrix.set(null);
		this.matrix.postScale(scale, scale, centerX, centerY);
		
		logger.log("Canvas size: (" + this.width + "; " + this.heigth + ")");

		//canvas.drawColor(Color.CYAN);
		this.drawRectangleBounds(canvas);
		this.drawCircle(canvas, game.ball);
		this.drawCircle(canvas, game.target);
		
		this.invalidate();
	}
	
	private void drawCircle(Canvas canvas, ICircleObject circle){
//		RectF rect = new RectF()
	}
	
	private void drawRectangleBounds(Canvas canvas){
		RectF rect = new RectF(0, 0, this.width, this.heigth);
		matrix.mapRect(rect);	

		this.paint.setColor(Color.MAGENTA);
		this.paint.setStyle(Paint.Style.STROKE);
		this.paint.setStrokeWidth(5);
		canvas.drawRect(rect, this.paint);
	}

	
}
