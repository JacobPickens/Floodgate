package com.pickens.objects;

import com.pickens.map.TileMap;

public abstract class Trap extends Object {

	public Trap(int x, int y, TileMap map, ObjectController oc) {
		super(x, y, map, oc);
	}

}
