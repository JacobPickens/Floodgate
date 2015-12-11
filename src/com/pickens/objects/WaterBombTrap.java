package com.pickens.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pickens.map.TileMap;

public class WaterBombTrap extends TriggeredTrap {

	public WaterBombTrap(int x, int y, int direction, TileMap map, ObjectController oc) {
		super(x, y, direction, map, oc);
	}

	@Override
	public void trigger() {
		oc.remove(this);
		oc.add(new Water(x, y, map, oc));
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameContainer gc, int delta) {
		// TODO Auto-generated method stub

	}

}
