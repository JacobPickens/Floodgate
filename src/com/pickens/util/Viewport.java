package com.pickens.util;

public class Viewport {

	public static int getMaxX(int w) {
		return w/Constants.TILE_SIZE;
	}
	
	public static int getMaxY(int h) {
		return h/Constants.TILE_SIZE;
	}
	
}
