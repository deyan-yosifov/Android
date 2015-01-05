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
	
	public PlaygroundView(Context context) {
		super(context);
		
		this.paint = new Paint();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		logger.log("onDraw");

		Game game = GameApplication.getGame();
		this.width = canvas.getWidth();
		this.heigth = canvas.getHeight();
			
		logger.log("Canvas size: (" + this.width + "; " + this.heigth + ")");

		//canvas.drawColor(Color.CYAN);
		this.drawRectangleBounds(canvas);
		this.drawCircle(canvas, game.ball);
		this.drawCircle(canvas, game.target);
		
		//this.invalidate();
	}
	
	private void drawCircle(Canvas canvas, ICircleObject circle){
		RectF rect = new RectF();
	}
	
	private void drawRectangleBounds(Canvas canvas){
		RectF rect = new RectF(0, 0, this.width, this.heigth);

		this.paint.setColor(Color.MAGENTA);
		this.paint.setStyle(Paint.Style.STROKE);
		this.paint.setStrokeWidth(50);
		canvas.drawRect(rect, this.paint);
		
		this.paint.setColor(Color.RED);
		this.paint.setStyle(Paint.Style.FILL);
		this.paint.setStrokeWidth(0);
		canvas.drawOval(rect,  this.paint);		
	}

	
}
