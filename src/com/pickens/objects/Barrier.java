package com.pickens.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pickens.map.TileMap;
import com.pickens.util.Constants;

public class Barrier extends Object {
	
	int health = 60;
	
	int healthTicker = 0;
	int healthCheck = 60;
	
	public Barrier(int x, int y, TileMap map, ObjectController oc) {
		super(x, y, map, oc);
		if(map.getRawData()[x][y] == Constants.FLOOR) {
			map.getRawData()[x][y] = Constants.BARRIER;
			map.swap(x, y);
		}
	}

	@Override
	public void render(Graphics g) {
		
	}

	@Override
	public void update(GameContainer gc, int delta) {
		if(y > 0 && map.getRawData()[x][y-1] == Constants.WATER) {
			damage();
		}
		if(y < map.getHeight() && map.getRawData()[x][y+1] == Constants.WATER) {
			damage();
		}
		if(x > 0 && map.getRawData()[x-1][y] == Constants.WATER) {
			damage();
		}
		if(y < map.getWidth() && map.getRawData()[x+1][y] == Constants.WATER) {
			damage();
		}
	}
	
	public void damage() {
		healthTicker++;
		if(healthTicker >= healthCheck) {
			healthTicker = 0;
			health--;
		}
		
		if(health == 0) {
			oc.remove(this);
			map.getRawData()[x][y] = Constants.FLOOR;
			map.swap(x, y);
		}
	}

}

