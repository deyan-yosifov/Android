package com.dipiuay.bouncingballgame;

import android.graphics.PointF;

public class Algebra {
	public static final float epsilon = 1E-6f;
	
	public static boolean isZero(float a, float eps){
		return Math.abs(a) < eps;
	}
	
	public static boolean isZero(float a){
		return isZero(a, epsilon);
	}
	
	public static boolean isZero(PointF point, float eps){
		return isZero(Algebra.getLength(point), eps);		
	}
	
	public static boolean isZero(PointF point){
		return isZero(point, epsilon);
	}
	
	public static boolean areEqual(float a, float b, float eps){
		return isZero(a-b, eps);
	}
	
	public static boolean areEqual(float a, float b){
		return areEqual(a, b, epsilon);
	}
	
	public static PointF add(PointF a, PointF b){
		return new PointF(a.x + b.x, a.y + b.y);
	}
	
	public static PointF substract(PointF a, PointF b){
		return new PointF(a.x - b.x, a.y - b.y);
	}
	
	public static PointF multiply(float number, PointF point){
		return new PointF((float)number * point.x, (float)number * point.y);
	}
	
	public static float dotProduct(PointF a, PointF b){
		return a.x * b.x  + a.y * b.y;
	}
	
	public static PointF getUnitVector(PointF vector){
		float length = Algebra.getLength(vector);
		
		if(isZero(length)){
			throw new IllegalArgumentException("Cannot find unit vector of zero!");
		}
		
		return Algebra.multiply(1 / length, vector);
	}
	
	public static float getLength(PointF a){
		return (float)Math.sqrt(dotProduct(a, a));
	}
}
