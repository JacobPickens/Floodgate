package com.pickens.objects;

import org.newdawn.slick.GameContainer;

import com.pickens.items.Inventory;
import com.pickens.map.TileMap;
import com.pickens.util.Constants;

public abstract class NPC extends Object {

	// Should completely rework how these inventories work eventually
	
	Inventory inv;
	private int health;
	int maxOxygen = 10;
	int oxygen = 10;
	int ticker = 0;
	int checker = 60;
	
	public NPC(int x, int y, TileMap map, ObjectController oc) {
		super(x, y, map, oc);
		health = 0;
	}
	
	public abstract void action();
	public abstract void updateCall(GameContainer gc, int delta);
	
	@Override
	public void update(GameContainer gc, int delta) {
		if(this.map.getRawData()[this.x][this.y] == Constants.WATER) {
			drown();
		} else {
			breathe();
		}		
		updateCall(gc, delta);
	}
	
	public void drown() {
		ticker++;
		if(ticker >= checker) {
			ticker = 0;
			oxygen--;
		}
		if(oxygen <= 0) {
			oc.remove(this);
		}
	}
	
	public void breathe() {
		ticker++;
		if(ticker >= checker) {
			ticker = 0;
			if(oxygen < maxOxygen) {
				oxygen++;
			}
		}
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public Inventory getInventory() {
		return inv;
	}
	
	public void setInventory(Inventory inv) {
		this.inv = inv;
	}

}
