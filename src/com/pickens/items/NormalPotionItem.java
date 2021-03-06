package com.pickens.items;

import com.pickens.buffs.NormalBuff;
import com.pickens.map.TileMap;
import com.pickens.objects.Player;
import com.pickens.states.PlayState;
import com.pickens.util.Images;

public class NormalPotionItem extends Item {

	TileMap map;
	int px = 0;
	int py = 0;
	
	public NormalPotionItem(int x, int y, Player p, Inventory i) {
		super(x, y, p, i);
		setName("Potion of Normal");
		setDescription("* Fills all chests with normal loot for 15 seconds.");
		setImage(Images.POTIONS.getSubImage(2, 1));
		setType(Item.MAJOR);
		map = PlayState.player.getMap();
	}

	@Override
	public void action() {
		p.getBuffs().add(new NormalBuff(0, 15*60, getImage(), p.getBuffs()));
		i.getSlots()[x][y].setItem(null);
	}

}
