package com.pickens.items;

import java.util.Random;

import com.pickens.objects.Player;

public abstract class Equipment extends Item {

	public static final int HEAD = 0;
	public static final int BODY = 1;
	public static final int FEET = 2;
	
	public static final int DAMAGED = 0;
	public static final int USED = 1;
	public static final int NEW = 2;
	
	private int gearType;
	private int condition;
	
	public Equipment(int x, int y, Player p, Inventory i) {
		super(x, y, p, i);
		Random r = new Random();
		if(r.nextInt(100) < 20) {
			setCondition(NEW);
		} else if(r.nextInt(100) < 65) {
			setCondition(USED);
		} else {
			setCondition(DAMAGED);
		}
	}
	
	public abstract void modify();
	public abstract void undo();

	public int getGearType() {
		return gearType;
	}

	public void setGearType(int type) {
		this.gearType = type;
	}

	public int getCondition() {
		return condition;
	}

	public void setCondition(int condition) {
		this.condition = condition;
	}
	
	public static String getConditionString(int condition) {
		String s = "";
		switch(condition) {
		case Equipment.DAMAGED:
			s = "Damaged";
			break;
		case Equipment.USED:
			s = "Used";
			break;
		case Equipment.NEW:
			s = "New";
			break;
		}
		return s;
	}

}
