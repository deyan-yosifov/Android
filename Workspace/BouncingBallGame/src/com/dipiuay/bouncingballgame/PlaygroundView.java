package com.dipiuay.bouncingballgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class PlaygroundView extends View {

	private static final Logger logger = new Logger("PlaygroundView");
	private final Paint paint;
	private int width;
	private int heigth;
	private RectF rectangle;
	
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
		this.rectangle = new RectF(0, 0, this.width, this.heigth);
		logger.log("Canvas size: (" + this.width + "; " + this.heigth + ")");

		//canvas.drawColor(Color.CYAN);
		this.paint.setColor(Color.RED);
		canvas.drawOval(this.rectangle, this.paint);
	}

	
}