package com.dipiuay.bouncingballgame;

public class Game {
	private static final Logger logger = new Logger("Game");
	private final double width;
	private final double height;
		
	public Game(double width, double height){
		this.width = width;
		this.height = height;
		
		logger.log("New Game with size: (" + this.width + ", " + this.height + ")");
	}
}
