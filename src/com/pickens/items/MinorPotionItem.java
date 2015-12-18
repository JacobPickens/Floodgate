package com.pickens.items;

import com.pickens.buffs.FreezeBuff;
import com.pickens.map.TileMap;
import com.pickens.objects.Player;
import com.pickens.objects.Water;
import com.pickens.states.PlayState;
import com.pickens.util.Constants;
import com.pickens.util.Images;

public class MinorPotionItem extends Item {

	TileMap map;
	int px = 0;
	int py = 0;
	
	public MinorPotionItem(int x, int y, Player p, Inventory i) {
		super(x, y, p, i);
		setName("Potion of Minor");
		setDescription("* Fills all chests with minor loot for 30 seconds.");
		setImage(Images.POTIONS.getSubImage(1, 1));
		setType(Item.MAJOR);
		map = PlayState.player.getMap();
	}

	@Override
	public void action() {
		p.getBuffs().add(new FreezeBuff(0, 30*60, getImage(), p.getBuffs()));
		i.getSlots()[x][y].setItem(null);
	}

}
