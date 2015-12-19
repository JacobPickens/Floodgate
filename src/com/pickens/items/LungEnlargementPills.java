package com.pickens.items;

import com.pickens.objects.Player;
import com.pickens.util.Images;

public class LungEnlargementPills extends Item {

	public LungEnlargementPills(int x, int y, Player p, Inventory i) {
		super(x, y, p, i);
		setName("Lung-Enlargement Pills");
		setDescription("* Adds a bubble to your oxygen pool. \n* Just like the pills in the adds, except these actually work!");
		setImage(Images.ITEMS_2.getSubImage(1, 0));
		setType(Item.MAJOR);
	}

	@Override
	public void action() {
		p.setMaxOxygen(p.getMaxOxygen()+1);
		i.getSlots()[x][y].setItem(null);
	}

}
