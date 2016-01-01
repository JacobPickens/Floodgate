package com.pickens.util;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

public class Character {
	
	private int oxygen;
	private int speed;
	private int waterSpeed;
	private int health;
	private int invWidth;
	private int invHeight;
	private Color color;
	
	//////////////////// Player ////////////////////
	public Image PLAYER_UP;
	public Image PLAYER_DOWN;
	public Image PLAYER_LEFT;
	public Image PLAYER_RIGHT;
	
	public Animation PLAYER_WUP;
	public Animation PLAYER_WDOWN;
	public Animation PLAYER_WLEFT;
	public Animation PLAYER_WRIGHT;
	
	public Character(int oxygen, int speed, int health, int invWidth, int invHeight) {
		this.oxygen = oxygen;
		this.speed = speed;
		this.waterSpeed = speed + (int) (this.speed*.5);
		this.health = health;
		this.invWidth = invWidth;
		this.invHeight = invHeight;
		
		////////////////////Player ////////////////////
		Images.PLAYER_SHEET.setImageColor(0, 0, 0);
		
		PLAYER_DOWN = Images.PLAYER_SHEET.getSubImage(0, 0);
		PLAYER_RIGHT = Images.PLAYER_SHEET.getSubImage(0, 1);
		PLAYER_LEFT = Images.PLAYER_SHEET.getSubImage(0, 2);
		PLAYER_UP = Images.PLAYER_SHEET.getSubImage(0, 3);
		
		Image[] frames = {Images.PLAYER_SHEET.getSubImage(1, 0), Images.PLAYER_SHEET.getSubImage(2, 0)};
		PLAYER_WDOWN = new Animation(frames, (int) ((speed*60)*0.8f));
		
		Image[] frames1 = {Images.PLAYER_SHEET.getSubImage(1, 1), Images.PLAYER_SHEET.getSubImage(2, 1)};
		PLAYER_WRIGHT = new Animation(frames1, (int) ((speed*60)*0.8f));
		
		Image[] frames2 = {Images.PLAYER_SHEET.getSubImage(1, 2), Images.PLAYER_SHEET.getSubImage(2, 2)};
		PLAYER_WLEFT = new Animation(frames2, (int) ((speed*60)*0.8f));
		
		Image[] frames3 = {Images.PLAYER_SHEET.getSubImage(1, 3), Images.PLAYER_SHEET.getSubImage(2, 3)};
		PLAYER_WUP = new Animation(frames3, (int) ((speed*60)*0.8f));
	}

	public int getOxygen() {
		return oxygen;
	}

	public void setOxygen(int oxygen) {
		this.oxygen = oxygen;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getWaterSpeed() {
		return waterSpeed;
	}

	public void setWaterSpeed(int waterSpeed) {
		this.waterSpeed = waterSpeed;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getInventoryWidth() {
		return invWidth;
	}

	public void setInventoryWidth(int invWidth) {
		this.invWidth = invWidth;
	}

	public int getInventoryHeight() {
		return invHeight;
	}

	public void setInventoryHeight(int invHeight) {
		this.invHeight = invHeight;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
}
