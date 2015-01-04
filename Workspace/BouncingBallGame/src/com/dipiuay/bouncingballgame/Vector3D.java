package com.dipiuay.bouncingballgame;

public class Vector3D {
	public final double x;
	public final double y;
	public final double z;
	
	public Vector3D(){
		this(0, 0, 0);
	}
	
	public Vector3D(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double dotProduct(Vector3D other){
		return this.x * other.x + this.y * other.y + this.z * other.z;
	}
	
	public double getLegth(){
		return Math.sqrt(this.dotProduct(this));
	}
}
