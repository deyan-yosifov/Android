package com.dipiuay.bouncingballgame;

import android.graphics.PointF;

public class Algebra {
	public static final double epsilon = 1E-6;
	
	public static boolean isZero(double a, double eps){
		return Math.abs(a) < eps;
	}
	
	public static boolean isZero(double a){
		return isZero(a, epsilon);
	}
	
	public static boolean isZero(PointF point, double eps){
		return isZero(Algebra.getLength(point), eps);		
	}
	
	public static boolean isZero(PointF point){
		return isZero(point, epsilon);
	}
	
	public static boolean areEqual(double a, double b, double eps){
		return isZero(a-b, eps);
	}
	
	public static boolean areEqual(double a, double b){
		return areEqual(a, b, epsilon);
	}
	
	public static PointF add(PointF a, PointF b){
		return new PointF(a.x + b.x, a.y + b.y);
	}
	
	public static PointF substract(PointF a, PointF b){
		return new PointF(a.x - b.x, a.y - b.y);
	}
	
	public static PointF multiply(double number, PointF point){
		return new PointF((float)number * point.x, (float)number * point.y);
	}
	
	public static double dotProduct(PointF a, PointF b){
		return a.x * b.x  + a.y * b.y;
	}
	
	public static double getLength(PointF a){
		return Math.sqrt(dotProduct(a, a));
	}
}
