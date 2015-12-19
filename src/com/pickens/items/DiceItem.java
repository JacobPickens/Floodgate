package com.pickens.items;

import com.pickens.objects.Player;
import com.pickens.util.Constants;
import com.pickens.util.Images;

public class DiceItem extends Item {
	
	public DiceItem(int x, int y, Player p, Inventory i) {
		super(x, y, p, i);
		setName("Dice");
		setDescription("* Re-rolls the item you choose (For better or for worse.). \n* Double-Click to use this item");
		setImage(Images.ITEMS.getSubImage(3, 0));
		setType(Item.MAJOR);
	}

	@Override
	public void action() {
		if(!Constants.CLONE) {
			Constants.REROLL = true;
			i.getSlots()[x][y].setItem(null);
		}
	}

}
