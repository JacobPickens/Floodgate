package com.pickens.items;

import com.pickens.buffs.SpeedBuff;
import com.pickens.objects.Player;
import com.pickens.util.Images;

public class SpeedPotionItem extends Item {
	
	int amount;
	
	public SpeedPotionItem(int x, int y, Player p, Inventory i) {
		super(x, y, p, i);
		amount = 3;
		setName("Potion of Speed");
		setDescription("* Increases all speeds by " + amount + " for 7 seconds.");
		setImage(Images.POTIONS.getSubImage(0, 0));
		setType(Item.NORMAL);
	}

	@Override
	public void action() {
		p.getBuffs().add(new SpeedBuff(amount, 7*60, getImage(), p.getBuffs()));
		i.getSlots()[x][y].setItem(null);
	}

}
