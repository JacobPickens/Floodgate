package com.pickens.objects;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pickens.map.TileMap;
import com.pickens.util.Constants;

public class Water extends Object {
	
	private int spreadCount = 0;
	private int spread;
	
	private int multiplier = 1;
	private boolean frozen = false;
	
	public Water(int x, int y, TileMap map, ObjectController oc) {
		super(x, y, map, oc);
		if(map.getRawData()[x][y] == Constants.FLOOR) {
			map.getRawData()[x][y] = Constants.WATER;
			map.swap(x, y);
		}
		multiplier += Constants.MAP_NUMBER * .4f;
		spread = 100 - new Random().nextInt(30*multiplier);
	}

	@Override
	public void render(Graphics g) {
		
	}
	
	Random r = new Random();
	@Override
	public void update(GameContainer gc, int delta) {
		if(!Constants.PAUSED) {
			spreadCount++;
		}
		if(!isFrozen() && !Constants.FROZEN) {
			if(spreadCount >= spread) {
				spreadCount = 0;
				if(x > 0 && (map.getRawData()[x-1][y] == Constants.FLOOR) && (oc.getObject(x-1, y) == null || oc.getObject(x-1, y) instanceof FaucetStopper || oc.getObject(x-1, y) instanceof Trap || oc.getObject(x-1, y) instanceof Floodgate)) {
					spread(x-1, y);
				}
				if(x < map.getWidth()-1 && map.getRawData()[x+1][y] == Constants.FLOOR && (oc.getObject(x+1, y) == null || oc.getObject(x+1, y) instanceof FaucetStopper || oc.getObject(x+1, y) instanceof Trap || oc.getObject(x+1, y) instanceof Floodgate)) {
					spread(x+1, y);
				}
				if(y > 0 && map.getRawData()[x][y-1] == Constants.FLOOR && (oc.getObject(x, y-1) == null || oc.getObject(x, y-1) instanceof FaucetStopper || oc.getObject(x, y-1) instanceof Trap || oc.getObject(x, y-1) instanceof Floodgate)) {
					spread(x, y-1);
				}
				if(y < map.getHeight()-1 && map.getRawData()[x][y+1] == Constants.FLOOR && (oc.getObject(x, y+1) == null || oc.getObject(x, y+1) instanceof Trap || oc.getObject(x, y+1) instanceof FaucetStopper || oc.getObject(x, y+1) instanceof Floodgate)) {
					spread(x, y+1);
				}
			}
		} else {
			if (spreadCount >= 50) {
				spreadCount = 0;
				if (x > 0 && (map.getRawData()[x - 1][y] == Constants.WATER)
						&& !((Water) map.getObjectController().getObject(x - 1, y)).isFrozen()) {
					((Water) map.getObjectController().getObject(x - 1, y)).toggleFreeze();
				}
				if (x < map.getWidth() - 1 && map.getRawData()[x + 1][y] == Constants.WATER
						&& !((Water) map.getObjectController().getObject(x + 1, y)).isFrozen()) {
					((Water) map.getObjectController().getObject(x + 1, y)).toggleFreeze();
				}
				if (y > 0 && map.getRawData()[x][y - 1] == Constants.WATER
						&& !((Water) map.getObjectController().getObject(x, y - 1)).isFrozen()) {
					((Water) map.getObjectController().getObject(x, y - 1)).toggleFreeze();
				}
				if (y < map.getHeight() - 1 && map.getRawData()[x][y + 1] == Constants.WATER
						&& !((Water) map.getObjectController().getObject(x, y + 1)).isFrozen()) {
					((Water) map.getObjectController().getObject(x, y + 1)).toggleFreeze();
				}
			}
		}
		
		if(!Constants.FROZEN && isFrozen()) {
			if(r.nextInt(100) < 10) {
				toggleFreeze();
			}
		}
	}
	
	private void spread(int x, int y) {
		Object tester = oc.getObject(x, y);
		if(tester instanceof Floodgate) {
			if(!((Floodgate) tester).closed) {
				oc.add(new Water(x, y, map, oc));
			}
		} else {
			oc.add(new Water(x, y, map, oc));
		}
	}
	
	public void toggleFreeze() {
		if(frozen) {
			frozen = false;
		} else {
			frozen = true;
		}
	}
	
	public boolean isFrozen() {
		return frozen;
	}

}
