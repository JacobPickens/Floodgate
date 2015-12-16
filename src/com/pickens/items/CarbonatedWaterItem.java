package com.pickens.items;

import java.util.Random;

import com.pickens.objects.Player;
import com.pickens.util.Constants;
import com.pickens.util.Images;

public class CarbonatedWaterItem extends Item {
	
	int amount;
	
	public CarbonatedWaterItem(int x, int y, Player p, Inventory i) {
		super(x, y, p, i);
		amount = new Random().nextInt(5)+3;
		setName("Carbonated Water");
		setDescription("* Gives \n* This fizzy drink is the same as regular water, it just costs more!");
		setImage(Images.POTIONS.getSubImage(1, 0));
		setType(Item.MAJOR);
	}

	@Override
	public void action() {
		if(p.getOxygen()+amount <= Constants.currentCharacter.getOxygen()) {
			p.setOxygen(p.getOxygen()+amount);
		} else if(p.getOxygen() <= Constants.currentCharacter.getOxygen()) {
			p.setOxygen(Constants.currentCharacter.getOxygen());
		}
		i.getSlots()[x][y].setItem(null);
	}

}
