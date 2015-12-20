package com.pickens.items;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import com.pickens.objects.Player;
import com.pickens.util.Constants;

public abstract class Equipment extends Item {

	public static final int HEAD = 0;
	public static final int BODY = 1;
	public static final int FEET = 2;
	
	public static final int DAMAGED = 0;
	public static final int USED = 1;
	public static final int NEW = 2;
	
	private int gearType;
	private int condition;
	private int maxDurability;
	private int durability;
	
	Random r = new Random();
	
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
		maxDurability = 0;
		durability = 0;
	}
	
	public abstract void modify();
	public abstract void undo();
	public abstract void damageCall(Player p);
	public abstract void onBreak();
	
	public void damage(Player p) {
		damageCall(p);
		if(durability <= 0 && maxDurability > 0) {
			switch(getGearType()) {
			case HEAD:
				i.getHead().setItem(null);
				onBreak();
				undo();
				break;
			case BODY:
				i.getBody().setItem(null);
				onBreak();
				undo();
				break;
			case FEET:
				i.getFeet().setItem(null);
				onBreak();
				undo();
				break;
			}
		}
	}
	
	public void render(Graphics g) {
		getImage().draw(x * Constants.TILE_SIZE + (Inventory.SLOT_SPACING * x), y * Constants.TILE_SIZE + (Inventory.SLOT_SPACING * y));
		if(maxDurability > 0) {
			g.setColor(Color.white);
			g.fillRect(x * Constants.TILE_SIZE + (Inventory.SLOT_SPACING * x) + 2,
					(y * Constants.TILE_SIZE + (Inventory.SLOT_SPACING * y)) + Constants.TILE_SIZE - 8,
					(Constants.TILE_SIZE - 4), 6);
			g.setColor(Color.red);
			float scale = (float)durability / (float)maxDurability;
			g.fillRect(x * Constants.TILE_SIZE + (Inventory.SLOT_SPACING * x) + 2,
					(y * Constants.TILE_SIZE + (Inventory.SLOT_SPACING * y)) + Constants.TILE_SIZE - 8,
					(Constants.TILE_SIZE - 4) * scale, 6);
		}
	}
	
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

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
		this.maxDurability = durability;
	}

	public int getMaxDurability() {
		return maxDurability;
	}
	
	public void addDurability(int a) {
		durability += a;
		if(durability <= 0) {
			switch(getGearType()) {
			case HEAD:
				i.getHead().setItem(null);
				onBreak();
				undo();
				break;
			case BODY:
				i.getBody().setItem(null);
				onBreak();
				undo();
				break;
			case FEET:
				i.getFeet().setItem(null);
				onBreak();
				undo();
				break;
			}
		}
	}

}
