package com.pickens.util;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Images {

	public static SpriteSheet WALL_SHEET;
	public static SpriteSheet RIM_SHEET;
	public static SpriteSheet PLAYER_SHEET;
	
	public static SpriteSheet WATER_ANIMATION_SHEET;
	public static Animation WATER_ANIMATION;
	
	public static Image RED_OPEN;
	public static Image RED_CLOSED;
	
	public static Image GREEN_OPEN;
	public static Image GREEN_CLOSED;
	
	public static Image BLUE_OPEN;
	public static Image BLUE_CLOSED;
	
	public static Image BARRIER;
	
	public static Image INVENTORY_BUTTON;
	public static SpriteSheet RED_BUTTON;
	public static SpriteSheet GREEN_BUTTON;
	public static SpriteSheet BLUE_BUTTON;
	
	public static Image HEART_FULL;
	public static Image HEART_EMPTY;
	
	public static Image BUBBLE;
	
	public static SpriteSheet ITEMS;
	public static Image MAP;
	
	public static void load() {
		try {
			WALL_SHEET = new SpriteSheet("res/wall_floor_tiles.png", Constants.TILE_SIZE, Constants.TILE_SIZE);
			RIM_SHEET = new SpriteSheet("res/rim_tiles.png", Constants.TILE_SIZE, Constants.TILE_SIZE);
			PLAYER_SHEET = new SpriteSheet("res/player_sheet.png", Constants.TILE_SIZE, Constants.TILE_SIZE);
			WATER_ANIMATION_SHEET = new SpriteSheet(RIM_SHEET.getSubImage(0, Constants.TILE_SIZE*1, Constants.TILE_SIZE*2, Constants.TILE_SIZE), Constants.TILE_SIZE, Constants.TILE_SIZE);
			WATER_ANIMATION = new Animation(WATER_ANIMATION_SHEET, 1000);
			
			RED_OPEN = RIM_SHEET.getSubImage(1, 2);
			RED_CLOSED = RIM_SHEET.getSubImage(1, 3);
			
			GREEN_OPEN = RIM_SHEET.getSubImage(2, 2);
			GREEN_CLOSED = RIM_SHEET.getSubImage(2, 3);
			
			BLUE_OPEN = RIM_SHEET.getSubImage(3, 2);
			BLUE_CLOSED = RIM_SHEET.getSubImage(3, 3);
			
			BARRIER = WALL_SHEET.getSubImage(1, 0);
			
			INVENTORY_BUTTON = new Image("res/inventory.png");
			RED_BUTTON = new SpriteSheet("res/red_button.png", 128, 128);
			GREEN_BUTTON = new SpriteSheet("res/green_button.png", 128, 128);
			BLUE_BUTTON = new SpriteSheet("res/blue_button.png", 128, 128);
			
			HEART_FULL = new Image("res/heart_full.png");
			HEART_EMPTY = new Image("res/heart_empty.png");
			
			BUBBLE = new Image("res/bubble.png");
			
			ITEMS = new SpriteSheet("res/item_sheet.png", Constants.TILE_SIZE, Constants.TILE_SIZE);
			MAP = ITEMS.getSubImage(0, 0);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
}
