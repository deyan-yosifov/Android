package com.dipiuay.bouncingballgame;

import android.graphics.PointF;
import android.graphics.RectF;

public class GameInfo {
	public final ICircleObject ball;
	public final ICircleObject target;
	public final RectF playground;
	
	public GameInfo(Game game){
			this.ball = GameInfo.Copy(game.ball);
			this.target = GameInfo.Copy(game.target);
			this.playground = GameInfo.Copy(game.rectangle);
	}
	
	private static ICircleObject Copy(final ICircleObject circle){
		final PointF position = Algebra.multiply(1, circle.getPosition());
		
		return new ICircleObject(){
			@Override
			public PointF getPosition() {
				return position;
			}
			@Override
			public float getRadius() {
				return circle.getRadius();
			}
			@Override
			public int getColor() {
				return circle.getColor();
			}
		};
	}
	
	private static RectF Copy(RectF rect){
		return new RectF(rect.left, rect.top, rect.right, rect.bottom);
	}
}
