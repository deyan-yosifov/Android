package com.dipiuay.bouncingballgame;

public class Logger {
	private String activityName;
	
	public Logger(String activityName){
		this.activityName = activityName;		
	}
	
	public void log(String text){
		android.util.Log.d(this.activityName, text);
	}
}
