package com.pickens.items;

import com.pickens.buffs.FreezeBuff;
import com.pickens.map.TileMap;
import com.pickens.objects.Player;
import com.pickens.objects.Water;
import com.pickens.states.PlayState;
import com.pickens.util.Constants;
import com.pickens.util.Images;

public class FreezePotionItem extends Item {

	TileMap map;
	int px = 0;
	int py = 0;
	
	public FreezePotionItem(int x, int y, Player p, Inventory i) {
		super(x, y, p, i);
		setName("Potion of Freeze");
		setDescription("* Freezes water for 10 seconds.\n* Must be used in water.");
		setImage(Images.POTIONS.getSubImage(3, 0));
		setType(Item.MAJOR);
		map = PlayState.player.getMap();
	}

	@Override
	public void action() {
		if(PlayState.player.isUnderwater()) {
			p.getBuffs().add(new FreezeBuff(0, 10*60, getImage(), p.getBuffs()));
			i.getSlots()[x][y].setItem(null);
			Constants.FROZEN = true;
			px = PlayState.player.getX();
			py = PlayState.player.getY();
			if(x > 0 && (map.getRawData()[x-1][y] == Constants.WATER)) {
				((Water) map.getObjectController().getObject(x-1, y)).toggleFreeze();
			}
			if(x < map.getWidth()-1 && map.getRawData()[x+1][y] == Constants.WATER) {
				((Water) map.getObjectController().getObject(x+1, y)).toggleFreeze();
			}
			if(y > 0 && map.getRawData()[x][y-1] == Constants.WATER) {
				((Water) map.getObjectController().getObject(x, y-1)).toggleFreeze();
			}
			if(y < map.getHeight()-1 && map.getRawData()[x][y+1] == Constants.WATER) {
				((Water) map.getObjectController().getObject(x, y+1)).toggleFreeze();
			}
		}
	}

}
