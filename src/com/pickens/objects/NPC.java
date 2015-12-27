package com.pickens.objects;

import com.pickens.items.Inventory;
import com.pickens.map.TileMap;

public abstract class NPC extends Object {

	// Should completely rework how these inventories work eventually
	
	Inventory inv;
	private int health;
	
	public NPC(int x, int y, TileMap map, ObjectController oc) {
		super(x, y, map, oc);
		health = 0;
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
