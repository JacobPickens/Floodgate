package com.pickens.items;

import com.pickens.objects.Player;
import com.pickens.util.Constants;
import com.pickens.util.Images;

public class DuplicationPotionItem extends Item {
	
	int amount;
	
	public DuplicationPotionItem(int x, int y, Player p, Inventory i) {
		super(x, y, p, i);
		setName("Potion of Duplication");
		setDescription("* Duplicates any item.");
		setImage(Images.POTIONS.getSubImage(0, 1));
		setType(Item.ULTRA);
	}

	@Override
	public void action() {
		if(!Constants.REROLL) {
			Constants.CLONE = true;
			i.getSlots()[x][y].setItem(null);
		}
	}

}
