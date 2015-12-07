package com.pickens.items;

import com.pickens.objects.Player;
import com.pickens.util.Constants;
import com.pickens.util.Images;

public class MapItem extends Item {

	public MapItem(int x, int y, Player p, Inventory i) {
		super(x, y, p, i);
		setName("Map");
		setDescription("* Shows a mini-map in the top right corner\n* If you click on the mini-map it will show the entire map (Including the valve and all water and floodgates)\n* 1 time use\n* Destroyed after leaving sewer");
		setImage(Images.MAP);
		setType(Item.MAJOR);
	}

	@Override
	public void action() {
		Constants.MINI_MAP = true;
		i.getSlots()[x][y].setItem(null);
	}

}
