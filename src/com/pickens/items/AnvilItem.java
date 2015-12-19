package com.pickens.items;

import com.pickens.objects.Player;
import com.pickens.util.Constants;
import com.pickens.util.Images;

public class AnvilItem extends Item {

	public AnvilItem(int x, int y, Player p, Inventory i) {
		super(x, y, p, i);
		setName("Anvil");
		setDescription("* Repairs any piece of equipment to max. \n* Double-Click to use this item");
		setImage(Images.ITEMS_2.getSubImage(0, 0));
		setType(Item.MAJOR);
	}

	@Override
	public void action() {
		Constants.REPAIR = true;
		i.getSlots()[x][y].setItem(null);
	}

}
