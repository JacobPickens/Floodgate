package com.pickens.objects;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pickens.items.Item;
import com.pickens.items.Slot;
import com.pickens.map.TileMap;
import com.pickens.states.PlayState;
import com.pickens.util.Constants;

public class Chest extends Object {
	
	Random r = new Random();
	int type;
	
	public Chest(int x, int y, TileMap map, ObjectController oc) {
		super(x, y, map, oc);
		if(map.getRawData()[x][y] == Constants.FLOOR) {
			map.getRawData()[x][y] = Constants.CHEST;
			map.swap(x, y);
			
			if(r.nextInt(100) < Constants.ULTRA_CHANCE) {
				type = Item.ULTRA;
			} else if(r.nextInt(100) < Constants.MAJOR_CHANCE+( Constants.MAP_NUMBER*.2)) {
				type = Item.MAJOR;
			} else if(r.nextInt(100) < Constants.NORMAL_CHANCE) {
				type = Item.NORMAL;
			} else {
				type = Item.MINOR;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		
	}

	@Override
	public void update(GameContainer gc, int delta) {
		
	}
	
	public boolean roll() {
		boolean state = false;
		
		loop:
		for (int y = 0; y < Player.inv.getHeight(); y++) {
			for (int x = 0; x < Player.inv.getWidth(); x++) {
				Slot slot = Player.inv.getSlots()[x][y];
				if(slot.getItem() == null) {
					if(Constants.CHEST_STATE == Constants.RANDOM_STATE) {
						switch (type) {
						case Item.MINOR:
							slot.setItem(Constants.rollMinor(x, y, PlayState.player,
									Player.inv));
							break;
						case Item.NORMAL:
							slot.setItem(Constants.rollNormal(x, y, PlayState.player,
									Player.inv));
							break;
						case Item.MAJOR:
							slot.setItem(Constants.rollMajor(x, y, PlayState.player,
									Player.inv));
							break;
						case Item.ULTRA:
							slot.setItem(Constants.rollUltra(x, y, PlayState.player,
									Player.inv));
							break;
						}
					} else { // Major, Minor, Normal, Ultra potions
						switch (Constants.CHEST_STATE) {
						case Constants.MINOR_STATE:
							slot.setItem(Constants.rollMinor(x, y, PlayState.player,
									Player.inv));
							break;
						case Constants.NORMAL_STATE:
							slot.setItem(Constants.rollNormal(x, y, PlayState.player,
									Player.inv));
							break;
						case Constants.MAJOR_STATE:
							slot.setItem(Constants.rollMajor(x, y, PlayState.player,
									Player.inv));
							break;
						case Constants.ULTRA_STATE:
							slot.setItem(Constants.rollUltra(x, y, PlayState.player,
									Player.inv));
							break;
						}
					}
					state = true;
					break loop;
				}
			}
		}
		
		map.getRawData()[x][y] = Constants.FLOOR;
		map.swap(x, y);
		oc.remove(this);
		return state;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
