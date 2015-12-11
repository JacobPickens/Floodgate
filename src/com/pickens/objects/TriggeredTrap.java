package com.pickens.objects;

import com.pickens.map.TileMap;

public abstract class TriggeredTrap extends Trap {

	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	
	int direction;
	
	public TriggeredTrap(int x, int y, int direction, TileMap map, ObjectController oc) {
		super(x, y, map, oc);
		this.direction = direction;
	}

	public abstract void trigger();
	
}
