package com.pickens.items;

import com.pickens.buffs.MajorBuff;
import com.pickens.map.TileMap;
import com.pickens.objects.Player;
import com.pickens.states.PlayState;
import com.pickens.util.Images;

public class MajorPotionItem extends Item {

	TileMap map;
	int px = 0;
	int py = 0;
	
	public MajorPotionItem(int x, int y, Player p, Inventory i) {
		super(x, y, p, i);
		setName("Potion of Major");
		setDescription("* Fills all chests with major loot for 5 seconds.");
		setImage(Images.POTIONS.getSubImage(3, 1));
		setType(Item.ULTRA);
		map = PlayState.player.getMap();
	}

	@Override
	public void action() {
		p.getBuffs().add(new MajorBuff(0, 5*60, getImage(), p.getBuffs()));
		i.getSlots()[x][y].setItem(null);
	}

}
