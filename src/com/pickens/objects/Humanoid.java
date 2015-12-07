package com.pickens.objects;

import com.pickens.map.TileMap;

public abstract class Humanoid extends Object {

	private int hp;
	
	public Humanoid(int x, int y, TileMap map, ObjectController oc) {
		super(x, y, map, oc);
	}
	
	public int getHP() {
		return hp;
	}

	public void setHP(int i) {
		hp = i;
	}
	
}
